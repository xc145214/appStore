
package com.appStore.common.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 *  JSON序列化和反序列化辅助方法集合。
 *
 *  @author xiachuan at 2016/7/7 19:46。
 */

public final class JsonUtils {
    public JsonUtils() {
    }
    private static final ObjectMapper mapper = new ObjectMapper();


    /**
     * {@link java.util.Map Map}-><a href="http://www.json.org/">JSON</a>格式的字符串。
     *
     * @param target {@link java.util.Map Map}实例。
     * @param <T> 泛型声明。
     * @return JSON格式字符串。
     * @throws java.io.IOException {@link com.fasterxml.jackson.core.JsonFactory#createGenerator(java.io.Writer)}可能抛出的例外。
     * @see com.fasterxml.jackson.core.JsonFactory#createGenerator(java.io.Writer)
     * @see com.fasterxml.jackson.databind.ObjectMapper#writeValue(com.fasterxml.jackson.core.JsonGenerator, Object)
     */
    public static <T> String toJSON(Object target) throws IOException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(new JsonFactory().createGenerator(writer), target);
        return writer.toString();
    }

    /**
     * <a href="http://www.json.org/">JSON</a>格式的字符串->{@code <T>}对象。
     *
     * @param JSON JSON格式字符串。
     * @param clazz 要转换对象的类类型。
     * @param <T> 泛型声明。
     * @return 指定类类型的对象实例。
     * @throws java.io.IOException {@link com.fasterxml.jackson.databind.ObjectMapper#readValue(String, Class)}可能抛出的例外。
     */
    public static <T> T toObject(String JSON, Class<T> clazz) throws IOException {
        return mapper.readValue(JSON, clazz);
    }
}

