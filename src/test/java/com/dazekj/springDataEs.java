package com.dazekj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.dazekj.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dazekj.service.ProductService;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class springDataEs {

	/**
	 * 学习es,以及数据测试crud方法
	 */
	@Autowired
	private ProductService productService;

	//测试
	@Value("${spring.data.elasticsearch.cluster-name}")
	private String name;

	@Value("${appkey}")
	private String appkey;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	private ObjectMapper mapper = new ObjectMapper();
		
	//向ES中插入全部所需商品数据
	@Test
	public void insertAllData() {
		System.out.println(appkey);
		System.out.println(name);
		productService.save(new Product());
	}
	
	//删除ES中存储的全部商品数据
	@Test
	public void deleteAllData() {
		productService.deleteAll();
	}
	
	//根据商品id删除ES中指定产品数据
	@Test
	public void testDeleteByProdId() {
		productService.deleteByProductId(1L);
	}
	
	//查询全部商品数据
	@Test
	public void testFindAll() {
		ArrayList<Product> products = new ArrayList<>();
		Iterator<Product> pIterator = productService.findAll().iterator();
		while (pIterator.hasNext()) {
			products.add(pIterator.next());
		}
		System.out.println("全部数据总数为: " + products.size()+"，最初插入总数为37");
	}
	//根据商品id查询
	@Test
	public void testFindByProdId() {
		Optional<Product> product = productService.findByProductId(1L);
		System.out.println(product);
		try {
			System.out.println("商品id查询数据: " + mapper.writeValueAsString(product));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//根据商品名称查询，并分页
	@Test
	public void testFindByProdName() {
		//ES中共有8条电吹风数据
		Page<Product> prodPage = productService.findByProductName("电吹风", new PageRequest(0, 10));
		
		//无论分页数设置为5还是10，输出的“数据条目总数：”都是8.只不过设置为5时后面打印“prod page:”时只会输出5条数据 
		System.out.println("商品名查询数据条目总数：" + prodPage.getTotalElements());
		try {
			System.out.println("商品名查询数据: " + mapper.writeValueAsString(prodPage.getContent()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据商品大类查询
	@Test
	public void testFindByProdCategory() {
		List<Product> products = productService.findByProductCategory("数码电子");
		try {
			System.out.println("商品分类查询数据: " + mapper.writeValueAsString(products));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void findProdByProdName(){
		//本地es库取出 模糊查询 商品数据
		Pageable pageRequest = new PageRequest(0, 5);
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("prodName", "连衣裙")).withPageable(pageRequest).build();
		List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
		System.out.println(products);
	}



}
