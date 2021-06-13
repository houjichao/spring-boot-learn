package com.hjc.learn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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

    @TableId
    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "202001")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableLogic
    private Integer deleted;

}
