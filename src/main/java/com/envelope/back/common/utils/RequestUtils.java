package com.envelope.back.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

    /**
     * 获取session
     */
    public static HttpSession getHttpSession() {
        return getHttpRequest().getSession();
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getHttpRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }

}
