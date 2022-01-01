package com.hjc.learn.test.number;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NumberTest {

    static class TestDTO {
        String id; //id
        Double num;//数字

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getNum() {
            return num;
        }

        public void setNum(Double num) {
            this.num = num;
        }
    }

    public static List<List<TestDTO>> Test(List<TestDTO> dtoParam, Double samplesNumber) {
        List<List<TestDTO>> reust = new ArrayList<List<TestDTO>>();
        int a = 1;
        int c = 1;
        List<TestDTO> d = null;
        for (int i = 0; i < dtoParam.size(); i++) {
            double s = dtoParam.get(i).getNum();
            StringBuffer str = new StringBuffer(dtoParam.get(i).getNum() + "+");//用于控制台打印显示，和逻辑无关
            boolean bb = true;
            while (bb) {
                if (bb = false) {
                    break;
                }
                if (dtoParam.size() == a) {
                    bb = false;
                    break;
                }
                boolean b = true;
                while (b) {
                    if (dtoParam.size() == c) {
                        a++;
                        b = false;
                        c = a;
                        break;
                    }
                    d = new ArrayList<>();
                    d.add(dtoParam.get(i));
                    for (int j = c; j < dtoParam.size(); j++) {
                        s = s + dtoParam.get(j).getNum();
                        d.add(dtoParam.get(j));
                        str.append(dtoParam.get(j).getNum() + "+");//用于控制台打印显示，和逻辑无关
                        System.out.println(str.substring(0, str.length() - 1));//用于控制台打印显示，和逻辑无关
                        if (s == samplesNumber) {
                            reust.add(d);
                            break;
                        }
                        if (dtoParam.size() - j == 1) {
                            s = dtoParam.get(i).getNum();
                            str = new StringBuffer(dtoParam.get(i).getNum() + "+");//用于控制台打印显示，和逻辑无关
                            c++;
                            break;
                        }
                    }
                }
            }
        }
        return reust;
    }

    public static void main(String[] args) {
        //模拟一个数字集合
        //模拟一个数字集合
        List<TestDTO> l = new ArrayList<>();

        ClassPathResource classPathResource = new ClassPathResource("file/number.txt");
        String path = classPathResource.getPath();
        String json = FileUtil.readUtf8String(path);
        List<String> strings = Splitter.on("\n").splitToList(json);
        for (String str : strings) {
            TestDTO d = new TestDTO();
            d.setId(str);
            if (StringUtils.isNotBlank(str)) {
                d.setNum(Double.parseDouble(str));
                l.add(d);
            }
        }
        List<List<TestDTO>> list = Test(l, 27519.72);
        for (int i = 0; i < list.size(); i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < list.get(i).size(); j++) {
                str.append(list.get(i).get(j).getNum()).append("+");
            }
            System.out.println("第" + i + "个结果：" + str.substring(0, str.length() - 1));
        }

    }
}
