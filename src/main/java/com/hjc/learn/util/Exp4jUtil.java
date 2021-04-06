package com.hjc.learn.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

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
public class Exp4jUtil {


    /**
     * 利用计算公式计算结果
     * @param expressionStr 数学公式
     * @param variables 变量
     * @return 计算结果
     */
    public static Double calculation(String expressionStr, Map<String,Double> variables) {
        /*
           变量名称必须以字母或下划线_开头，并且只能包含字母，数字或下划线。
           以下是有效的变量名称：
           varX
           _x1
           _var_X_1
           而1_var_x不是，因为它不是以字母或下划线开头。
         */
        // 1.构建表达式
        Set<String> variablesKey = variables.keySet();
        Expression expression = new ExpressionBuilder(expressionStr).variables(variablesKey).build();
        // 2.循环放置变量
        expression.setVariables(variables);
        return expression.evaluate();
    }

    /**
     * 验证表达式
     */
    public static void checkExpression() {
        Expression e = new ExpressionBuilder("[(2*3)+ (3*4)+(4*5)]/x")
                .variable("x")
                .build();

        ValidationResult res = e.validate();
        boolean valid = res.isValid();
        System.out.println(valid);
        List<String> errors = res.getErrors();
        errors.forEach(System.out::println);

        e.setVariable("x", 1d);
        res = e.validate();
        System.out.println(res.isValid());
    }

    public static void example1() {
        Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        double result = e.evaluate();
    }

    public static void example2() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Expression e = new ExpressionBuilder("3log(y)/(x+1)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        Future<Double> future = e.evaluateAsync(exec);
        double result = future.get();
    }

    public static void example3() {
        double result = new ExpressionBuilder("2cos(xy)")
                .variables("x","y")
                .build()
                .setVariable("x", 0.5d)
                .setVariable("y", 0.25d)
                .evaluate();
        System.out.println(2d * Math.cos(0.5d * 0.25d));
    }

    public static void example4() {
        String expr = "pi+π+e+φ";
        double expected = 2*Math.PI + Math.E + 1.61803398874d;
        Expression e = new ExpressionBuilder(expr).build();
        //assertEquals(expected, e.evaluate(),0d);
    }

    public static void main(String[] args) {
        Expression e = new ExpressionBuilder("[(2*3)+ (3*4)+(4*5)]/x")
                .variable("x")
                .build()
                .setVariable("x", 3);
        double result = e.evaluate();
        System.out.println(result);

        //test calculation
        String expressionStr = "[(2*3)+ (3*4)+(4*5)]/_1124e76845b141809a1ac3d1b158bb94";
        Map<String,Double> variables = new HashMap<>();
        variables.put("_1124e76845b141809a1ac3d1b158bb94",3D);
        System.out.println(calculation(expressionStr,variables));

        checkExpression();
    }


}
