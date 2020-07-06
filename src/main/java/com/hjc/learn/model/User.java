package com.hjc.learn.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author houjichao
 */
@ApiModel("用户对象实体")
@Data
public class User {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "201001")
    private String name;

}
