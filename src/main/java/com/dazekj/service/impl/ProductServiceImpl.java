package com.dazekj.service.impl;

import java.util.*;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.common.SDKResult;
import com.alibaba.product.param.AlibabaCategoryCategoryInfo;
import com.alibaba.product.param.AlibabaCategoryGetParam;
import com.alibaba.product.param.AlibabaCategoryGetResult;
import com.dazekj.entity.Category;
import com.dazekj.entity.Product;
import com.dazekj.model.PageResult;
import com.dazekj.model.ResponseMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dazekj.utils.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import com.dazekj.dao.ProductRepository;
import com.dazekj.service.ProductService;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;




//在实现类而不是接口上添加注解
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	public ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	public ApiExecutor apiExecutor;


	@Autowired
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:54
	 *@desc	 本地库操作,保存商品
	 */
	@Override
	public Product save(Product product) {

		return prodRepository.save(product);
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:54
	 *@desc	 本地库操作,根据id删除具体的文档
	 */
	@Override
	public void deleteByProductId(long id) {
		prodRepository.deleteById(id);
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:55
	 *@desc 本地库操作,根据商品id,查找商品列表
	 */
	@Override
	public Optional<Product> findByProductId(long id) {
		return prodRepository.findById(id);
	}


	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:56
	 *@desc	 本地库操作,根据商品名称以及分页条件,查询商品分页列表数据!
	 */
	@Override
	public Page<Product> findByProductName(String name,PageRequest pageRequest) {
		return prodRepository.findByProdName(name,pageRequest);
	}
	
	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:57
	 *@desc	 本地库操作,根据商品分类查询商品列表
	 */
	@Override
	public List<Product> findByProductCategory(String category) {
		return prodRepository.findByProdCategory(category);
	}


	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:53
	 *@desc 1688接口,通过商品分裂id,查询商品分类
	 */
	@Override
	public List<AlibabaCategoryCategoryInfo> findProductCategoryById(Long categoryId) {


//		//创建执行器
//		ExecutorUtils executorUtils = new ExecutorUtils();
//		ApiExecutor apiExecutor = executorUtils.getApiExecutor();



		//构建参数
		AlibabaCategoryGetParam param = new AlibabaCategoryGetParam();
		param.setCategoryID(categoryId);

		SDKResult<AlibabaCategoryGetResult> result = apiExecutor.execute(param);

		AlibabaCategoryCategoryInfo[] categoryInfo = result.getResult().getCategoryInfo();

		List<AlibabaCategoryCategoryInfo> list = Arrays.asList(categoryInfo);

		return list;
	}


	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:53
	 *@desc 1688接口,查询用户自定义列表
	 */
	@Override
	public Map findUserCategory(String accessToken) {
		//参数构建
		AlibabaUserCategoryListGetParam param = new AlibabaUserCategoryListGetParam();

		SDKResult<AlibabaUserCategoryListGetResult> result = apiExecutor.execute(param, accessToken);
		if ("401".equals(result.getErrorCode())){
			return ResponseMap.fail("没有授权的用户或身份权限过期",5000);
		}

		ArrayList<Category> categories = new ArrayList<>();
		UserCategoryInfo[] cateList = result.getResult().getCateList();
		List<UserCategoryInfo> categoryInfos = Arrays.asList(cateList);

		//遍历处理数据
		for (UserCategoryInfo userCategoryInfo: categoryInfos) {
			Category category = new Category();
			category.setId(userCategoryInfo.getId());
			category.setName(userCategoryInfo.getName());
			categories.add(category);
		}

		return ResponseMap.success(categories);
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:52
	 *@desc	 本地库操作,取出商品数据
	 */
	public List<Product> findProdByProdName(String productName ,@PageableDefault Pageable pageable){
		//本地es库取出商品数据
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("prodName",productName)).withPageable(pageable).build();
		List<Product> products = null;
		try {
			products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
		} catch (Exception e) {
			throw 	new  RuntimeException("本地索引库所有发生错误");
		}
		return products;
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:52
	 *@desc	 根据商品名称关键字,查询商品列表
	 */
	@Override
	public Map findProductList(String productName, Integer pageNo, Integer pageSize,String accesstoken) {

		//参数构建
		AlibabaProductListGetParam param = new AlibabaProductListGetParam();
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setSubjectKey(productName);

		//查询出结果alibab
		SDKResult<AlibabaProductListGetResult> result = apiExecutor.execute(param, accesstoken);
		if ("401".equals(result.getErrorCode())){
			return ResponseMap.fail("没有授权的用户或身份权限过期",5000);
		}
		//结果处理
		List<Product> productArrayList = new ArrayList<>();
		PageResult pageResult = new PageResult();

		AlibabaProductProductInfo[] resultList = result.getResult().getResult().getPageResult().getResultList();
		if (resultList==null || resultList.length<=0){
			return ResponseMap.fail("未找到数据",3000);
		}

		List<AlibabaProductProductInfo> aliproductInfos = Arrays.asList(resultList);

		Pageable pageRequest = new PageRequest(pageNo, pageSize);
		//查询本地数据库,分页查询
		List<Product> product_local = findProdByProdName(productName, pageRequest);

		for (AlibabaProductProductInfo aliproductInfo: aliproductInfos) {
			Product product = new Product();
			for (Product prod_local:product_local) {
					//如果在本地es库有数据,
					if (aliproductInfo.getProductID() == prod_local.getId()){
						//本地数据
						productArrayList.add(prod_local);
					}
			}

			//获取aliababa商品数据
			product.setId(aliproductInfo.getProductID());
			product.setProdName(aliproductInfo.getSubject());
			product.setProdCategory(aliproductInfo.getCategoryName());
			//添加到List

			productArrayList.add(product);
		}
		//获取结果总个数
		int totalCount = productArrayList.size();
		int totalPages= (int) Math.ceil(totalCount / pageSize);

		pageResult.setTotalCount(totalCount);
		pageResult.setPageIndex(pageNo);
		pageResult.setPageSize(pageSize);
		pageResult.setTotalPages(totalPages);
		pageResult.setList(productArrayList);

		return ResponseMap.success(pageResult);
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:51
	 *@desc	 本地库操作,删除所有文档
	 */
	@Override
	public void deleteAll() {
		prodRepository.deleteAll();
	}

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:50
	 *@desc 本地库操作,查询所有的文档
	 */
	@Override
	public Iterable<Product> findAll() {
		return prodRepository.findAll();
	}

}
