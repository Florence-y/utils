package com.florence.dev.utils.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author KingsHunter
 * @createDate August 17,2016
 *
 */
public class HeaderUtils {

	
	/**
     * 获取客户端ip地址
     * 
     * @return
     */
    public static String getIp(HttpServletRequest request) {
    	// 优先从x-forwarded-for获取
    	String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
        
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip.split(",")[0];
		}
        return null;
    }
    
    /**
     * 获取user-agent
     * 
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
    	String ua = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(ua))
        	return ua;
        return StringUtils.defaultIfEmpty(ua, StringUtils.EMPTY);
    }
    
    /**
     * 来源页面
     * 
     * @return
     */
    public static String getRequesReferUrl(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (StringUtils.isNotBlank(referer))
        	return referer;
        return StringUtils.defaultIfEmpty(referer, StringUtils.EMPTY);
    }
    
}
