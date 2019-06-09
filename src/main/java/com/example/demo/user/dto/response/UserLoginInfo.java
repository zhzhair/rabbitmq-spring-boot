package com.example.demo.user.dto.response;

import java.sql.Timestamp;

public class UserLoginInfo {
    private Integer userId;
    private Timestamp loginTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
