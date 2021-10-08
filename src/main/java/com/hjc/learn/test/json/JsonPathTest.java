package com.hjc.learn.test.json;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

/**
 * JsonPath使用
 * <p>
 * 这里我习惯用第一种，也推荐使用，比较类似编程时的方法调用；用 $ 表示从根元素开始找，. 点上要找的元素，
 * 一层一层的找下去，直到达到你需要找的地方；同样的元素可以根据中括号[]+索引的方法来区分，索引也是从0开始
 *
 * @author houjichao
 */
public class JsonPathTest {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("file/gis.json");
        String path = classPathResource.getPath();
        String fileData = FileUtil.readUtf8String(path);
        String jsonPath = "$.result.city.440100.population.resident.消费水平（分段）.低.percent";

        DocumentContext documentContext = JsonPath.parse(fileData);
        Double read = documentContext.read(jsonPath, Double.class);
        System.out.println(read);

        /*
         * 输出该json中所有percent的值
         */
        List<Object> containPrices = documentContext.read("$..percent");
        for (Object p : containPrices) {
            System.out.println(p.toString());
        }

        /*
         * 取出大于percent 大于 0.4的元素
         */
        List<Object> prices = documentContext.read("$.result.city.440100.population.home..[?(@.percent>0.4)]");
        for (Object p : prices) {
            System.out.println("percent:----" + p.toString());
        }


    }


}
