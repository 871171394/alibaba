package com.hhf.service.impl;

import com.hhf.dao.ProductDao;
import com.hhf.domain.Product;
import com.hhf.domain.User;
import com.hhf.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Override
    public Product findByPid(Integer pid) {

        return productDao.findById(pid).get();
    }

    @Override
    public void reduceInventory(Integer pid, int num) {
        Product product = productDao.findById(pid).get();
        if (product.getStock()<num){
            throw new RuntimeException("库存不足");
        }
        product.setStock(product.getStock()-num);
        productDao.save(product);
    }

    @Override
    public User getUser(User user) {
        System.out.println("get提交，用户为："+user);
        return user;
    }

    @Override
    public User postUser(User user) {
        System.out.println("post提交，用户为："+user);
        return user;
    }

    @Override
    public User jsonUser(User user) {
        System.out.println("json提交，用户为："+user);
        return user;
    }

    @Override
    public User uploadPicture(MultipartFile multipartFile) {
        System.out.println("文件上传！ 文件："+multipartFile);
        User user=new User();
        user.setUid(1);
        user.setUsername("上传文件");
        user.setPassword("上传");
        user.setTelephone("a487489");

        return user;
    }
}
