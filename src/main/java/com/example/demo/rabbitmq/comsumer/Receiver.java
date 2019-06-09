package com.example.demo.rabbitmq.comsumer;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.user.daoAction.UserActionMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 异步方法调用：这里是登录异步入库登录历史记录，更加典型的场景是注册成功异步发送邮件,同时给用户送积分
 * @link
 */
/**
 * Created by hxy on 2018/7/2.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class Receiver {

    @Resource
    private UserActionMapper userActionMapper;
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_QUEUE"})
    public void message(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String userStr = new String(message.getBody());
        JSONObject jsonObject = JSONObject.parseObject(userStr);
        if(!jsonObject.isEmpty()){
            userActionMapper.addLoginHistory(jsonObject.getInteger("userId"));
        }
    }

    /**
     * FANOUT广播队列监听一.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
//    @RabbitListener(queues = {"FANOUT_QUEUE_A"})
//    public void on(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.debug("FANOUT_QUEUE_A " + new String(message.getBody()));
//    }

    /**
     * FANOUT广播队列监听二.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception   这里异常需要处理
     */
//    @RabbitListener(queues = {"FANOUT_QUEUE_B"})
//    public void t(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.debug("FANOUT_QUEUE_B " + new String(message.getBody()));
//    }

}
