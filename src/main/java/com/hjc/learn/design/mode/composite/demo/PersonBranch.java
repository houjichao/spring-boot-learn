package com.hjc.learn.design.mode.composite.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houjichao
 */
public class PersonBranch extends PersonMode {
    private List<PersonMode> personModeList = new ArrayList<>();

    public PersonBranch(String name, String sex, int age) {
        super(name, sex, age);
    }

    public void addPerson(PersonMode person) {
        this.personModeList.add(person);
    }

    public List<PersonMode> getPersonModeList() {
        return this.personModeList;
    }

	@Override
	public String getPersonInfo() {
		return super.getPersonInfo()+"\t有子嗣";
	}
}
