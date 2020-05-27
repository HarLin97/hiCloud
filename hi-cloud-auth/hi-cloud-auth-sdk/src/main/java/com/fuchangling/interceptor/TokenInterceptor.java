package com.fuchangling.interceptor;


import com.fuchangling.annotation.IgnoreToken;
import com.fuchangling.constant.ParameterConst;
import com.fuchangling.constant.TestConst;
import com.fuchangling.enums.ApiStatusCode;
import com.fuchangling.exception.ResultException;
import com.fuchangling.utils.ClientInfoUtil;
import com.fuchangling.utils.JwtUtil;
import com.fuchangling.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * token验证拦截
 * 对每个请求都进行token验证，包括
 * 1.是否携带token
 * 2.token的合法性
 * 3.token的时效性（从redis中验证）
 * 4.更新token时效性
 *
 * @author wangzhen
 */
@EnableDubbo
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenInterceptor implements HandlerInterceptor {

    private final RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 判断请求映射的方式是否忽略权限验证
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(IgnoreToken.class)) {
            return true;
        }

        // 获取请求对象头部token数据
        String token = JwtUtil.getToken(request);

        // 没有获取到token数据
        if (StringUtils.isEmpty(token)) {
            throw new ResultException(ApiStatusCode.NO_TOKEN);
        }
        //验证token是否合法
        boolean flag = JwtUtil.verifyToken(token);
        if (!flag) {
            throw new ResultException(ApiStatusCode.TOKEN_ERROR);
        }
        //判断当前请求是否是由单元测试发起的请求
        if (token.equals(TestConst.TEST_ACCESS_TOKEN_VALUE)) return true;

        //根据userId+ip确定同一客户端、同一用户
        String key = ParameterConst.PREFIX_USER_TOKEN + JwtUtil.getSubject(token) + ClientInfoUtil.getIpAddress(request);
        //验证登录用户是否过期（redis中的userId+ip）
        if (!redisUtil.hasKey(key)) {
            throw new ResultException(ApiStatusCode.TOKEN_TIMEOUT);
        }
//        //验证用户状态是否正常，如被强制下线
//        Map<String, UserDTO> userMap = new HashMap();
//        if(redisUtil.hasKey(key)){
//            userMap = (Map<String,UserDTO>)redisUtil.get(key);
//        }
//        //根据userId+appCode确定同一用户登录不同的子系统，存在多个token同时可用的情况
//        String userKey = JwtUtil.getSubject(token)+JwtUtil.getAppCode(token);
//        UserDTO userDTO = userMap.get(userKey);
//        //验证用户有没有退出当前子系统
//        if(userDTO == null){
//            throw new ResultException(ApiStatusCode.LOGOUT_APP);
//        }
//        //验证用户有没有被强制踢出
//        if(userDTO.getKickOut()){
//            throw new ResultException(ApiStatusCode.KICK_OUT);
//        }

        //刷新redis中在线用户的过期时间
        redisUtil.expire(key, JwtUtil.EXPIRE_TIME / 1000);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
