package de.telran;

import jdk.internal.util.xml.impl.XMLStreamWriterImpl;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;

public class Main {
/*
     Есть Map<String, Object>, олицетворяющий json. То есть, значениями может быть либо число, либо строка, либо булеан, либо другой Map<String, Object>. Известно, что ключи оформлены в виде snake case, то есть "first_key". Необходимо переделать этот мап таким образом, чтобы ключи стали camelCase. Подсказка: надо проверять value, являются ли они Map, в этом случае рекурсивно вызвать функцию для него
 */


    public static Map<String, Object> snakeToCamelMap(Map<String, Object> map) {


        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            result.put(snakeToCamelString(key), snakeToCamel(value));
        }
        return result;
    }

    public static List<Object> snakeToCamelList(List<Object> list) {

        List<Object> result = new ArrayList<>();
        for (Object o : list) {
            result.add(snakeToCamel(o));
        }
        return result;
    }

    public static Object snakeToCamel(Object obj) {
        if (obj instanceof Map) {
            return snakeToCamelMap((Map<String, Object>) obj);
        } else if (obj instanceof List) {
            return snakeToCamelList((List) obj);
        } else {
            return obj;
        }
    }

    private static String snakeToCamelString(String key) {
        if (key.contains("_")) {
            return  key.replaceFirst("_[a-z]", String.valueOf(Character.toUpperCase(key.charAt(key.indexOf("_") + 1))));
        }
        return key;
    }



}
