package com.glg.baselib.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.util.List;

public class JsonUtil {

    /**
     * 将json转为bean
     * @param json 目标json串
     * @param classOfT  目标bean的class
     * @param <T> 目标bean
     * @return 返回值
     */
    public static <T> T object(String json, Class<T> classOfT) {
        return JSON.parseObject(json, classOfT);
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts =  JSON.parseArray(jsonString, clazz);
        return ts;
    }

    /**
     * 将bean转为json字符串
     * @param object bean 对象
     * @return 返回值
     */
    public static String string(Object object) {
        try {
            return JSON.toJSONString(object);
        }catch (Exception e){
            Logger.e(e.getMessage());
        }
        return "解析错误";
    }


}
