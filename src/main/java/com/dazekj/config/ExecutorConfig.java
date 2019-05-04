package com.dazekj.config;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @CliassName: ExecutorConfig
 * @Author: DZ5343
 * @Date: 2019/4/15 15:51
 * @Description:  配置类创建执行器
 */


@Configuration
public class ExecutorConfig {
    @Value("${appkey}")
    private String appkey;

    @Value("${seckey}")
    private String seckey;

    @Bean
    public ApiExecutor getApiExecutor(){
        System.out.println(appkey);
        //判断key是否为空
        ApiExecutor apiExecutor = null;
        try {
            apiExecutor = new ApiExecutor(appkey, seckey);
        } catch (Exception e) {
            throw new RuntimeException("创建执行器失败");
        }
        return apiExecutor;
    }


}
