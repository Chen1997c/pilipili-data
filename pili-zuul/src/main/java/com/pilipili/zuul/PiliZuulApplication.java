package com.pilipili.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 描述： runner
 *
 * @author ChenJianChuan
 * @date 2019/1/30　16:14
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class PiliZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiliZuulApplication.class, args);
    }
}
