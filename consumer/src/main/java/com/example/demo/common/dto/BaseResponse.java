package com.example.demo.common.dto;

public class BaseResponse<T> {
    private int code = -4;
    private String msg = "未知错误";
    private T data;
    public void ok(){
        this.code = 0;
        this.msg = "success";
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
