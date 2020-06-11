package com.hhf.dao;

import com.hhf.domain.Order;
import com.hhf.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
