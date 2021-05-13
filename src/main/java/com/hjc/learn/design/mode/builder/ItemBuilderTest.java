package com.hjc.learn.design.mode.builder;

import com.alibaba.fastjson.JSONObject;

/**
 * 建造者模式
 *
 * @author houjichao
 */
public class ItemBuilderTest {

    public static void main(String[] args) {
        ItemBuilder builder = new ItemConcreteBuilder();
        ItemDirector director = new ItemDirector(builder);
        Item item = director.normalConstruct("普通商品", 1);
        System.out.println(JSONObject.toJSONString(item));
        Item item1 = director.cardConstruct("电子卡券类商品", 2, "123456");
        System.out.println(JSONObject.toJSONString(item1));
        //剔除导演雷
        ItemBuilder builder1 = new ItemConcreteBuilder();
        builder1.buildNormal("普通商品1", 1);
        Item item2 = builder1.getResult();
        System.out.println(JSONObject.toJSONString(item2));
    }
}
