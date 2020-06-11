package com.hhf.dao;

import com.hhf.domain.Product;
import com.hhf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
