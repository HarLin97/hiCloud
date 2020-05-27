package com.fuchangling.enums;

import lombok.Getter;


/**
 * 菜单类型枚举类
 *
 * @author wangzhen
 */

@Getter
public enum MenuTypeEnum {
    /**
     * 菜单
     */
    MENU("0", "菜单"),
    /**
     * 按钮
     */
    BUTTON("9", "按钮");

    private String code;

    private String message;

    MenuTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

