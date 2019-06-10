package com.example.demo.user.service.impl;

import com.example.demo.user.dao.UserMapper;
import com.example.demo.user.dto.response.UserLoginInfo;
import com.example.demo.user.service.UserService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean loginWithRabbitMQ(@NotBlank String userName, @NotBlank String password) {
        Integer userId = userMapper.login(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (userId != null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUserId(userId);
            userLoginInfo.setLoginTime(timestamp);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_KEY", userLoginInfo, correlationData);
            return true;
        }
        return false;
    }
}
