package com.hjc.learn.design.mode.builder;

/**
 * 创建我们的抽象建造者类。这里面我们看下有三个抽象方法，来确定不同的商品类型，我们调用不同的方法，达到解偶的思想
 *
 * @author houjichao
 */
public abstract class ItemBuilder {
    /**
     * 创建产品对象
     */
    protected Item item = new Item();

    /**
     * 创建普通产品
     *
     * @param itemName 商品名称
     * @param type     商品类型
     */
    public abstract void buildNormal(String itemName, Integer type);

    /**
     * 创建电子卡券类
     *
     * @param itemName 商品名称
     * @param type     商品类型
     * @param code     电子卡券码
     */
    public abstract void buildCard(String itemName, Integer type, String code);

    /**
     * 创建视频类
     *
     * @param itemName 商品名称
     * @param type     商品类型
     * @param url      视频链接
     */
    public abstract void buildVideo(String itemName, Integer type, String url);


    public Item getResult() {
        return item;
    }
}
