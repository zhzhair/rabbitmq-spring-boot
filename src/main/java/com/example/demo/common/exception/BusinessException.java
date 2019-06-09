package com.example.demo.common.exception;

/**
 * @author Created by xuyf22 on 2017/3/30.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
