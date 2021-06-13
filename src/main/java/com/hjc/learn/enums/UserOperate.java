package com.hjc.learn.enums;

import lombok.Getter;

/**
 * 用户操作枚举
 *
 * @author houjichao
 */
@Getter
public enum UserOperate {

    /**
     * 新增
     */
    ADD("add", 0, "新增"),

    /**
     * 更新
     */
    UPDATE("update", 1, "更新"),


    /**
     * 删除
     */
    DELETE("delete", 2, "删除");


    private String name;

    private Integer value;

    private String desc;

    UserOperate(String name, Integer value, String desc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    public static UserOperate getByValue(Integer value) {
        for (UserOperate e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

}
