package com.hjc.learn.design.mode.composite.demo;

/**
 * ②树叶构件
 *
 * @author houjichao
 */
public class PersonLeaf extends PersonMode {

	/**
	 * 写一个构造函数
	 * @param name
	 * @param sex
	 * @param age
	 */
	public PersonLeaf(String name, String sex, int age) {
		super(name, sex, age);
	}

	@Override
	public String getPersonInfo() {
		return super.getPersonInfo() + "\t无子嗣";
	}
}
