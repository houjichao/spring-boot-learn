package com.hjc.learn.service.impl;

import com.hjc.learn.service.MyMap;

/**
 * HashMap
 * Hash：Hash散列将一个任意的长度通过某种（hash算法）算法转换成一个固定值。 移位
 * Map：地图x，y 存储
 *
 * 工作原理：通过hash算法，通过put和get存储和获取对象。
 * 存储对象时，我们将K/V传给put方法时，它调用hashCode计算hash从而得到bucket位置，进一步存储，HashMap会根据当前bucket的占用情况自动调整容量(超过Load Factor则resize为原来的2倍)。如果发生碰撞的时候，Hashmap通过链表将产生碰撞冲突的元素组织起来。如果一个bucket中碰撞冲突的元素超过某个限制(默认是8)，则使用红黑树来替换链表，从而提高速度。
 *
 * 获取对象时，我们将K传给get，它调用hashCode计算hash从而得到bucket位置，并进一步调用equals()方法确定键值对。
 * 总结：通过hash出来的值，然后通过值定位到这个map，然后value存储到这个map中hashmap基本原理
 * ————————————————
 * 版权声明：本文为CSDN博主「BoomV」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/weixin_43241043/article/details/91043868
 *
 * 不足之处（伸缩性角度）
 * Key是否重复相关
 *
 * 1.伸缩性
 * 2.时间复杂度：你的hash算法决定了你的效率
 * 伸缩性角度：每当hashmap扩容的时候需要重新去addentry对象，需要Hash。然后放入我们新的entry table的数组里
 * 如果你们的工作中你知道你们hashmap需要存多少值 几千或几万的时候最好先指定他们的扩容大小 防止在put的时候进行再次扩容很多次
 *
 * @param <K>
 * @param <V>
 *
 * @author houjichao
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    private static int defaultLength = 16;
    private static double defaultLoader = 0.75;
    private Entry<K, V>[] table = null;
    private int size = 0;

    public MyHashMap() {
        this(defaultLength, defaultLoader);
    }

    public MyHashMap(int length, double loader) {
        defaultLength = length;
        defaultLoader = loader;
        table = new Entry[defaultLength];
    }

    @Override
    public V put(K k, V v) {
        size++;
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {//判断是否已经创建
            table[index] = newEntry(k, v, null);
        } else {
            table[index] = newEntry(k, v, entry);
        }
        return table[index].getValue();
    }

    public Entry<K, V> newEntry(K k, V v, Entry<K, V> next) {
        return new Entry<K, V>(k, v, next);
    }

    public int hash(K k) {
        int m = defaultLength;
        int i = k.hashCode() % m; //得到哈希值取模
        return i >= 0 ? 1 : -i;
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        if (table[index] == null) { //判断是否找到
            return null;
        }
        return find(k, table[index]);

    }

    public V find(K k, MyHashMap<K, V>.Entry<K, V> entry) {
        if (k == entry.getK() || k.equals((entry.getK()))) { //判断key值是否相等
            return entry.getV();
        } else {
            if (entry.next != null) { //不相等从next里继续往下找
                return find(k, entry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    class Entry<K, V> implements MyMap.Entry<K, V> {
        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }

        @Override
        public K setKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }
}

