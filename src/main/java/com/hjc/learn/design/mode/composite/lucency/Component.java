package com.hjc.learn.design.mode.composite.lucency;

import java.util.List;

/**
 * 透明模式实现组合模式
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
		System.out.println("这是抽象节点");
	}

	/**
	 * 增加一个叶子构件或树枝构件
	 * @param component
	 */
	public abstract void add(Component component);

	/**
	 * 删除一个叶子构件或树枝构件
	 * @param component
	 */
	public abstract void remove(Component component);

	/**
	 * 获得分支下的所有叶子构件和树枝构件
	 * @return
	 */
	public abstract List<Component> getChildren();
}
