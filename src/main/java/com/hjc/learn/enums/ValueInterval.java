package com.hjc.learn.enums;

/**
 * 值区间判断
 *
 * @author houjichao
 */
public enum ValueInterval {

    /**
     * 在区间内
     */
    CONTAIN(0, "在区间内"),

    /**
     * 大于最大值
     */
    GREATER_THAN_MAX(1, "大于最大值"),

    /**
     * 小于最小值
     */
    LESS_THAN_MIN(-1, "小于最小值");

    private Integer code;
    private String desc;

    ValueInterval(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
