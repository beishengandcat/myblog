package com.lz.exception;

import com.lz.enums.AppHttpCodeEnum;

/**
 * 功能描述：统一的异常处理
 *
 * @Author: 李振
 * @Date: 2022/12/15 14:29
 **/
public class SystemException extends RuntimeException{
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
