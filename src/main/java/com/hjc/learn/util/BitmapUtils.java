package com.hjc.learn.util;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Bitmap的概念
 * Bitmap 翻译做中文称为”位图”, 其核心里面是充分利用一部分数据本身就存在的元属性(空间/位置/容量)信息,
 * 我们这里主要是使用其中的每一位的位置信息,达到使用一个信息表达两种含义的作用
 * <p>
 * 其实就也是一种特殊的编码(coding)过程(或者叫多工(multiplex))
 * <p>
 * 解决的问题
 * bitmap可以用来有效解决两类问题
 * <p>
 * 1、存储大量值可以用布尔值标识的数据
 * 2、部分有用到交,并,差等集合运算的数据
 * 第一个特性主要是利用位存储的节省空间的特性,第二个是利用计算机位运算比较快速的特性
 * <p>
 * 如果标签过于稀疏会不会浪费空间?
 * 如果我们在一个很长的bitmap中只存除了极少量的数据是不是会对空间造成浪费呢?
 * 例如: 在bitmap的第40000位置为1,那存储的数据大概就类似: 00000000000…0000000001
 * 这样的数据前面的39999位都是0,不会浪费空间吗
 * Google的EWAHCompressedBitmap
 * Google的EWAHCompressedBitmap就对这种情况做了优化
 * EWAHCompressedBitmap 将整个的二进制数据分成每64位一个的word
 * 一个空的Bitmap默认拥有 4 个word 也就是 4*64 位
 * 其中 word0 存储bitmap的头信息
 * 当我们改变对应位置的比特位的值时 word 会跟着变化
 * 当我们插入的值非常大的时候(例如:40000), 算法会根据当前的值 创建两个新的word
 * 一个用于存储第40000个数据所在的word的信息(LW), 还有一个存储跨度信息(称为:跨度word /RLW )
 * <p>
 * 假如说我们给一个空的bitmap,我们插入40000的话正常情况下会有6个word,前4个是头信息word+3个空word,第6个中保存40000这个数字所在的位置信息,
 * 第5个word中保存从第 4-625 word的跨度信息,第626word中存储有 40000 这个数据
 * ps: 第一个word存储头信息, 625 = floor( (40000 + 1) / 64 )
 * 存储跨度信息的word和普通的存储数据的word虽然空间一样但是存储的内容不一样
 * 存储跨度信息的word大概内容这样
 * 前32位存储 `当前跨度word(RLW)横跨了多少空word`
 * 后32位存储 `当前跨度word(RLW)后方有多少个连续的LW`
 * 当我们存储 位置在跨度word(RLW)之中的数据(例如:20000), RLW会进行分裂
 * 变成3个word,中间一个存储20000所在的LW信息,前后各有一个RLW保存新的跨度信息
 *
 * @author houjichao
 */
@Slf4j
public class BitmapUtils {

    /**
     * 反序列化
     *
     * @param data data数据
     * @return bitmap
     */
    public static EWAHCompressedBitmap deserialize(byte[] data) {
        if (null == data) {
            return null;
        }
        EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();
        try {
            bitmap.deserialize(new DataInputStream(new ByteArrayInputStream(data)));
        } catch (IOException e) {
            log.error("bitmap操作失败");
        }
        return bitmap;
    }

    /**
     * 序列化
     *
     * @param bitmap bitmap
     * @return byte[]
     */
    public static byte[] serialize(EWAHCompressedBitmap bitmap) {
        if (null == bitmap) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.serialize(new DataOutputStream(bos));
            return bos.toByteArray();
        } catch (IOException e) {
            log.error("bitmap操作失败");
        }
        return null;

    }

    /**
     * 判断bitmap是否为空
     *
     * @param data 序列化后的bitmap
     * @return true if bitmap is empty
     */
    public static boolean isEmpty(byte[] data) {
        EWAHCompressedBitmap bitmap = deserialize(data);
        return null == bitmap || bitmap.isEmpty();
    }

    /**
     * 打标签 如果原来不存在标签信息，则新建标签bitmap结构
     *
     * @param data     原来的标签信息
     * @param targetId 新的需要贴标签的目标id
     * @return 贴完标签之后的bitmap
     */
    public static byte[] labeling(byte[] data, long targetId) {
        EWAHCompressedBitmap bitmap;
        if (null == data) {
            bitmap = new EWAHCompressedBitmap();
        } else {
            bitmap = deserialize(data);
        }
        //贴标签
        bitmap.set((int) targetId);
        return serialize(bitmap);
    }

    /**
     * 取消标签，如果取消之后bitmap为空，则返回null
     *
     * @param data     原来的标签信息
     * @param targetId 需要取消标签的目标
     * @return 取消之后的标签bitmap
     */
    public static byte[] unLabeling(byte[] data, long targetId) {
        if (null == data) {
            return null;
        }
        EWAHCompressedBitmap bitmap = deserialize(data);
        // 取消标签，将对应二进制位修改为0
        bitmap.clear((int) targetId);

        return bitmap.isEmpty() ? null : serialize(bitmap);
    }

    /**
     * 多个标签查询取交集
     *
     * @param dataList dataList
     * @return ids
     */
    public static List<Integer> intersection(List<byte[]> dataList) {
        // 如果包含null，那么直接返回空列表
        if (dataList.contains(null)) {
            return Collections.emptyList();
        }
        Optional<EWAHCompressedBitmap> reduce = dataList.stream()
                .filter(Objects::nonNull).map(BitmapUtils::deserialize)
                .reduce((o1, o2) -> o1.and(o2));
        return reduce.map(EWAHCompressedBitmap::toList).orElse(Collections.emptyList());
    }
}
