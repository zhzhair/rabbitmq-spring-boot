package com.example.demo.user.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Integer login(@Param("userName") String userName, @Param("password") String password);
}