package com.hhf.service;

import com.hhf.domain.Product;
import com.hhf.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    //根据pid查询商品信息
    Product findByPid(Integer pid);

    // 根据pid减少库存
    void reduceInventory(Integer pid,int num);

    User getUser(User user);

    User postUser(User user);

    User jsonUser(User user);

    User uploadPicture(MultipartFile multipartFile);
}
