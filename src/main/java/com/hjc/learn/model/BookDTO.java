package com.hjc.learn.model;

import com.hjc.learn.enums.BookType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author houjichao
 */
@Getter
@Setter
public class BookDTO {

    private String bookName;

    /**
     * 有两个属性 name 和 birthday
     */
    private AuthorDTO author;

    /**
     * 一个枚举类型
     */
    private BookType bookType;

    /**
     * 一个类包含 ISBN 和 page
     */
    private BookInfo bookInfo;

}
