package com.example.demo.user.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.dto.BaseResponse;
import com.example.demo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("user")
@Api(description = "基础 -- 用户")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "添加测试用户数据", notes = "添加测试用户数据，RabbitMQ方式异步添加登录历史记录")
    @RequestMapping(value = "/loginForRabbitMQTest", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> loginForRabbitMQTest() {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        String[] userNames = {"xiaoming","xiaohong","jingjing","xiaoqiang"};
        String password = "123456";
        int rand = new Random().nextInt(userNames.length);
        if (userService.loginWithRabbitMQ(userNames[rand], password)) {
            baseResponse.setCode(0);
            baseResponse.setMsg("登录成功");
        } else {
            baseResponse.setCode(1);
            baseResponse.setMsg("用户名或密码不正确");
        }
        return baseResponse;
    }
}
