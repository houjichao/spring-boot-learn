package com.hjc.learn.design.mode.visitor;

/**
 * 具体访问者VisitorB类
 *
 * @author houjichao
 */
public class VisitorB implements Visitor {
    /**
     * 对应于NodeA的访问操作
     */
    @Override
    public void visit(NodeA node) {
        System.out.println("VisitorB" + node.operationA());
    }

    /**
     * 对应于NodeB的访问操作
     */
    @Override
    public void visit(NodeB node) {
        System.out.println("VisitorB" + node.operationB());
    }

}