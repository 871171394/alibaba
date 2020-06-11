package com.hhf.service.impl;

import com.hhf.dao.ProductDao;
import com.hhf.domain.Product;
import com.hhf.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Override
    public Product findByPid(Integer pid) {

        return productDao.findById(pid).get();
    }
}
