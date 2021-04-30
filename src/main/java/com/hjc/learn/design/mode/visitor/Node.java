package com.hjc.learn.design.mode.visitor;

/**
 * 抽象节点类
 *
 * @author houjichao
 */
public abstract class Node {
    /**
     * 接受操作
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
}