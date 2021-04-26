package com.hjc.learn.design.mode.composite.lucency;

import java.util.List;

/**
 * 透明模式实现组合模式
 *
 * 叶子构件
 *
 * @author houjichao
 */
public class Leaf extends Component {

	@Override
	public void add(Component component){
		//空实现
	}

	@Override
	public void remove(Component component){
		//空实现
	}

	@Override
	public List<Component> getChildren(){
		//空实现
		return null;
	}
}
