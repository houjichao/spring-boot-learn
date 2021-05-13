package com.hjc.learn.design.mode.builder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 假设我们现在有另外的一种场景，我们复制一个商品时，当没有填写库存时我们默认是0，当用户填写了时我们库存数量不能大于999999999。
 *
 * @author houjichao
 */
@Data
public class CopyItem {
    private String name;

    private Long stock;

    private CopyItem(ItemBuilder itemBuilder) {
        this.name = itemBuilder.name;
        this.stock = itemBuilder.stock;
    }

    public static class ItemBuilder {
        private static final Long DEFAULT_STOCK = 0L;
        private static final Long MAX_STOCK = 999999999L;
        private String name;
        private Long stock = DEFAULT_STOCK;

        public CopyItem build() {
            //再处理一些额外的逻辑
            return new CopyItem(this);
        }

        public ItemBuilder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("参数异常");
            }
            this.name = name;
            return this;
        }

        public ItemBuilder setStock(Long stock) {
            if (stock > MAX_STOCK) {
                throw new IllegalArgumentException("库存数量错误");
            }
            this.stock = stock;
            return this;
        }
    }

    public static void main(String[] args) {
        CopyItem copyItem = new CopyItem.ItemBuilder().setName("copyItem").build();
        System.out.println(JSONObject.toJSONString(copyItem));
        CopyItem copyItem1 = new CopyItem.ItemBuilder().setName("copyItem").setStock(9999999999L).build();
        System.out.println(JSONObject.toJSONString(copyItem1));
    }


}
