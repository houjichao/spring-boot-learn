package com.hjc.learn.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author houjichao
 */
public class ArrayTest {

    static int[][] myArray = new int[3][3];

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == i) {
                    myArray[i][j] = 1;
                } else {
                    myArray[i][j] = 0;
                }
            }
        }
        System.out.println(JSONObject.toJSONString(myArray));
    }
}
