package com.hjc.learn.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author houjichao
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5915747598060705898L;

    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "202001")
    private String name;

    private Integer age;
    private String email;
}
