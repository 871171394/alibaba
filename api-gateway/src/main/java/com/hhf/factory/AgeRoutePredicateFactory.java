package com.hhf.factory;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Huang.Hua.Fu
 * @date 2020/6/18 15:24
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<Config> {
    public AgeRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge","maxAge");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 从serverWebExchange获取传入的参数
                String ageStr= serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isNotEmpty(ageStr)){
                    int age=Integer.parseInt(ageStr);
                    return age>config.getMinAge() && age< config.getMaxAge();
                }
                return true;
            }
        };
    }
}
@Data
class Config{
    private int minAge;
    private int maxAge;
}