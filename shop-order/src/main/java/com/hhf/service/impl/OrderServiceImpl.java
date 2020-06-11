package com.hhf.service.impl;


import com.hhf.dao.OrderDao;
import com.hhf.domain.Order;
import com.hhf.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
