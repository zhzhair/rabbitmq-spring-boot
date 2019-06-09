package com.example.demo.user.service;

public interface UserService {

    boolean login(String userName, String password);

    boolean loginWithRabbitMQ(String userName, String password);
}
