package com.hjc.learn.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 作者信息
 *
 * @author houjichao
 */
@Getter
@Setter
public class AuthorDTO {

    private String name;

    private Date birthday;
}
