package com.hhf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang.Hua.Fu
 * @date 2020/6/16 17:29
 */
@RestController
public class OrderController2 {
    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2(){
        return "message2";
    }
}
