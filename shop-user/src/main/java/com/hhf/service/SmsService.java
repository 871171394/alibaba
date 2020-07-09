package com.hhf.service;

import com.alibaba.fastjson.JSON;
import com.hhf.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Huang.Hua.Fu
 * @date 2020/7/8 15:44
 */
@Slf4j
@Service // 需要注入容器
@RocketMQMessageListener(
        consumerGroup = "xx",//消费者分组  group：不用和生产者group相同
        topic = "tx_topic",//要消费的主题  topic：和消费者发送的topic相同
        consumeMode = ConsumeMode.CONCURRENTLY, //消费模式:无序和有序
        messageModel = MessageModel.BROADCASTING //消息模式:广播和集群,默认是集群
        //泛型必须和接收的消息类型相同
)
public class SmsService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }
}
