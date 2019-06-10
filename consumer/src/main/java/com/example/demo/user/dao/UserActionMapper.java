package com.example.demo.user.dao;

import org.apache.ibatis.annotations.Param;

public interface UserActionMapper {

    void addLoginHistory(@Param("userId") Integer userId);
}