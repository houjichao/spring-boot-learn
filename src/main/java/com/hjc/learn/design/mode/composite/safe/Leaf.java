package com.hjc.learn.design.mode.composite.safe;

/**
 * 安全模式实现组合模式
 *
 * 叶子构件
 *
 * @author houjichao
 */
public class Leaf extends Component {

	/**
	 * 可以覆写父类方法
	 */
	 @Override
	 public void operation(){
		 System.out.println("这是叶子节点");
	 }

}
