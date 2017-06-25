package com.liang.graduationProject.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/17.
 */
public class FastJsonUtils {

    /**
     * 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getBean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将JavaBean转为String
     *
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean) {
        String text = null;
        try {
            text = JSON.toJSONString(bean);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 将JavaBean转为json
     *
     * @param bean
     * @return
     */
    public static JSONObject beanToJson2(Object bean) {
        JSONObject objectJson = null;
        try {
            String text  = JSON.toJSONString(bean);
            objectJson  = JSON.parseObject(text);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     * @param text
     * @return
     */
    public static JSONObject textToJson(String text) {
        JSONObject json   = null;
        try {
            json = JSON.parseObject(text);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return json;
    }

    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = null;
        try {
            m = JSONObject.parseObject(s);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return m;
    }

    /**
     * 将map转化为string
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = null;
        try {
            s = JSONObject.toJSONString(m,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 将list转化为string
     * @param o
     * @return
     */
    public static String listToJson(List o) {
        String s = null;
        try {
            s = JSON.toJSONString(o);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 将String数组转回数组
     * @param str
     * @return
     * */
    public static JSONArray stringToJSONArray(String str){
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONArray.parseArray(str);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 将list转化为JSONArray
     * @param o
     * @return
     */
    public static JSONArray listToJSONArray(List o) {
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONArray.parseArray(JSON.toJSONString(o));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 处理对象为null
     * @param object
     * @param features
     * @return
     */
    public static String toJSONString(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        String s;
        JSONSerializer serializer = new JSONSerializer(out);
        SerializerFeature arr$[] = features;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            SerializerFeature feature = arr$[i$];
            serializer.config(feature, true);
        }
        serializer.getValueFilters().add(new ValueFilter() {
            public Object process(Object obj, String s, Object value) {
                if (null != value) {
                    if (value instanceof java.util.Date) {
                        return String.format("%1$tF %1tT", value);
                    }
                    return value;
                } else {
                    return "";
                }
            }
        });
        serializer.write(object);
        s = out.toString();
        out.close();
        return s;
    }
}
