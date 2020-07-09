package com.hhf.service.impl;

import com.hhf.dao.TxLogDao;
import com.hhf.domain.Order;
import com.hhf.domain.TxLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceImpl4Listener implements RocketMQLocalTransactionListener {

    private TxLogDao txLogDao;
    private OrderServiceImpl4 orderServiceImpl4;

    /**执行本地事务 */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        try {
            orderServiceImpl4.createOrder((String) msg.getHeaders().get("txId"),(Order) arg);
            log.info(">>执行成功");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.info(">>执行失败");
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    /**消息回查 */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        TxLog txLog = txLogDao.findById((String) msg.getHeaders().get("txId")).get();
        if (txLog == null){
            log.info(">>回调COMMIT");
            return  RocketMQLocalTransactionState.COMMIT;
        }else {
            log.info(">>回调ROLLBACK");
            return RocketMQLocalTransactionState.ROLLBACK;
        }


    }
}
