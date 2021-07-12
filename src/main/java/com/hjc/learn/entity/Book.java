package com.hjc.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * BookEntity
 *
 * @author houjichao
 */
@Getter
@Setter
@AllArgsConstructor
public class Book {

    private String bookName;

    private String authorName;

    private Date authorBirthday;

    /**
     * 一个Json字符串
     */
    private String bookInformation;

    private Byte type;

}
