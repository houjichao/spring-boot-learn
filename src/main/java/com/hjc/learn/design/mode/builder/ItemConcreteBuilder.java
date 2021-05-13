package com.hjc.learn.design.mode.builder;

/**
 * 创建具体建造者类。对**抽象建造者类**的抽象方法进行实现赋值，达到我们所需要的结果。
 *
 * @author houjichao
 */
public class ItemConcreteBuilder extends ItemBuilder {
    @Override
    public void buildNormal(String itemName, Integer type) {
        item.setItemName(itemName);
        item.setType(type);
    }

    @Override
    public void buildCard(String itemName, Integer type, String code) {
        item.setItemName(itemName);
        item.setType(type);
        item.setCode(code);
    }

    @Override
    public void buildVideo(String itemName, Integer type, String url) {
        item.setItemName(itemName);
        item.setType(type);
        item.setUrl(url);
    }
}
