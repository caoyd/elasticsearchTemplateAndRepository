package com.dazekj.dao;

import com.dazekj.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.lang.String;
import java.util.List;


public interface ProductRepository extends ElasticsearchRepository<Product, Long>{

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:30
	 *@desc  根据产品名称查询并分页
	 */
	Page<Product> findByProdName(String prodname, Pageable pageable);

	/**
	 *@author DZ5343
	 *@date 2019/4/15 10:30
	 *@desc 根据产品分类查询
	 */
	List<Product> findByProdCategory(String prodcategory);

}
