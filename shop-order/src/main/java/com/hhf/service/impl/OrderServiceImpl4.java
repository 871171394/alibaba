package com.hhf.service.impl;

import com.hhf.dao.OrderDao;
import com.hhf.dao.TxLogDao;
import com.hhf.domain.Order;
import com.hhf.domain.TxLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.Local;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl4 {
    private OrderDao orderDao;
    private TxLogDao txLogDao;
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderBefore(Order order){
        String txId = UUID.randomUUID().toString();
        // 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId",txId).build(),
                order
        );
    }

    /**本地事务 */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(String txId,Order order){
        // 本地事务代码
        orderDao.save(order);

        //记录日志到数据库，回查使用
        TxLog txLog=new TxLog();
        txLog.setTxLogId(txId);
        txLog.setContent("事务测试");
        txLog.setDate(LocalDateTime.now());
        txLogDao.save(txLog);
    }


}
