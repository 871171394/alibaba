package com.hhf.controller;

import com.alibaba.fastjson.JSON;
import com.hhf.domain.Order;
import com.hhf.domain.Product;
import com.hhf.service.OrderService;
import com.hhf.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang.Hua.Fu
 * @date 2020/7/8 15:08
 */
@RestController
@Slf4j
@AllArgsConstructor
public class OrderController3 {
    private OrderService orderService;
    private ProductService productService;
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/order1/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        // 通过fegin调用商品微服务
        Product product = productService.findByPid(pid);
        if (product == null) {
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        log.info(">>商品信息，查询结果：" + JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);

        // 下单成功之后，将消息放到mq中
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }
}
