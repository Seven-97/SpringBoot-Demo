package com.seven.controllervalidation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * json转换工具类
 */
@Slf4j
public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //  将对象转换为JSON字符串
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("write to json string error:{}", object, e);
            return null;
        }
    }

    //  将JSON字符串转换回对象
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("write to json string error:{}", json, e);
            return null;
        }
    }

}

