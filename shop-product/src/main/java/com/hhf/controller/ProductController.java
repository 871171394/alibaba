package com.hhf.controller;

import com.alibaba.fastjson.JSON;
import com.hhf.domain.Product;
import com.hhf.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    /**
     * 商品信息查询
     */
    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid")Integer pid){
        log.info("接下来要进行{}号商品的信息查询",pid);
        Product product=productService.findByPid(pid);
        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(product));
        return product;
    }
}
