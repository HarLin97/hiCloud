package com.fuchangling.utils;

import com.fuchangling.constant.ParameterConst;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpServlet工具类，处理一些常用的参数获取
 *
 * @author wangzhen
 * @date 2019-11-28
 */
public class HttpServletUtil {
    /**
     * 获取ServletRequestAttributes对象
     */
    public static ServletRequestAttributes getServletRequest() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取HttpServletRequest对象
     */
    public static HttpServletRequest getRequest() {
        return getServletRequest().getRequest();
    }

    /**
     * 获取HttpServletResponse对象
     */
    public static HttpServletResponse getResponse() {
        return getServletRequest().getResponse();
    }

    /**
     * 获取请求参数
     */
    public static String getParameter(String param) {
        return getRequest().getParameter(param);
    }

    /**
     * 获取请求参数，带默认值
     */
    public static String getParameter(String param, String defaultValue) {
        String parameter = getRequest().getParameter(param);
        return StringUtils.isEmpty(parameter) ? defaultValue : parameter;
    }

    /**
     * 将request中的参数转为map集合
     * 2017年9月11日
     *
     * @return author:wangzhen
     */
    public static Map<String, Object> getRequestParamsMap() {
        HashMap<String, Object> requestParamsMap = new HashMap<String, Object>();
        Enumeration paramNames = getRequest().getParameterNames();
        String token = JwtUtil.getToken(getRequest());
        requestParamsMap.put(ParameterConst.ACCESS_TOKEN, token);
        requestParamsMap.put(ParameterConst.CURRENT_LOGIN_USER_ID, JwtUtil.getSubject(token));
        requestParamsMap.put(ParameterConst.CURRENT_LOGIN_APP_CODE, JwtUtil.getAppCode(token));
        requestParamsMap.put(ParameterConst.CURRENT_LOGIN_APP_ID, JwtUtil.getAppId(token));
        while (paramNames != null && paramNames.hasMoreElements()) {
            Object paramName = paramNames.nextElement();
            if (paramName != null) {
                Object paramValue = getRequest().getParameterValues(paramName.toString());
                requestParamsMap.put(paramName.toString(), ((String[]) paramValue)[0]);
            }
        }
        return requestParamsMap;
    }

    /**
     * 获取请求参数转换为int类型
     */
    public static Integer getParameterInt(String param) {
        return Integer.valueOf(getRequest().getParameter(param));
    }

    /**
     * 获取请求参数转换为int类型，带默认值
     */
    public static Integer getParameterInt(String param, Integer defaultValue) {
        return Integer.valueOf(getParameter(param, String.valueOf(defaultValue)));
    }
}
