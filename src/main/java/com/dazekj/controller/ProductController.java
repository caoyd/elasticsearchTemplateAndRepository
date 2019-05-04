package com.dazekj.controller;

import com.alibaba.product.param.AlibabaCategoryCategoryInfo;

import com.dazekj.entity.Product;
import com.dazekj.model.ResponseMap;
import com.dazekj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @CliassName: ProductController
 * @Author: DZ5343
 * @Date: 2019/4/15 10:25
 * @Description: Product处理器
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     *@author DZ5343
     *@date 2019/4/15 11:00
     *@desc 通过商品分类id,查询商品分类
     */
    @GetMapping("findCategoryByCategoryId")
    public Map findCategoryByCategoryId(String categoryId){
        //判断categoryId
        if (categoryId.isEmpty()){
            return ResponseMap.fail("数据验证不通过",5010);
        }
        long id = Long.parseLong(categoryId);
        List<AlibabaCategoryCategoryInfo> productCategory = productService.findProductCategoryById(id);

        //结果封装
        return ResponseMap.success(productCategory);
    }

    /**
     *@author DZ5343
     *@date 2019/4/15 11:01
     *@desc 查询用户自定义类目
     */
    @GetMapping("findUserCategory")
    public Map findUserCategory(String accessToken){
        if (StringUtils.isEmpty(accessToken)){
            return ResponseMap.fail("数据验证不通过",5010);
        }
        Map category = productService.findUserCategory(accessToken);

        return category;
    }

    /**
     *@author DZ5343
     *@date 2019/4/15 11:02
     *@desc  alibaba接口,通过商品名称关键字,查询商品列表
     */
    @GetMapping("findProductList")
    public Map findProductList(String productName,Integer pageNo , Integer pageSize , String accessToken){
        //判断页面信息
        if (StringUtils.isEmpty(productName) || StringUtils.isEmpty(accessToken)){
            return ResponseMap.fail("数据验证不通过",5010);
        }
        if (pageNo == null){
            pageNo = 1;
        }
        if (pageSize == null){
            pageSize = 20;
        }
        Map productList = productService.findProductList(productName, pageNo, pageSize, accessToken);
        return productList;
    }


    /**
     *@author DZ5343
     *@date 2019/4/15 11:02
     *@desc  保存商品简称
     */
    @PutMapping("saveProduct")
    public Map saveProduct(@RequestBody Product product, String name){
        if (product==null){
            return ResponseMap.fail("数据验证不通过",5010);
        }
        if (StringUtils.isEmpty(name)){
            return ResponseMap.fail("数据验证不通过",5010);
        }
        if (product.getShortName().isEmpty()){
            //保存到本地索引库
            product.setShortName(name);
            Product product1 = productService.save(product);
            return ResponseMap.success(product1);
        }else {
            return ResponseMap.fail("已设置简称",999);
        }
    }

}
