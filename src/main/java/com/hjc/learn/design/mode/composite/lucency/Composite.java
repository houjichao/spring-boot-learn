package com.hjc.learn.design.mode.composite.lucency;

import java.util.ArrayList;
import java.util.List;

/**
 * 透明模式实现组合模式
 *
 * 树枝构件
 *
 * @author houjichao
 */
public class Composite extends Component {

	/**
	 * 构件容器
	 */
	private ArrayList<Component> componentArrayList = new ArrayList<>();

	/**
	 * 增加一个叶子构件或树枝构件
	 * @param component
	 */
	@Override
	public void add(Component component){
		this.componentArrayList.add(component);
	}

	/**
	 * 删除一个叶子构件或树枝构件
	 * @param component
	 */
	@Override
	public void remove(Component component){
		this.componentArrayList.remove(component);
	}

	/**
	 * 获得分支下的所有叶子构件和树枝构件
	 * @return
	 */
	@Override
	public List<Component> getChildren(){
		return this.componentArrayList;
	}
}
