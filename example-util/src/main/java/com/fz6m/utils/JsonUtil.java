package com.fz6m.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class JsonUtil {

    private static final SerializeConfig config;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            // SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            // SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /** Object TO Json String 字符串输出 */
    public static String toJSON(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            log.error("JsonUtil | method=toJSON() | 对象转为Json字符串 Error！" + e.getMessage(), e);
        }
        return null;
    }


    /** Object TO Json String 字符串输出 */
    public static String toJSONWithDateFormat(Object object) {
        try {
            return JSON.toJSONStringWithDateFormat(object, DATE_FORMAT);
        } catch (Exception e) {
            log.error("JsonUtil | method=toJSONWithDateFormat() | 对象转为Json字符串 Error！" + e.getMessage(), e);
        }
        return null;
    }

    /** Object TO Json String 字符串输出 */
    public static String toJSONLib(Object object) {
        try {
            return JSON.toJSONString(object, config);
        } catch (Exception e) {
            log.error("JsonUtil | method=toJSONLib() | 对象转为Json字符串 Error！" + e.getMessage(), e);
        }
        return null;
    }

    /** Object TO Json String  */
    public static String toJsonFeatures(Object object) {
        try {
            return JSON.toJSONString(object, features);
        } catch (Exception e) {
            log.error("JsonUtil | method=toJsonFeatures() | 对象转为Json字符串 Error！" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将 json 字符串转为 Object 实例
     */
    public static Object parse(String json) {
        try {
            return JSON.parse(json);
        } catch (Exception e) {
            log.error("JsonUtil | method=parse() | 将 json 字符串转为 Object 实例 Error！" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json字符串转为指定类型的实例
     */
    public static <T> T parse(String json, Class<T> cls) {
        try {
            return JSON.parseObject(json, cls);
        } catch (Exception e) {
            log.error("JsonUtil | method=parse() | 将json字符串转为指定类型的实例 Error！" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Json 转为 Map
     */
    public static Map<?, ?> toMap(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            log.error("JsonUtil | method=toMap() | Json 转为Map Error!" + e.getMessage(), e);
        }
        return null;
    }

    /** 转换为数组 Object */
    public static Object[] toArray(String text) {
        try {
            return toArray(text, null);
        } catch (Exception e) {
            log.error("JsonUtil | method=toArray() | 将json格式的数据转换为数组 Object  Error！" + e.getMessage(), e);
        }
        return null;
    }

    /** 转换为数组 （可指定类型） */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz).toArray();
        } catch (Exception e) {
            log.error("JsonUtil | method=toArray() | 将json格式的数据转换为数组 （可指定类型）   Error！" + e.getMessage(), e);
        }
        return null;
    }

    /** Json 转为 Java Bean */
    public static <T> T toBean(String text, Class<T> clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            log.error("JsonUtil | method=toBean() | Json 转为  Jave Bean  Error！" + e.getMessage(), e);
        }
        return null;
    }

    /** Json 转 List,Class 集合中泛型的类型，非集合本身，可json-lib兼容的日期格式 */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception e) {
            log.error("JsonUtil | method=toList() | Json 转为   List {},{}" + e.getMessage(), e);
        }
        return null;
    }

    /** 从json字符串获取指定key的字符串 */
    public static Object getValueFromJson(final String json, final String key) {
        try {
            if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
                return null;
            }
            return JSON.parseObject(json).getString(key);
        } catch (Exception e) {
            log.error("JsonUtil | method=getStringFromJson() | 从json获取指定key的字符串 Error！" + e.getMessage(), e);
        }
        return null;
    }


}
