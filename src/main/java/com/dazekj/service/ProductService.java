package com.dazekj.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alibaba.product.param.AlibabaCategoryCategoryInfo;
import com.dazekj.entity.Category;
import com.dazekj.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ProductService {

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:33
	 *@desc 保存商品,到本地库
	 */
	Product save(Product product);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:33
	 *@desc 本地库操作,删除全部产品
	 */
	void deleteAll();

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:33
	 *@desc 本地库操作,根据产品id删除指定商品
	 */
	void deleteByProductId(long id);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:35
	 *@desc 本地库操作,查询全部商品
	 */
	Iterable<Product> findAll();

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:35
	 *@desc 本地库操作,根据商品id查询指定商品
	 */
	Optional<Product> findByProductId(long id);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:36
	 *@desc 本地库操作,根据商品名称查询商品，并分页
	 */
	Page<Product> findByProductName(String name,PageRequest pageRequest);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:36
	 *@desc 根据商品分类查询商品
	 */
	List<Product> findByProductCategory(String category);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:36
	 *@desc 1688接口,根据产品分类id,查询商品类目
	 */
	List<AlibabaCategoryCategoryInfo> findProductCategoryById(Long categoryId );

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:37
	 *@desc	 根据用户身份token,获取用户自定义目录
	 */
	Map findUserCategory(String accessToken);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:38
	 *@desc	 根据商品名称搜索，查询卖家商品列表
	 */
	Map findProductList(String productName ,Integer pageNo , Integer pageSize , String accessToken);


}
