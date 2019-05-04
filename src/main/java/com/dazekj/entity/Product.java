package com.dazekj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @CliassName: Product
 * @Author: DZ5343
 * @Date: 2019/4/15 10:42
 * @Description: 商品列表分装类
 */
@Document(indexName="elastic",type="product" )
public class Product {


    @Id
    private Long id;

    /**
     *@author DZ5343
     *@date 2019/4/15 10:44
     *@desc 商品名称
     */
    private String prodName;

    /**
     *@author DZ5343
     *@date 2019/4/15 10:44
     *@desc 商品分类名称
     */
    private String prodCategory;

    /**
     *@author DZ5343
     *@date 2019/4/15 10:45
     *@desc 商品简称
     */
    private String shortName;

    /**
     *@author DZ5343
     *@date 2019/4/15 10:45
     *@desc 必须有无参构造器，否则会导致Jackson解析错误
     */
    public Product() {
        // TODO Auto-generated constructor stub
    }

    public Product(Long id, String prodName, String prodCategory, String shortName) {
        this.id = id;
        this.prodName = prodName;
        this.prodCategory = prodCategory;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", prodCategoryId='" + prodCategory + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
