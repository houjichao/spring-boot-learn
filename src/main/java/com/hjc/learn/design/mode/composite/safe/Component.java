package com.hjc.learn.design.mode.composite.safe;

/**
 * 安全模式实现组合模式
 *
 * 抽象构件
 *
 * @author houjichao
 */
public abstract class Component {

	/**
	 * 个体和整体都具有
	 */
	public void operation(){
		//编写业务逻辑
		System.out.println("这是一个节点");
	}
}
