package com.hjc.learn.test.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author houjichao
 */
public class SplitterDemo {

    public static void main(String[] args) {
        String sql = "select * from user LIMIT 1 limit 2";

        System.out.println(Splitter.on("limit").splitToList(sql).get(0));

        System.out.println(Splitter.onPattern("(?i)limit").splitToList(sql).get(0));


        System.out.println(Splitter.on(Pattern.compile("^limit$", Pattern.CASE_INSENSITIVE)).splitToList(sql).get(0));

        String regex1 = "^(?i)limit$";

        String regex = "^limit$";
        String s = "limit1";
        System.out.println(s.matches(regex1));
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        System.out.println(p.matcher(s).matches());

        System.out.println(Splitter.onPattern(regex1).splitToList(sql).get(0));


        String demo = "count(DISTINCT     aa.id,bb.id)";
        String substring = demo.substring(demo.indexOf("(") + 1, demo.indexOf(")"));
        System.out.println(substring);
        List<String> strings = Splitter.on(" ").omitEmptyStrings().splitToList(substring);
        System.out.println(JSONObject.toJSONString(strings));

    }

}
