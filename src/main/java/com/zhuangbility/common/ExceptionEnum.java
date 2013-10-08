package com.zhuangbility.common;

public enum ExceptionEnum {

    ERROR_404("ERROR_404", "404,你懂的"),
    UNKNOW_ERROR("UNKNOW_ERROR", "未知异常");

    private String code;

    private String message;

    private ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return "ERROR." + code;
    }

}
