package com.hjc.learn.service;

/**
 * @author houjichao
 */
public interface Map<K, V> {
    public V put(K k, V v);

    public V get(K k);

    public int size();

    public interface Entry<K, V> {
        public K setKey();

        public V getValue();
    }
}
