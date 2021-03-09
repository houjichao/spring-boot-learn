package com.hjc.learn.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * CaseFormat 以提供不同 ASCII 字符格式之间的转换，比如，编程语言的命名规范。
 *
 * @author houjichao
 */
public class GuavaCaseFormatUtil {

    public static void main(String[] args) {


        /*
         *
         * Converter<String,String>	converterTo(CaseFormat targetFormat)
         * 返回一个转换，从这个格式转换targetFormat格式.
         * String	to(CaseFormat format, String str)
         * 转换指定类型字符串.
         * static CaseFormat	valueOf(String name)
         * 返回此类型具有指定名称的枚举常量.
         * static CaseFormat[]	values()
         * 返回一个包含该枚举类型的常量数组中的顺序被声明.
         */

        /*
         * LOWER_CAMEL	Java变量的命名规则	lowerCamel
         * LOWER_HYPHEN	连字符连接变量的命名规则	lower-hyphen
         * LOWER_UNDERSCORE	C ++变量命名规则	lower_underscore
         * UPPER_CAMEL	Java和C++类的命名规则	UpperCamel
         * UPPER_UNDERSCORE	Java和C++常量的命名规则	UPPER_UNDERSCORE
         */

        /*
         * converterTo 格式器转化为targetFormat格式
         */
        Converter<String, String> camelConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        System.out.println(camelConverter.convert("input_camel"));

        /*
         * to 转换指定类型字符串
         */
        String input = "ting-feng";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, input));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, input));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, input));

    }
}
