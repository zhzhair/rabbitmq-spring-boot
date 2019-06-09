package com.example.demo.user.service.impl;

import com.example.demo.user.daoAction.UserActionMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 异步方法调用：这里是登录异步入库登录历史记录，更加典型的场景是注册成功异步发送邮件
 */
@Component
public class AsyncTask {
    @Resource
    private UserActionMapper userActionMapper;

    @Async
    public void addLoginHistory(Integer userId) {
        userActionMapper.addLoginHistory(userId);
    }
}
