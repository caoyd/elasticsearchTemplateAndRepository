package com.dazekj.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @CliassName: ResponseMap1
 * @Author: DZ5343
 * @Date: 2019/4/15 10:58
 * @Description:
 */
public class ResponseMap {
    /**
     *@author DZ5343
     *@date 2019/4/15 10:58
     *@desc 接口成功
     */
    public static Map<String,Object> success(Object object){
        Map<String, Object> resopnseMap = new HashMap<>();
        resopnseMap.put("result",object);
        resopnseMap.put("msg","succcess");
        resopnseMap.put("code",200);
        return resopnseMap;
    }


    /**
     *@author DZ5343
     *@date 2019/4/15 10:59
     *@desc 接口失败
     */
    public static Map<String,Object>  fail(String exceptionMessage,int code){
        Map<String, Object> resopnseMap = new HashMap<>();
        resopnseMap.put("result",null);
        resopnseMap.put("msg",exceptionMessage);
        resopnseMap.put("code",code);
        return resopnseMap;
    }
}
