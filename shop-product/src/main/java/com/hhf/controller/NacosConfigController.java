package com.hhf.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态配置文件
 * @author Huang.Hua.Fu
 * @date 2020/6/19 9:26
 */
@RestController
@RefreshScope
public class NacosConfigController {
    @Value("${config.env}")
    private String env;

    /**
     * 同一微服务的不同环境下共享配置
     */
    @GetMapping("/nacos-config-test3")
    public String nacosConfigTest3(){
        return env;
    }

    /**
     * 注解方式
     * @return
     */
    /*@Value("${config.appName}")
    private String appName;


    @GetMapping("/nacos-config-test2")
    public String nacosConfigTest2(){
        return appName;
    }*/


    /**
     * 硬编码方式
     */
  /*  @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/nacos-config-test1")
    public String nacosConfigTest1(){
        return applicationContext.getEnvironment().getProperty("config.appName");
    }*/
}
