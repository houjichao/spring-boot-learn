package com.hjc.learn.design.mode.builder;

import lombok.Data;

/**
 * 普通方式定义商品--普通实物商品、电子卡券商品、虚拟视频学习商品
 *
 * @author houjichao
 */
@Data
public class Item {

    /**
     * 必填 商品名称
     */
    private String itemName;

    /**
     * 必填 商品类型
     */
    private Integer type;

    /**
     * 卡号 卡券类必填
     */
    private String code;

    /**
     * 链接 视频类必填
     */
    private String url;
}
