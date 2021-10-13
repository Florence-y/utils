package com.florence.dev.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jf-platform-api
 * @description: 在原有CopyUtils对BeanUtils.copyProperties进行改进
 * @author: florence
 * @create: 2020-03-30 16:13
 **/
@Slf4j
public class BaseCopyUtils {
    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    /**
     * 将origin对象拷贝到dest对象
     *
     * @param resource    原始对象
     * @param targetClass 目标对象的类型
     * @return 目标类对象
     */
    public static <T> T copy(Object resource, Class<T> targetClass) {
        if (resource != null) {
            try {
                T t = targetClass.newInstance();
                copy(resource, t);
                return t;
            } catch (InstantiationException | IllegalAccessException | BeansException e) {
                log.error("copy error:{}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 将origin类的List映射为dest类的List
     *
     * @param originList 原始类列表
     * @param destClass  目标类class
     */
    public static <K, T> List<K> copyOriginListToDestList(List<T> originList, Class<K> destClass) {
        int size = originList.size();
        List<K> destList = new ArrayList<>(size);
        for (T t : originList) {
            K k;
            try {
                k = destClass.newInstance();
                copy(t, k);
                destList.add(k);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("copyOriginListToDestList error:{}", e.getMessage());
            }
        }
        return destList;
    }
}
