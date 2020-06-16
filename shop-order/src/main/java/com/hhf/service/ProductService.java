package com.hhf.service;

import com.hhf.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Huang.Hua.Fu
 * @date 2020/6/12 10:36
 */
@FeignClient("service-product") // 声明调用的提供者的name
public interface ProductService {
    /**
     * 根据产品id查询对应的产品
     * @param pid 产品id
     * @return 产品参数
     */
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
