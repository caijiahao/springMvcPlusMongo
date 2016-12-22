package com.lida.mongo.util;


import net.sf.json.JSONObject;

/**
 * Created by stevenfen on 2016/12/22.
 * json工具类
 */
public class JsonUtil {
    public static <T> T fromJson(String resultString,Class<T> beanClass){
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }
}
