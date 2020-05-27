package com.fuchangling.enums;

import lombok.Getter;


/**
 * api接口状态码
 *
 * @author wangzhen
 */

@Getter
public enum ApiStatusCode {


    /**
     * 成功
     **/
    OK(200, "success"),


    /**
     * 认证权限
     */
    NO_ROLE(3001, "没有授权角色!"),
    NO_MENU_ACCESS(3002, "没有系统菜单权限!"),
    NO_FOUND_VOUCHER(3003, "未知的认证方式！"),
    NO_EFFI_VOUCHER(3004, "未找到有效的登录凭证！"),
    NO_USER(3005, "用户不存在！"),
    NO_APP_ACCESS(3006, "无系统访问权限！"),
    NO_TOKEN(3007, "TOKEN为空！"),
    TOKEN_ERROR(3008, "TOKEN错误！"),
    TOKEN_TIMEOUT(3009, "TOKEN过期！"),
    LOGOUT_APP(3010, "用户已退出当前系统！"),
    KICK_OUT(3011, "用户被强制下线！"),


    /**
     * 校验异常
     */
    VAILD_ERROR(4000, "参数校验出错!"),

    /**
     * SQL错误
     */
    SQL_ERROR(4100, "SQL出错!"),

    /**
     * 请求错误
     */
    BAD_REQUEST(4000, "没有授权角色！"),
    NOT_FOUND(4004, "not_found"),
    METHOD_NOT_ALLOWED(4005, "method_not_allowed"),
    MEDIA_TYPE_NOT_ACCEPTABLE(4006, "media_type_not_acceptable"),
    TOO_MANY_REQUESTS(4029, "too_many_requests"),


    /**
     * 业务错误
     */

    INIT_DEPT_ERROR(6001, "初始化机构信息错误！"),

    /**
     * 系统错误
     */
    ERROR(5000, "error"),
    GATEWAY_TIMEOUT(5004, "gateway_timeout"),
    SERVICE_UNAVAILABLE(5003, "service_unavailable");


    private int code;

    private String message;

    ApiStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

