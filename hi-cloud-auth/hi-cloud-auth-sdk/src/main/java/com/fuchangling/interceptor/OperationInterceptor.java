package com.fuchangling.interceptor;

import com.fuchangling.utils.ClientInfoUtil;
import com.fuchangling.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 操作日志拦截器
 * 1.对于操作日志的录入操作
 *
 * @author chensi
 */
@Component
@Slf4j
public class OperationInterceptor implements HandlerInterceptor {

    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";

    /**
     * 该方法在处理请求之前进行调用，就是在执行Controller的任务之前。如果返回true就继续往下执行，返回false就放弃执行
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        System.out.println("------preHandle-----------");
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());

        return true;
    }

    /**
     * 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("------postHandle-----------");
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理。
     *
     * @param request
     * @param response
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

        String token = JwtUtil.getToken(request);

        System.out.println("------afterCompletion-----------");
        log.info("请求响应码-->" + response.getStatus());
        int status = response.getStatus();
        log.info("获取客户端ip IP-->" + ClientInfoUtil.getIpAddress(request));
        String logIp = ClientInfoUtil.getIpAddress(request);
        log.info("获取客户端操作系统-->" + ClientInfoUtil.getClientOS(request));
        String logIps = ClientInfoUtil.getIpAddress(request);
        log.info("获取客户端浏览器-->" + ClientInfoUtil.getClientBrowser(request));
        String browser = ClientInfoUtil.getClientBrowser(request);
        log.info("获取请求方法类型 request.getMethod()-->" + request.getMethod());
        String method = request.getMethod();
        log.info("获取请求方法路径 request.getRequestURL()-->" + request.getRequestURL());
        String url = request.getRequestURL().toString();
        log.info("获取请求方法绝对路径 request.getRequestURI()-->" + request.getRequestURI());
        String uri = request.getRequestURI();
        //log.info("获取客户端主机IP request.getRemoteHost()-->"+request.getRemoteHost());
        String host = request.getRemoteHost();
        //log.info("request.getRemotePort()-->"+request.getRemotePort());
        int remotePort = request.getRemotePort();
        log.info("获取服务器地址 request.getLocalAddr()-->" + request.getLocalAddr());
        String localAddr = request.getLocalAddr();
        log.info("request.getLocalName()-->" + request.getLocalName());
        String localName = request.getLocalName();
        log.info("request.getQueryString()-->" + request.getQueryString());
        String queryString = request.getQueryString();

        if (StringUtils.isNotEmpty(token)) {

           /* log.info("userId-->" + JwtUtil.getSubject(token));
            String userId = JwtUtil.getSubject(token);
            log.info("appCode-->" + JwtUtil.getAppCode(token));
            String appCode = JwtUtil.getAppCode(token);
            log.info("appId-->" + JwtUtil.getAppId(token));
            String appId = JwtUtil.getAppId(token);*/

            //当前时间
            long currentTime = System.currentTimeMillis();
            log.info("当前时间-->" + currentTime);

            //请求开始时间
            long time = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
            log.info("请求时间-->" + time);
            long lostTime = currentTime - Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
            log.info("请求耗时-->" + ((double) lostTime / 1000) + "s");

            if (o instanceof HandlerMethod) {
                HandlerMethod h = (HandlerMethod) o;
                log.info("执行的类为-->" + h.getMethod().getDeclaringClass());
                String zxl = h.getMethod().getDeclaringClass().toString();
                log.info("你所执行的系统模块-->" + h.getBeanType().getAnnotation(Api.class).tags()[0]);
                String className = h.getBeanType().getAnnotation(Api.class).tags()[0];
                log.info("用户想执行的操作是-->" + Objects.requireNonNull(h.getMethodAnnotation(ApiOperation.class)).value());
                String meathName = Objects.requireNonNull(h.getMethodAnnotation(ApiOperation.class)).value();
                //判断后执行操作...

            }
        }

    }

}
