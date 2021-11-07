package com.florence.dev.utils.json;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Florence
 */
@Slf4j
public class JsonUtil {

    private static final String JSON = "json";
    private static final String TEXT = "text";

    /**
     * json转换成对象
     *
     * @param jsonStr json字符串
     * @return Object 封装城的对象
     */
    public static <T> T jsonToObj(Class<T> clazz, String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, clazz);
    }

    /**
     * 对象转换成json
     *
     * @param obj 转换的对象
     * @return json字符串
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    /**
     * 对象转换成json
     *
     * @param obj 转换的对象
     * @return json字符串
     */
    public static String objToJsonNotIncludeNull(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }


    public static <T> T mapToObj(Map<String, Object> map, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(mapToJson(map), clazz);
    }

    /**
     * map转json(忽略null)
     *
     * @param map 要转换的map
     * @return 得到的jsonString
     * @throws JsonProcessingException json转化异常
     */
    public static String mapToJsonNotIncludeNull(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }

    /**
     * map转json
     *
     * @param map 要转换的map
     * @return 得到的jsonString
     * @throws JsonProcessingException json转化异常
     */
    public static String mapToJson(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }

    /**
     * json转Map
     *
     * @param json jsonString
     * @return 得到的Map
     * @throws IOException 转化异常
     */
    public static HashMap jsonToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        return mapper.readValue(json, HashMap.class);
    }


    public static <T> void addPropertiesToExistedObjByJsonStr(T data,String string){
        try {
            HashMap wantToAddProperties = jsonToMap(string);
            BeanUtil.copyProperties(wantToAddProperties,data);
        } catch (IOException e) {
            log.error("Json字符串转化失败",e);
        }
    }
}
