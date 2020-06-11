package com.hhf.controller;

import com.alibaba.fastjson.JSON;
import com.hhf.domain.Order;
import com.hhf.domain.Product;
import com.hhf.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@AllArgsConstructor
public class OrderController {

    private RestTemplate restTemplate;

    private OrderService orderService;

    private DiscoveryClient discoveryClient;

    // 下单--自定义负载均衡
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求,接下来调用商品微服务查询此商品信息",pid);
        //调用商品微服务，查询商品信息
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        // 随机选择
        int index = new Random().nextInt(instances.size());
        ServiceInstance instance = instances.get(index);


        Product product = restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/"+pid, Product.class);
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(product));

        Order order=new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //下单（创建订单）
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }

    /*@RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求,接下来调用商品微服务查询此商品信息",pid);
        //调用商品微服务，查询商品信息
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);


        Product product = restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/"+pid, Product.class);
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(product));

        Order order=new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //下单（创建订单）
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }*/

    /*@RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求,接下来调用商品微服务查询此商品信息",pid);
        //调用商品微服务，查询商品信息
        Product product = restTemplate.getForObject("http://localhost:8081/product/"+pid, Product.class);
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(product));

        Order order=new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //下单（创建订单）
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }*/
}
