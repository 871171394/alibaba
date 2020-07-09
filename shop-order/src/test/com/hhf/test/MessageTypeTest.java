package com.hhf.test;

import com.hhf.OrderApplication;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author Huang.Hua.Fu
 * @date 2020/7/8 17:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步消息
     */
    @Test
    public void testSyncSend() {
        //参数一：topic，如果想添加tag 可以使用“topic:tag”的写法
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-1", "这是一条同步消息");
        System.out.println(sendResult);
    }

    /**
     * 异步消息
     */
    @Test
    public void testAsyncSend() throws InterruptedException {

        // 参数一: topic, 如果想添加tag  可以使用"topic:tag"的写法        
        // 参数二: 消息内容        
        // 参数三: 回调函数, 处理返回结果
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //成功响应的回调
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                //异常响应的回调
                System.out.println(throwable);
            }
        });
        System.out.println("==================");
        Thread.sleep(300000000);
    }
    /**
     * 单向消息
     */
    @Test
    public void testOneWay(){
        rocketMQTemplate.sendOneWay("test-topic-1","这是一条单向消息");
    }
    @Test
    public void sendOneWayOrderly(){
        rocketMQTemplate.sendOneWayOrderly("test-topic-1","这是一条单向消息","xx");
    }
}
