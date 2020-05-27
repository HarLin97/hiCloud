package com.fuchangling.annotation;

import java.lang.annotation.*;

/**
 * 忽略token验证，当方法添加该注解时系统不会对该方法进行token验证
 *
 * @author harlin
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreToken {

}
