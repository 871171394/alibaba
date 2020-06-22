package com.hhf.controller;

import com.alibaba.fastjson.JSON;
import com.hhf.domain.Order;
import com.hhf.domain.Product;
import com.hhf.domain.User;
import com.hhf.service.OrderService;
import com.hhf.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author
 */
@RestController
@Slf4j
@AllArgsConstructor
public class OrderController {

    private RestTemplate restTemplate;

    private OrderService orderService;

    private DiscoveryClient discoveryClient;

    private ProductService productService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        return orderService.createOrder(pid);
    }

    @GetMapping("/order/user")
    public User getUser(User user){
        User user1 = productService.getUser(user);
       return  user1;
    }

    @PostMapping("/order/user")
    public User postUser(User user){
        User user1 = productService.postUser(user);
        return user1;
    }

    @DeleteMapping("/order/user")
    public User jsonUser(@RequestBody User user){
        User user1 = productService.jsonUser(user);
        return user1;
    }
    /**
     * 下单--自定义负载均衡
     */
   /* @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求,接下来调用商品微服务查询此商品信息",pid);

        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(product));
        // 模拟一次网络延时
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order=new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //下单（创建订单）
        //orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }*/


    /**
     * 下单--自定义负载均衡
     */
    /*@RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求,接下来调用商品微服务查询此商品信息",pid);

        Product product = restTemplate.getForObject("http://service-product/product/"+pid, Product.class);
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

    /**
     * 下单--自定义负载均衡
     *//*
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
    }*/

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
