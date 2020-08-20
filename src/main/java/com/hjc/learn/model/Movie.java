package com.hjc.learn.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author houjichao
 */
@ApiModel("电影对象实体")
@Data
public class Movie {

    @ApiModelProperty(value = "电影名")
    @NotBlank(message = "201001")
    private String name;

}
