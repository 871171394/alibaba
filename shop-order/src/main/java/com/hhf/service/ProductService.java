package com.hhf.service;

import com.hhf.domain.Product;
import com.hhf.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Huang.Hua.Fu
 * @date 2020/6/12 10:36
 */
@FeignClient("service-product") // 声明调用的提供者的name
public interface ProductService {
    /**
     * 根据产品id查询对应的产品
     *
     * @param pid 产品id
     * @return 产品参数
     */
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);

    /**
     * 根据产品id减少商品数量
     * @param pid 上盘id
     * @param num 减少数量
     */
    @PutMapping(value = "/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid,@RequestParam("num") int num);

    @GetMapping("/product/user")
    User getUser(@RequestBody User user);

    @PostMapping("/product/user")
    User postUser(@RequestBody User user);

    @DeleteMapping("/product/user")
    User jsonUser(@RequestBody User user);
}
