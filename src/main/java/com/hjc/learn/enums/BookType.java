package com.hjc.learn.enums;

import lombok.Getter;

/**
 * 书类型
 *
 * @author houjichao
 */
@Getter
public enum BookType {

    /**
     * 小说
     */
    NOVEL((byte) 1, "NOVEL"),

    /**
     * 小说
     */
    COMICS((byte) 1, "COMICS");

    private Byte code;
    private String desc;

    BookType(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static BookType getByCode(Byte code) {
        for (BookType e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
