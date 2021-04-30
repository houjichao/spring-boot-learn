package com.hjc.learn.design.mode.visitor;

/**
 * 具体节点类NodeB
 *
 * @author houjichao
 */
public class NodeB extends Node{
    /**
     * 接受方法
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    /**
     * NodeB特有的方法
     */
    public String operationB(){
        return "NodeB";
    }
}