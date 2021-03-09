package com.hjc.learn.util;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.hjc.learn.enums.ValueInterval;

/**
 * 区间判断工具类
 *
 * @author houjichao
 */
public class GuavaRangeUtil {


    /**
     * 判断一个值是否在某个区间内
     * @param doubleRange 区间
     * @param value 值
     * @return ValueInterval枚举值
     */
    public static Integer inTheInterval(Range<Double> doubleRange, Double value) {
        if (doubleRange == null || value == null) {
            return ValueInterval.CONTAIN.getCode();
        }

        //是否包含区间内
        if (doubleRange.contains(value)) {
            return ValueInterval.CONTAIN.getCode();
        }

        //判断区间是否有特定边界，或是无限的
        //(-∞..+∞) 必包含，已返回，只需判断单边无穷
        if (!doubleRange.hasLowerBound()) {
            //负无穷 只有 包含 或大于最大值
            return ValueInterval.GREATER_THAN_MAX.getCode();
        }

        if (!doubleRange.hasUpperBound()) {
            //正无穷 只有 包含 或小于最小值
            return ValueInterval.LESS_THAN_MIN.getCode();
        }
        double maxValue = doubleRange.upperEndpoint();
        BoundType upperBoundType = doubleRange.upperBoundType();
        //是否大于最大值
        //最大值开区间
        if (BoundType.OPEN.compareTo(upperBoundType) == 0 && Double.compare(value, maxValue) >= 0) {
            return ValueInterval.GREATER_THAN_MAX.getCode();
        }
        //最大值闭区间
        if (BoundType.CLOSED.compareTo(upperBoundType) == 0 && Double.compare(value, maxValue) > 0) {
            return ValueInterval.GREATER_THAN_MAX.getCode();
        }
        return ValueInterval.LESS_THAN_MIN.getCode();

    }

    public static void main(String[] args) {

        //Range的几种类型
        //open closed closedOpen openClosed
        //greaterThan (a..+∞)  atLeast [a..+∞)
        //lessThan(-∞..b) atMost(-∞..b]
        //all(-∞..+∞)

        // (3..+∞) 无上界区间：(a..+∞) 或 [a..+∞)
        Range<Integer> downTo = Range.downTo(3, BoundType.OPEN);
        // (-∞..3] 无下界区间：(-∞..b) 或 (-∞..b]
        Range<Integer> upTo = Range.upTo(3, BoundType.CLOSED);
        // [1..6) 等同于 Range.closedOpen(1, 6)
        //有界区间
        Range<Integer> range = Range.range(1, BoundType.CLOSED, 6, BoundType.OPEN);

        /*
         * hasLowerBound() 和 hasUpperBound()	判断区间是否有特定边界，或是无限的
         * lowerBoundType() 和 upperBoundType()	返回区间边界类型，CLOSED 或 OPEN；如果区间没有对应的边界，抛出 IllegalStateException。
         * lowerEndpoint() 和 upperEndpoint()	返回区间的端点值；如果区间没有对应的边界，抛出 IllegalStateException。
         * isEmpty()	判断是否为空区间。
         */

        Range<Double> closed = Range.greaterThan(0D);
        double maxValue = closed.upperEndpoint();
        System.out.println(BoundType.OPEN.compareTo(closed.lowerBoundType()));
        System.out.println(closed.lowerBoundType());
        System.out.println(closed.upperBoundType());
        System.out.println(Double.compare(maxValue, Double.MAX_VALUE));
        System.out.println(closed.upperEndpoint());
        System.out.println(closed.contains(1D));
    }

}
