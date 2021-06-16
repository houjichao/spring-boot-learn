package com.hjc.learn.collection.stack;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 计算中缀表达式
 * 在编写程序的时候，我们使用的带括号的数学表达式实际上是中缀表达式，即运算符在中间，例如：1 + 2 * (9 - 5)。
 * 但是计算机执行表达式的时候，它并不能直接计算中缀表达式，而是通过编译器把中缀表达式转换为后缀表达式，例如：1 2 9 5 - * +。
 * 这个编译过程就会用到栈。我们先跳过编译这一步（涉及运算优先级，代码比较复杂），看看如何通过栈计算后缀表达式。
 * 计算后缀表达式不考虑优先级，直接从左到右依次计算，因此计算起来简单。
 *
 * @author houjichao
 */
public class Lisp {

    public static void main(String[] args) {
        String s5 = "(div 8 0)";
        String s6 = "(add (sub (div 8 2) (mul 1 9)) 20)";
        System.out.println(getResult(s5));
        System.out.println(getResult(s6));
    }

    public static String getResult(String input) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            String c = String.valueOf(input.charAt(i));
            if (!")".equals(c)) {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String pop = stack.pop();
                    if ("(".equals(pop)) {
                        break;
                    }
                    sb.append(pop);
                }
                String single = getSingle(sb.reverse().toString());
                if (stack.isEmpty() || "error".equals(single)) {
                    return single;
                }
                stack.push(new StringBuffer(single).reverse().toString());
            }
        }
        return null;
    }

    public static String getSingle(String express) {
        String[] s = express.split(" ");
        switch (s[0]) {
            case "add":
                return new BigDecimal(s[1]).add(new BigDecimal(s[2])).toString();
            case "sub":
                return new BigDecimal(s[1]).subtract(new BigDecimal(s[2])).toString();
            case "mul":
                return new BigDecimal(s[1]).multiply(new BigDecimal(s[2])).toString();
            case "div":
                if (new BigDecimal(s[2]).equals(BigDecimal.ZERO)) {
                    return "error";
                }
                return new BigDecimal(s[1]).divide(new BigDecimal(s[2]), BigDecimal.ROUND_UP).toString();
            default:
                return "";
        }
    }

}
