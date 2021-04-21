package com.hjc.learn.util;

import com.google.common.base.CharMatcher;

/**
 * CharMatcher
 *
 * 使用 CharMatcher 的好处更在于它提供了一系列方法，让你对字符作特定类型的操作，例如：修剪[trim]、折叠[collapse]、移除[remove]、保留[retain]等等。
 *
 * @author houjichao
 */
public class GuavaCharMatcherUtil {

    public static void main(String[] args) {
        String str = "TingFeng Sharing the Google Guava Used";
        System.out.println(countCharInStr(str,'a'));

        System.out.println(retainLetterOrDigit("H*el.lo,中国}12"));

        System.out.println(countDigitInStr("asdjkas1asdnjk2"));

        testTrimAndCollapseFrom();
    }

    /**
     * 统计某个字符在字符串中出现的次数
     * @param str 字符串
     * @param match 字符
     * @return 出现次数
     */
    public static int countCharInStr(String str,char match) {
        return CharMatcher.is(match).countIn(str);
    }

    /**
     * 将一个字符串中的字母、数字保留（包含中文），剔除其他字符
     * @param str 字符串
     * @return 剔除后的字符串
     */
    public static String retainLetterOrDigit(String str) {
        //匹配字母（含中文）或数字
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        //保留匹配到的字符
        return  matcher.retainFrom(str);
    }

    /**
     * 统计某个字符串中数字的个数
     * @param str 字符串
     * @return 数字个数
     */
    public static int countDigitInStr(String str) {
        return CharMatcher.digit().countIn(str);
    }

    /**
     * countIn 查找字符在字符串中的个数
     */
    public static void testCountIn() {

        String input = "H*el.lo,}12";

        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        //保留匹配到的字符
        System.out.println(matcher.retainFrom(input));  // Hello12
        System.out.println(matcher.countIn(input));     // 7

        //通过边界值初始化CharMatcher，该匹配器可以匹配处于startInclusive和endInclusive之间的所有字符.
        matcher = CharMatcher.inRange('a', 'l');
        System.out.println(matcher.countIn(input));     // 3
    }

    public static void testTrimAndCollapseFrom() {
        String input = "     Ting Feng  aaa  asdasd ";
        System.out.println(CharMatcher.anyOf("aaad").countIn(input));
        String result = CharMatcher.anyOf("aaa").trimAndCollapseFrom(input, '-');
        System.out.println(result);

        result = CharMatcher.is('a').trimAndCollapseFrom(input, '-');
        System.out.println(result);
    }

}
