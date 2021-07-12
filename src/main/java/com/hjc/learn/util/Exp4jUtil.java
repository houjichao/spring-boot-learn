package com.hjc.learn.util;

import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 数学公式计算
 *
 * @author houjichao
 */
@Slf4j
public class Exp4jUtil {


    /**
     * 利用计算公式计算结果
     *
     * @param expressionStr 数学公式
     * @param variables     变量
     * @param decimalDigits 保留小数位数，四舍五入
     * @return 计算结果
     */
    public static Double calculation(String expressionStr, Map<String, Double> variables, Integer decimalDigits) {
        /*
           变量名称必须以字母或下划线_开头，并且只能包含字母，数字或下划线。
           以下是有效的变量名称：
           varX
           _x1
           _var_X_1
           而1_var_x不是，因为它不是以字母或下划线开头。
         */
        log.info("Exp4jUtil.calculation计算表达式为:{};;;;计算参数为{}", expressionStr, variables.toString());
        if (StringUtils.isNotBlank(expressionStr)) {
            // 1.判断表达式有变量参数，但是变量中没有 或数量不匹配
            if (expressionStr.contains("_") && CollectionUtils.isEmpty(variables)) {
                return null;
            }
            /*int variablesCount = CharMatcher.is('_').countIn(expressionStr);
            if (variablesCount < variablesKey.size()) {
                return null;
            }*/
            boolean variablesIllegal = false;
            for (Map.Entry<String, Double> map : variables.entrySet()) {
                if (map.getValue() == null) {
                    variablesIllegal = true;
                    break;
                }
            }
            if (variablesIllegal) {
                return null;
            }
            Set<String> variablesKey = variables.keySet();
            // 2.构建表达式
            Expression expression = new ExpressionBuilder(expressionStr).variables(variablesKey).build();
            // 3.循环放置变量
            expression.setVariables(variables);
            // 4.校验
            ValidationResult validate = expression.validate();
            if (!validate.isValid()) {
                return null;
            }
            try {
                // 5.计算结果，进行小数位四舍五入
                double result = expression.evaluate();
                BigDecimal bigDecimal = new BigDecimal(result);
                result = bigDecimal.setScale(decimalDigits, BigDecimal.ROUND_HALF_UP).doubleValue();
                return result;
            } catch (ArithmeticException e) {
                log.error("Exp4jUtil.calculation计算结果错误，表达式:{}，参数为{}", expressionStr, variables.toString());
            }
        }
        return null;
    }

    /**
     * 检查表达式
     *
     * @param expressionStr 数学公式
     * @param variables     变量
     */
    public static Boolean checkExpression(String expressionStr, Map<String, Double> variables) {
        if (StringUtils.isNotBlank(expressionStr)) {
            // 1.构建表达式
            Set<String> variablesKey = variables.keySet();
            Expression expression = new ExpressionBuilder(expressionStr).variables(variablesKey).build();
            // 2.循环放置变量
            expression.setVariables(variables);
            // 3.校验
            ValidationResult validate = expression.validate();
            return validate.isValid();
        }
        return false;
    }

    /**
     * 执行一个简单的表达式
     */
    public static void example1() {
        Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        double result = e.evaluate();
        System.out.println("example1 result：" + result);
    }

    /**
     * 异步执行表达式
     *
     * @throws Exception
     */
    public static void example2() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Expression e = new ExpressionBuilder("3log(y)/(x+1)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        Future<Double> future = e.evaluateAsync(exec);
        double result = future.get();
        System.out.println("example2 result：" + result);
    }

    /**
     * 从v0.4.0开始，exp4j确实支持隐式乘法。因此，像2cos（yx）这样的表达式将被解释为2 * cos（y * x）
     */
    public static void example3() {
        double result = new ExpressionBuilder("2cos(xy)")
                .variables("x", "y")
                .build()
                .setVariable("x", 0.5d)
                .setVariable("y", 0.25d)
                .evaluate();
        System.out.println("example3 result：" + 2d * Math.cos(0.5d * 0.25d));

    }

    /**
     * 从版本0.4.6开始，以下通用常量已添加到exp4j并自动绑定：pi，π是Math.PI中定义的π值，e是欧拉数e的值，φ是黄金分割率的值（1.61803398874 ）
     */
    public static void example4() {
        //Math.E 自然对数
        //φ黄金分隔率
        String expr = "pi+π+e+φ";
        double expected = 2 * Math.PI + Math.E + 1.61803398874d;
        Expression e = new ExpressionBuilder(expr).build();
        boolean b = expected == e.evaluate();
        System.out.println("example4 result：" + b);
    }


    /**
     * 从0.3.5版开始，可以对数字使用科学计数法。
     * 该数字被分解为有效位数/尾数y和形式为yEx的指数x，其值被评估为y * 10 ^ x。请注意，
     * “ e / E”不是运算符，而是数字的一部分，因此无法评估类似1.1e-（x * 2）的表达式。使用精细结构常数α= 7.2973525698 * 10 ^ -3的示例：
     */
    public static void example5() {
        String expr = "7.2973525698e-3";
        double expected = Double.parseDouble(expr);
        Expression e = new ExpressionBuilder(expr).build();
        boolean b = expected == e.evaluate();
        System.out.println("example5 result：" + b);
    }

    /**
     * 您可以扩展抽象类Function以便在表达式中使用自定义函数。
     * 您只需要实现apply（double ... args）方法。在以下示例中，创建了一个以2为底的对数的函数，并在以下表达式中使用该函数。
     * 一个自定义函数，使用恒等式log（value，base）= ln（value）/ ln（base）将对数计算为任意基数
     */
    public static void example6() {
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        double result = new ExpressionBuilder("logb(8, 2)")
                .function(logb)
                .build()
                .evaluate();
        double expected = 3;
        boolean b = expected == result;
        System.out.println("example6 result：" + b);
    }

    /**
     * 还可以通过构造函数Function（String name，int argc）定义多个参数函数，
     * 例如avg（a，b，c，d ），其中argc为参数计数。下面的示例使用一个自定义函数，该函数返回四个值的平均值。
     */
    public static void example7() {
        Function avg = new Function("avg", 4) {

            @Override
            public double apply(double... args) {
                double sum = 0;
                for (double arg : args) {
                    sum += arg;
                }
                return sum / args.length;
            }
        };
        double result = new ExpressionBuilder("avg(1,2,3,4)")
                .function(avg)
                .build()
                .evaluate();

        double expected = 2.5d;
        boolean b = expected == result;
        System.out.println("example7 result：" + b);
    }

    /**
     * 您可以扩展抽象类Operator，以便声明供表达式使用的自定义运算符，符号是一个由！，＃，§，$，＆，;，：，〜，<，>，|，==组成的String 。。
     * 请注意，添加带有已使用符号的运算符会覆盖任何现有运算符，包括内置运算符。因此可以覆盖例如+运算符。运算符的构造函数最多包含4个参数：
     * <p>
     * 用于此操作的符号（由！，＃，§，$，＆，;，：，〜，<，>，|，==组成的字符串）
     * 如果操作是关联的
     * 操作的优先级
     * 运算符具有的操作数（1或2）
     * <p>
     * 创建用于计算阶乘的自定义运算符
     */
    public static void example8() {
        Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

            @Override
            public double apply(double... args) {
                final int arg = (int) args[0];
                if ((double) arg != args[0]) {
                    throw new IllegalArgumentException("Operand for factorial has to be an integer");
                }
                if (arg < 0) {
                    throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                }
                double result = 1;
                for (int i = 1; i <= arg; i++) {
                    result *= i;
                }
                return result;
            }
        };

        double result = new ExpressionBuilder("3!")
                .operator(factorial)
                .build()
                .evaluate();

        double expected = 6d;

        boolean b = expected == result;
        System.out.println("example8 result：" + b);
    }

    /**
     * 创建一个自定义运算符逻辑运算符，如果第一个参数大于或等于第二个参数，则返回1，否则返回0。
     */
    public static void example9() {
        Operator gteq = new Operator(">=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] >= values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };

        Expression e = new ExpressionBuilder("1>=2").operator(gteq)
                .build();
        boolean b = e.evaluate() == 0d;
        System.out.println("example9 result：" + b);
        e = new ExpressionBuilder("2>=1").operator(gteq)
                .build();
        b = e.evaluate() == 1d;
        System.out.println("example9 result：" + b);
    }

    /**
     * 尝试除以零时，exp4j会引发ArithmeticException。在实现涉及除法的Operator或Function时，实现者必须确保抛出相应的RuntimeException。
     */
    public static void example10() {
        Operator reciprocal = new Operator("$", 1, true, Operator.PRECEDENCE_DIVISION) {
            @Override
            public double apply(final double... args) {
                if (args[0] == 0d) {
                    throw new ArithmeticException("Division by zero!");
                }
                return 1d / args[0];
            }
        };
        Expression e = new ExpressionBuilder("0$").operator(reciprocal).build();
        // <- this call will throw an ArithmeticException
        System.out.println("example10 result：" + e.evaluate());
    }

    /**
     * 在设置变量之前验证表达式，即跳过是否已设置所有变量的检查。
     */
    public static void example12() {
        Expression e = new ExpressionBuilder("x")
                .variable("x")
                .build();

        ValidationResult res = e.validate(false);
        //assertTrue(res.isValid());
        //assertNull(res.getErrors());
    }


    public static void main(String[] args) throws Exception {
        Expression e = new ExpressionBuilder("[(2*3)+ (3*4)+(4*5)]/x")
                .variable("x")
                .build()
                .setVariable("x", 3);
        double result = e.evaluate();
        System.out.println(result);

        //test calculation
        String expressionStr = "[(2*3)+ (3*4)+(4*5)]/_1124e76845b141809a1ac3d1b158bb94";
        Map<String, Double> variables = new HashMap<>();
        variables.put("_1124e76845b141809a1ac3d1b158bb94", 3D);
        System.out.println(calculation(expressionStr, variables, 2));
        checkExpression(expressionStr, variables);

        example1();
        example2();
        example3();
        example4();
        example5();
        example6();
        example7();
        example8();
        example9();
        //example10();
        example12();
    }


}
