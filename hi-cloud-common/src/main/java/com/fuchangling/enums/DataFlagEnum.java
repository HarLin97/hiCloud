package com.fuchangling.enums;

import lombok.Getter;


/**
 * 数据状态枚举类
 * 数据状态定义在实体类统一父类中，默认所有表都有该字段
 *
 * @author wangzhen
 */

@Getter
public enum DataFlagEnum {
    /**
     * 正常的数据
     */
    OK(1, "启用"),
    /**
     * 被冻结的数据，不可用
     */
    FREEZE(0, "冻结"),
    /**
     * 数据已被删除,逻辑删除
     */
    DELETE(-1, "删除");

    private int code;

    private String message;

    DataFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

