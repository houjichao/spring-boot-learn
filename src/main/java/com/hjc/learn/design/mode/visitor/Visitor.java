package com.hjc.learn.design.mode.visitor;

/**
 * 抽象访问者角色
 *
 * @author houjichao
 */
public interface Visitor {

    /**
     * 对应于NodeA的访问操作
     * @param node
     */
    void visit(NodeA node);

    /**
     * 对应于NodeB的访问操作
     * @param node
     */
    void visit(NodeB node);
}