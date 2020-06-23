package com.hhf.service.impl;


import com.alibaba.fastjson.JSON;
import com.hhf.dao.OrderDao;
import com.hhf.domain.Order;
import com.hhf.domain.Product;
import com.hhf.service.OrderService;
import com.hhf.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ProductService productService;


    @Override
    @GlobalTransactional
    public Order createOrder(Integer pid) {
        //1. 调用商品微服务，查询商品信息
        Product product = productService.findByPid(pid);
        log.info("查询到{}号的商品信息，内容是:{}", pid, JSON.toJSONString(product));

        //2. 下单（创建订单）
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        productService.reduceInventory(pid, order.getNumber());
        return order;
    }

}
