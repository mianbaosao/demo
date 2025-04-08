package org.example.suanfa.project.codeVerse.excel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xuehui.
 */
@Slf4j
public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectMapper SNAKE_OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SNAKE_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SNAKE_OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SNAKE_OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    private static final JavaType javaTypeMapStringObject;
    private static final JavaType javaTypeMapStringMap;
    private static final JavaType javaTypeMapStringString;

    static {
        javaTypeMapStringObject = constructParametricType(Map.class, String.class, Object.class);
        javaTypeMapStringMap = constructParametricType(Map.class, String.class, Map.class);
        javaTypeMapStringString = constructParametricType(Map.class, String.class, String.class);
    }

    private static JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametrizedType(parametrized, parametrized, parameterClasses);
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public static ObjectMapper getSnakeInstance(){
        return SNAKE_OBJECT_MAPPER;
    }

    public static String writeValueAsString(Object value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.warn("[JsonUtils#writeValueAsString] 序列化json失败, value={}", value, e);
            return null;
        }
    }

    public static String writeValueAsSnakeString(Object value) {
        try {
            return getSnakeInstance().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.warn("[JsonUtils#writeValueAsSnakeString] 序列化json失败, value={}", value, e);
            return null;
        }
    }

    public static JsonNode findValue(String jsonStr, String key) {
        try {
            JsonNode rootNode = getInstance().readTree(jsonStr);
            return rootNode == null ? null : rootNode.findValue(key);
        } catch (IOException e) {
            log.warn("[JsonUtils#findValue] 反序列化json失败, jsonStr={}, key={}", jsonStr, key, e);
            return null;
        }
    }

    public static JsonNode findSnakeValue(String jsonStr, String key) {
        try {
            JsonNode rootNode = getSnakeInstance().readTree(jsonStr);
            return rootNode == null ? null : rootNode.findValue(key);
        } catch (IOException e) {
            log.warn("[JsonUtils#findSnakeValue] 反序列化json失败, jsonStr={}, key={}", jsonStr, key, e);
            return null;
        }
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public static <T> List<T> findList(String jsonStr) {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            return (List<T>) getInstance().readValue(jsonStr, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            log.warn("[JsonUtils#findList] 反序列化json失败, jsonStr={}", jsonStr, e);
            return null;
        }

    }

    public static <T> List<T> findList(String jsonStr, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            JavaType javaType = getInstance().getTypeFactory().constructCollectionType(List.class, clazz);
            return getInstance().readValue(jsonStr, javaType);
        } catch (Exception e) {
            log.warn("[JsonUtils#findList] 反序列化json失败, jsonStr={}, clazz={}", jsonStr, clazz.getName(), e);
            return null;
        }
    }

    public static <T> List<T> findSnakeList(String jsonStr, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            JavaType javaType = getSnakeInstance().getTypeFactory().constructCollectionType(List.class, clazz);
            return getSnakeInstance().readValue(jsonStr, javaType);
        } catch (Exception e) {
            log.warn("[JsonUtils#findSnakeList] 反序列化json失败, jsonStr={}, clazz={}", jsonStr, clazz.getName(), e);
            return null;
        }
    }


    public static <T> T findObject(String jsonStr, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            return getInstance().readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.warn("[JsonUtils#findObject] 反序列化json失败, jsonStr={}", jsonStr, e);
            return null;
        }
    }

    public static <T> T findSnakeObject(String jsonStr, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return null;
            }
            return getSnakeInstance().readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.warn("[JsonUtils#findSnakeObject] 反序列化json失败, jsonStr={}", jsonStr, e);
            return null;
        }
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(jsonStr, typeReference);
        } catch (IOException e) {
            log.warn("[JsonUtils#readValue] 反序列化json失败, jsonStr={}", jsonStr, e);
            return null;
        }
    }

    public static <T> T readSnakeValue(String jsonStr, TypeReference<T> typeReference) {
        try {
            return getSnakeInstance().readValue(jsonStr, typeReference);
        } catch (IOException e) {
            log.warn("[JsonUtils#readSnakeValue] 反序列化json失败, jsonStr={}", jsonStr, e);
            return null;
        }
    }

    public static Map<String, Object> fromJson(String content) {
        return fromJson(content, javaTypeMapStringObject);
    }


    public static Map<String, Map> fromJsonToMapStringMap(String content) {
        return fromJson(content, javaTypeMapStringMap);
    }

    public static Map<String, String> fromJsonToMapStringString(String content) {
        return fromJson(content, javaTypeMapStringString);
    }

    public static <K, V> Map<K, V> fromJsonToMap(String conetnt, Class<K> key, Class<V> value) {
        return fromJson(conetnt, constructParametricType(Map.class,key, value));
    }

    public static <T> T fromJson(String content, Class<T> valueType) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (Exception ex) {
            log.error("fromJson failed:content={},error={}", content, ex.getMessage());
            return null;
        }
    }

    public static <T> T fromJson(String content, JavaType valueType) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (Exception ex) {
            log.error("fromJson failed:content={},error={}", content, ex.getMessage());
            return null;
        }
    }

    /**********************对象转换成为Json***********************/

    public static String toJson(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (Exception ex) {
            log.error("toJson failed: " + ex.getMessage(), ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> safeConvertObjectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return (Map) obj;
        }
        try {
            return OBJECT_MAPPER.convertValue(obj, javaTypeMapStringObject);
        } catch (Exception ex) {
            log.error("Failed to convert {} to java.util.Map, return null", obj.getClass().getName());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T safeConvertMapToObject(Map<String, Object> map, Class<T> type) {
        if (type == null) {
            log.warn("Target type is required, return null");
            return null;
        }
        if (map == null) {
            return null;
        }
        if (type == map.getClass()) {
            return (T) map;
        }
        try {
            return OBJECT_MAPPER.convertValue(map, type);
        } catch (Exception ex) {
            log.error("Failed to convert java.util.Map to {}, return null", type.getName());
            return null;
        }
    }

}