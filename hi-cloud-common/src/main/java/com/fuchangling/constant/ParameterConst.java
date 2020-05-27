package com.fuchangling.constant;

/**
 * 系统多处需要使用的一些参数常量
 *
 * @author wangzhen
 */
public class ParameterConst {

    /**
     * 分页列表sql集合
     */
    public static final String SQL_MAP = "sqlMap";
    /**
     * 分页列表sql语句
     */
    public static final String PAGE_SQL = "pageSql";
    /**
     * 分页列表sql语句 指定的参数
     */
    public static final String SQL_PARA = "sqlPara";
    /**
     * 根据分页列表sql语句查出的结果集 转换的VO对象
     */
    public static final String SQL_VO = "sqlVo";


    /**
     * 接口调用时head中携带的token参数名
     */
    public static final String ACCESS_TOKEN = "Access-Token";

    /**
     * token存入redis中的key值前缀
     */
    public static final String PREFIX_USER_TOKEN = "PREFIX_USER_TOKEN_";

    /**
     * 定义系统登陆用户的用户ID参数名
     */
    public static final String CURRENT_LOGIN_USER_ID = "currentLoginUserId";

    /**
     * 定义当前登陆系统的系统ID参数名
     */
    public static final String CURRENT_LOGIN_APP_ID = "currentLoginAppId";

    /**
     * 定义当前登陆系统的系统编码参数名
     */
    public static final String CURRENT_LOGIN_APP_CODE = "currentLoginAppCode";
}
