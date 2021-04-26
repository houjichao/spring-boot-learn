package com.hjc.learn.design.mode.composite.demo;

/**
 * ①抽象构件抽象族员类
 *
 * @author houjichao
 */
public abstract class PersonMode {

	/**
	 * 人名
	 */
	private String name;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 年龄
	 */
	private int age;

	public PersonMode(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	/**
	 * 个人信息
	 * @return 个人信息
	 */
	public String getPersonInfo(){
		return "姓名:"+name+"\t性别:"+sex+"\t年龄:"+age;
	}
}
