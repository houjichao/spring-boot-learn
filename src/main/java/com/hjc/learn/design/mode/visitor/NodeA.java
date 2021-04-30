package com.hjc.learn.design.mode.visitor;

/**
 * 具体节点类NodeA
 *
 * @author houjichao
 */
public class NodeA extends Node{
    /**
     * 接受操作
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    /**
     * NodeA特有的方法
     */
    public String operationA(){
        return "NodeA";
    }

}