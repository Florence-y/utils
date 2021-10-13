package com.florence.dev.utils.bean;

import org.apache.commons.lang3.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 
 * @author KingsHunter
 * @createDate July 1st,2016
 * 
 */
public final class ConvertUtils {

	public static Map<?, String> javaBean2StringValueMap(Object bean)
			throws IntrospectionException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		if (bean != null) {
			Class<? extends Object> clazz = bean.getClass();
			Map<Object, String> returnMap = new HashMap<Object, String>();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			int propertyDescriptorsLen = propertyDescriptors.length;
			PropertyDescriptor descriptor = null;
			String propertyName = null;
			Method readMethod = null;
			for (int i = 0; i < propertyDescriptorsLen; i++) {
				descriptor = propertyDescriptors[i];
				propertyName = descriptor.getName();
				if (!"class".equals(propertyName)) {
					readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result.toString());
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
			return returnMap;
		}
		return Collections.emptyMap();
	}

	public static Object map2JavaBean(Class<? extends Object> clazz, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		if (clazz != null && map != null) {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			Object obj = clazz.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			int propertyDescriptorsLen = propertyDescriptors.length;
			PropertyDescriptor descriptor = null;
			String propertyName = null;
			for (int i = 0; i < propertyDescriptorsLen; i++) {
				descriptor = propertyDescriptors[i];
				propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);

					if (descriptor.getPropertyType().equals(boolean.class))
						value = Boolean.valueOf(value.toString());

					if (descriptor.getPropertyType().equals(int.class))
						value = Integer.valueOf(value.toString());

					if (descriptor.getPropertyType().equals(long.class))
						value = Long.valueOf(value.toString());

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
			return obj;
		}
		return null;
	}

	public static String[] getJavaBeanProperties(Class<? extends Object> clazz)
			throws IntrospectionException, InstantiationException,
			IllegalAccessException {
		if (clazz != null) {
			String[] propertyNames = {};
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			int propertyDescriptorsLen = propertyDescriptors.length;
			PropertyDescriptor descriptor = null;
			String propertyName = null;
			for (int i = 0; i < propertyDescriptorsLen; i++) {
				descriptor = propertyDescriptors[i];
				propertyName = descriptor.getName();
				if (!"class".equals(propertyName)) {
					propertyNames = (String[]) ArrayUtils.add(propertyNames,
							propertyName);
				}
			}
			return propertyNames;
		}
		return null;
	}

	public static <T> List<Object> getObjectList(T... params) throws Exception {
		List<Object> objs = new ArrayList<Object>();
		if (params.length == 1) {
			if (params[0].getClass().isArray()) {
				Class<?> type = params[0].getClass().getComponentType();
				if (type == int.class) {
					int[] intArray = (int[]) params[0];
					for (int item : intArray) {
						objs.add(item);
					}
				} else if (type == long.class) {
					long[] longArray = (long[]) params[0];
					for (long item : longArray) {
						objs.add(item);
					}
				} else if (type == float.class) {
					float[] floatArray = (float[]) params[0];
					for (float item : floatArray) {
						objs.add(item);
					}
				} else if (type == double.class) {
					double[] doubleArray = (double[]) params[0];
					for (double item : doubleArray) {
						objs.add(item);
					}
				} else if (type == String.class) {
					String[] stringArray = (String[]) params[0];
					for (String item : stringArray) {
						objs.add(item);
					}
				} else {
					throw new Exception("error: " + params[0]);
				}
			} else if (params[0] instanceof List) {
				// 泛型类型处理
				List<?> li = (List<?>) params[0];
				if (li.size() == 0) {
					throw new Exception("error: " + params[0]);
				}
				for (int i = 0; i < li.size(); i++) {
					objs.add(li.get(i));
				}
			} else {
				objs.add(params[0]);
			}
		} else if (params.length > 1) {
			for (Object obj : params) {
				objs.add(obj);
			}
		} else {
			objs = null;
		}
		return objs;
	}
}
