package com.example.demo.user.daoAction;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserActionMapper {

    void addLoginHistory(@Param("userId") Integer userId);
}