package com.as.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡算法， provider-goods 无集群可以不用配置
 */
@Configuration
public class LoadBalanceRule {
    @Bean
    public IRule iRule(){
        return new WeightedResponseTimeRule();
    }
}
