package com.hhf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Huang.Hua.Fu
 * @date 2020/6/22 15:59
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自己的包名
                .apis(RequestHandlerSelectors.basePackage("com.hhf.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("springboot利用swagger构建api文档")
                // 描述
                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
                // 相关服务地址
                .termsOfServiceUrl("http://blog.csdn.net/saytime")
                // 版本说明
                .version("1.0")
                .build();
    }
}
