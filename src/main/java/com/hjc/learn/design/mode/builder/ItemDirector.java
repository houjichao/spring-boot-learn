package com.hjc.learn.design.mode.builder;

/**
 * 第三步：创建我们的导演类。指导我们怎么去创建对象，这个我们是可以简化的，视具体使用场景确定吧！
 *
 * @author houjichao
 */
public class ItemDirector {

    private ItemBuilder itemBuilder;

    public ItemDirector(ItemBuilder itemBuilder) {
        this.itemBuilder = itemBuilder;
    }

    public Item normalConstruct(String itemName, Integer type) {
        itemBuilder.buildNormal(itemName, type);
        return itemBuilder.getResult();
    }

    public Item cardConstruct(String itemName, Integer type, String code) {
        itemBuilder.buildCard(itemName, type, code);
        return itemBuilder.getResult();
    }

    public Item videoConstruct(String itemName, Integer type, String url) {
        itemBuilder.buildVideo(itemName, type, url);
        return itemBuilder.getResult();
    }
}
