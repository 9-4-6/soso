package com.gz.soso.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 初始化配置
        OBJECT_MAPPER.registerModule(new JavaTimeModule()); // 支持Java8时间类型
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 日期不写为时间戳
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 日期格式
        // 其他配置按需添加
    }

    /**
     * 对象转JSON字符串
     *
     * @param object 要转换的对象
     * @return JSON字符串
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("对象转JSON字符串失败", e);
        }
    }

    /**
     * JSON字符串转对象
     *
     * @param json  JSON字符串
     * @param clazz 目标对象类型
     * @param <T>   泛型类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON字符串转对象失败", e);
        }
    }

    /**
     * JSON字符串转复杂对象（如List、Map等）
     *
     * @param json          JSON字符串
     * @param typeReference 目标类型引用，如 new TypeReference<List<User>>() {}
     * @param <T>           泛型类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("JSON字符串转复杂对象失败", e);
        }
    }
}
