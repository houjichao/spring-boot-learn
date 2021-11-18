package com.hjc.learn.util;

import org.apache.commons.lang3.StringUtils;

/**
 * pdd加密数据检索方案 - 检索串提取代码示例
 * <p>
 * 密文检索的功能实现是根据4位英文字符（半角），2个中文字符（全角）为一个检索条件。将一个字段拆分为多个，
 * <p>
 * 比如：taobao123
 * 使用4个字符为一组的加密方式。
 * 第一组 taob ，第二组aoba ，第三组obao ，第四组 bao1 … 依次类推
 * 如果需要检索 所有包含 检索条件4个字符的数据 比如：aoba ，加密字符后通过key like “%partial%” 查库。
 * 因为密文检索开启后 密文长度会膨胀几倍以上，如果没有强需求建议不开启。
 * <p>
 * 但是使用这种方式也有一定代价：
 * <p>
 * • 支持模糊查询加密方式，产出的密文比较长；
 * <p>
 * • 支持的模糊查询子句长度必须大于等于4个英文/数字，或者2个汉字。不支持过短的查询(出于安全考虑)；
 * <p>
 * • 返回的结果列表中有可能有多余的结果，需要增加筛选的逻辑：对记录先解密，再筛选；
 *
 * @author houjichao
 */
public class PasswordUtil {

    public static String extractIndex(String encryptedData) {
        if (encryptedData == null || encryptedData.length() < 4) {
            return null;
        }
        char sepInData = encryptedData.charAt(0);
        if (encryptedData.charAt(encryptedData.length() - 2) != sepInData) {
            return null;
        }
        String[] parts = StringUtils.split(encryptedData, sepInData);
        if (sepInData == '$' || sepInData == '#') {
            return parts[0];
        } else {
            return parts[1];
        }
    }

    public static void main(String[] args) {
        System.out.println(extractIndex("67038927"));
    }

}
