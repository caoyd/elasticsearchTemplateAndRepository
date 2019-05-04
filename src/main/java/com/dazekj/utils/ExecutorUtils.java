package com.dazekj.utils;

import com.alibaba.ocean.rawsdk.ApiExecutor;

//初始化一个执行器,
public class ExecutorUtils {

    private static final String appkey = "6105336";
    private static final String seckey ="zW2kqOCKtJuX";

    public ApiExecutor getApiExecutor(){
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
