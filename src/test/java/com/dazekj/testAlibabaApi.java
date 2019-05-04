package com.dazekj;

import com.alibaba.ocean.rawsdk.ApiExecutor;

import com.alibaba.ocean.rawsdk.common.SDKResult;
import com.alibaba.product.param.AlibabaCategoryCategoryInfo;
import com.alibaba.product.param.AlibabaCategoryGetParam;
import com.alibaba.product.param.AlibabaCategoryGetResult;
import com.alibaba.product.param.AlibabaChildCategoryInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testAlibabaApi {
    /**
     * 测试alibabaAPI
     */
    @Test
    public void testAlibabaApi(){
        ApiExecutor apiExecutor = new ApiExecutor("6105336", "zW2kqOCKtJuX");
        AlibabaCategoryGetParam param = new AlibabaCategoryGetParam();
        param.setCategoryID(0L);

        SDKResult<AlibabaCategoryGetResult> result = apiExecutor.execute(param, "  ");
        AlibabaCategoryCategoryInfo[] categoryInfo = result.getResult().getCategoryInfo();
        for (AlibabaCategoryCategoryInfo  category: categoryInfo) {
            AlibabaChildCategoryInfo[] childCategorys = category.getChildCategorys();
            for (AlibabaChildCategoryInfo child: childCategorys
                 ) {
                String name = child.getName();
                System.out.println(name);
        }
        }
    }
}
