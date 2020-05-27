package com.fuchangling.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.fuchangling.constant.ParameterConst;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author wangzhen
 * @date 2019-12-28
 */
@Slf4j
public class JwtUtil {

    /**
     * 加密私钥
     */
    public static final String SECRET = "secret1234567890";
    /**
     * 过期时间 30分钟
     */
    public static final long EXPIRE_TIME = 1000 * 60 * 30;

    /**
     * 前端Token 用于自动登录,1天时间
     */
    public static final long EXPIRE_TIME_OUT = 1000 * 60 * 60 * 24;

    /**
     * @param appCode
     * @param userId
     * @param loginName
     * @return
     */
    public static String createToken(String appCode, String userId, String loginName, String type, String appId) {

        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 随机Claim
        String random = StringUtil.getRandomString(6);
        Algorithm algorithm = getSecret(SECRET, random);
        // 创建JwtToken对象
        String token = "";
        token = JWT.create()
                // 用户名
                .withSubject(userId)
                // 发布时间
                .withIssuedAt(new Date())
                // 过期时间
                .withExpiresAt(date)
                // 自定义随机Claim
                .withClaim("random", random)
                .withClaim("appCode", appCode)
                .withClaim("userId", userId)
                .withClaim("loginName", loginName)
                .withClaim("type", type)
                .withClaim("appId", appId)
                .sign(algorithm);

        return token;
    }

    /**
     * 获取当前token中的用户名
     *
     * @param token
     * @return
     */
    public static String getSubject(String token) {
        return JWT.decode(token).getSubject();
    }

    /**
     * 获取当前token中的用户名
     *
     * @param token
     * @return
     */
    public static String getLoginName(String token) {
        return JWT.decode(token).getClaim("loginName").asString();
    }

    /**
     * 获取当前token中的type
     *
     * @param token
     * @return
     */
    public static String getType(String token) {
        return JWT.decode(token).getClaim("type").asString();
    }

    /**
     * 获取当前token中的登录子系统编码
     *
     * @param token
     * @return
     */
    public static String getAppCode(String token) {
        return JWT.decode(token).getClaim("appCode").asString();
    }

    /**
     * 获取当前token中的登录子系统标识
     *
     * @param token
     * @return
     */
    public static String getAppId(String token) {
        return JWT.decode(token).getClaim("appId").asString();
    }

    /**
     * 获取当前token中的key的值
     *
     * @param token
     * @param key
     * @return
     */
    public static String getValue(String token, String key) {
        return JWT.decode(token).getClaim(key).asString();
    }

    /**
     * 验证JwtToken
     *
     * @param token JwtToken数据
     * @return true 验证通过
     * @throws TokenExpiredException    Token过期
     * @throws JWTVerificationException 令牌无效（验证不通过）
     */
    public static boolean verifyToken(String token) {
        try {
            String random = JWT.decode(token).getClaim("random").asString();
            JWTVerifier jwtVerifier = JWT.require(getSecret(SECRET, random)).build();
            jwtVerifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            //不以jwt的过期时间作为判定依据，以redis中存储为过期时间
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取Claim map集合
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaimsFromToken(String token) {
        return JWT.decode(token).getClaims();
    }


    /**
     * 获取jwt过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpirationDateFromToken(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    public static Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        log.info("过期时间：{}" + expiration);
        log.info("是否过期：{}" + expiration.before(new Date()));
        return expiration.before(new Date());
    }

    /**
     * 判断token过期是否超过一天
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    public static Boolean isTokenExpiredOverOneDay(String token) {
        Date expiration = getExpirationDateFromToken(token);
        Date expirationDate = new Date(expiration.getTime() + EXPIRE_TIME_OUT - EXPIRE_TIME);
        return expirationDate.before(new Date());
    }

    /**
     * 获取请求头中携带的token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        // 获取请求对象头部token数据
        return request.getHeader(ParameterConst.ACCESS_TOKEN);
    }


    /**
     * 生成Secret混淆数据
     * 密钥+混淆盐+随机串
     */
    private static Algorithm getSecret(String secret, String random) {
        String salt = "iop123";
        return Algorithm.HMAC256(secret + salt + random);
    }
}
