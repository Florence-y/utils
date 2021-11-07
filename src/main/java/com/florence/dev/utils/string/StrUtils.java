package com.florence.dev.utils.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author Derek Xu
 * @CreateDate Jul 19, 2016
 */
public class StrUtils {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)  

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String intArrayToStr(Integer[] array) {
        if (array.length == 0)
            return null;
        String tagsStr = Arrays.toString(array);
        tagsStr = StringUtils.substring(tagsStr, 1, tagsStr.length() - 1);
        return StringUtils.replace(tagsStr, " ", "");
    }

    /**
     * levels的格式为"[1,2,3,4,5]"
     *
     * @param levels
     * @return
     */
    public static String getLevelsString(String levels) {
        levels = StringUtils.substring(levels, 1, levels.length() - 1);
        if (StringUtils.isBlank(levels))
            return null;
        return levels;
    }

    /**
     * 是否有非正确字符特殊字符
     *
     * @param str
     * @return
     */
    private static final String REG = "[a-zA-Z0-9-]*";

    public static boolean checkStr(String str) {
        if (str == null) return false;
        // 只允许字母和数字
        return str.matches(REG);
    }

    public static String stringFilter(String str) {
        if (str == null) return null;
        // 只允许字母和数字
        Pattern p = Pattern.compile(REG);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 去除首尾空白符号
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (str == null) return null;
        String result = "";
        if (null != str && !"".equals(str)) {
            result = str.replaceAll("^[\\s*]*", "").replaceAll("[\\s*]*$", "");
        }
        return result;
    }
}
