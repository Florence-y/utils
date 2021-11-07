package com.florence.dev.utils.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuyanzhen
 */
public class CookieUtils {


    public static String getCookieValue(HttpServletRequest request, String key) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    if (StringUtils.isNotBlank(cookie.getValue())) {
                        value = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return value;
    }

    public static boolean existsCookie(HttpServletRequest request, String key) {
        boolean exists = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    public static void addCookieValue(HttpServletResponse response, String key, String value, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(-1);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static void addCookieValue(HttpServletResponse response, String key, String value, int seconds, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(seconds);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static void deleteCookieValue(HttpServletRequest request, HttpServletResponse response, String key, String domain) {
        boolean exists = existsCookie(request, key);
        if (exists) {
            Cookie cookie = new Cookie(key, null);
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
