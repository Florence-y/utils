package com.florence.dev.utils.array;

import java.util.ArrayList;
import java.util.List;

/**
*
* 列表工具类
* 
*/
public class BaseOperUtils {
	
	/**
	 * 对比两字符串列表内容是否一致
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean equals(List<String> list1, List<String> list2) {
		for (String l1 : list1) {
			if (! list2.contains(l1.trim())) {
				return false;
			}
		}
		for (String l2 : list2) {
			if (! list1.contains(l2.trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 复制列表
	 * @param list
	 * @return
	 */
	public static List<String> clone(List<String> list) {
		List<String> cloneList = new ArrayList<String>();
		for (String item : list) {
			cloneList.add(item.trim());
		}
		return cloneList;
	}
	
	/**
	 * 获取新增列表
	 * @param origList 原队列
	 * @param newList 新队列
	 * @return
	 */
	public static List<String> addedItems(List<String> origList, List<String> newList) {
		List<String> addedList = new ArrayList<String>();
		for (String item : newList) {
			if (! origList.contains(item.trim())) {
				addedList.add(item.trim());
			}
		}
		return addedList;
	}
	
	/**
	 * 获取删除列表
	 * @param origList
	 * @param newList
	 * @return
	 */
	public static List<String> deletedItems(List<String> origList, List<String> newList) {
		List<String> deletedList = new ArrayList<String>();
		for (String item : origList) {
			if (! newList.contains(item.trim())) {
				deletedList.add(item.trim());
			}
		}
		return deletedList;
	}
}