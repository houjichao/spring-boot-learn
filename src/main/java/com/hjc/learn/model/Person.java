package com.hjc.learn.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author houjichao
 */
@ApiModel("人员对象实体")
@Data
public class Person {

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "子孙")
    private Child child;

    @ApiModelProperty(value = "子孙列表")
    private List<Child> childList;

    @Data
    public static class Child{

        @ApiModelProperty(value = "性别")
        private String sex;

        @ApiModelProperty(value = "子孙年龄")
        private Integer childAge;
    }
}
