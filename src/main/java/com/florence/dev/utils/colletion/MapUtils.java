package com.florence.dev.utils.colletion;

import java.util.Map;

/**
 * @author wuyanzhen
 */
public class MapUtils {

    public static <K,V> void printKeys(Map<K,V> map){
        map.forEach((k,v)->System.out.println("key:"+k));
    }
    public static <K,V> void printValues(Map<K,V> map){
        map.forEach((k,v)->System.out.println("value:"+v));
    }
    public static <K,V> void printKeysAndValues(Map<K,V> map){
        map.forEach((k,v)->System.out.println("key:"+k+" value:"+v));
    }
}
