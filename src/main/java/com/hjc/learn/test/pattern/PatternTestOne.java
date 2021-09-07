package com.hjc.learn.test.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author houjichao
 */
public class PatternTestOne {

    /**
     * * 匹配前面的子表达式零次或多次。要匹配 * 字符，请使用 \*。
     * + 匹配前面的子表达式一次或多次。要匹配 + 字符，请使用 \+。
     * . 匹配除换行符 \n 之外的任何单字符。要匹配 . ，请使用 \. 。
     */
    private final static String regex = "('\\$\\{.*?}')+";

    private final static String regex1 = "\\$\\{.*?\\}";


    public static void main(String[] args) {


        /*
         * groupCount讲解
         *
         * m.groupCount()表示()的个数，每个()表示需要一次匹配，这个小括号要特别注意。
         * m.group(0)表示要匹配满足正则表达式中所有括号里的字符串的第一个值，因此为cat。
         * m.group(1)表示匹配正则表达式中的第一个括号里的内容即可，因此为ca，注意，也是第一次的值。
         * m.group(2)表示匹配正则表达式中的第二个括号里的内容即可，因此为t，注意，也是第一次的值。
         */
        Pattern p = Pattern.compile("(ca)(t)");
        Matcher m = p.matcher("one cat,two cats in the yard");
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        System.out.println("groupCount为：" + m.groupCount());
        for (int i = 0; i <= m.groupCount(); i++) {
            System.out.println("第" + i + "个子串为:" + m.group(i));
        }


        Pattern compile = Pattern.compile(regex1);

        String str = "MOD(phone,'${MMOD}')='${VVAL}')";


        Matcher matcher = compile.matcher(str);

        /*
         * （2）find有参方法
         * 是通过调用search方法实现的，只不过在find方法中检测了边界。
         * 然后就是把find的参数传给search进行匹配了。
         *
         * （3）find无参方法
         * 无参方法对比有参方法区别不大。只是无参方法会自动 以上一次匹配成功的子串的下一个字符索引
         * 作为search的参数，然后开始匹配。(功能类似于iterator迭代器的next)
         */
        /*
         * Attempts to find the next subsequence of the input sequence that matches
         * the pattern.
         * 尝试查找与模式匹配的输入序列的下一个子序列。
         *
         * <p> This method starts at the beginning of this matcher's region, or, if
         * a previous invocation of the method was successful and the matcher has
         * not since been reset, at the first character not matched by the previous
         * match.
         * 该方法从该匹配器所在区域的开头开始，或者，如果对该方法的前一次调用成功，
         * 且该匹配器还没有被重置，则从前一个匹配项未匹配的第一个字符开始。
         *
         * <p> If the match succeeds then more information can be obtained via the
         * <tt>start</tt>, <tt>end</tt>, and <tt>group</tt> methods.  </p>
         *
         * @return  <tt>true</tt> if, and only if, a subsequence of the input
         *          sequence matches this matcher's pattern
         */
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            group = group.replace("'${", "${").replace("}'", "}");
            /*
            Returns the number of capturing groups in this matcher's pattern.
            返回此匹配器模式中捕获组的数量。
             */
            System.out.println("replace result: " + group);
        }

    }

}