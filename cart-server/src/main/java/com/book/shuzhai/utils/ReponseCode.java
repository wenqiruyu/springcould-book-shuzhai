package com.book.shuzhai.utils;

public enum ReponseCode {
    ERROR(0, "ERROR"),
    SUCCESS(1, "SUCCESS"),
    NEED_LOGIN(3, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");
    // illegal_argument 非法参数
    private final int code;
    private final String desc;

    ReponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
