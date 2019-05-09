package com.pilipili.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述： runner
 *
 * @author ChenJianChuan
 * @date 2019/2/2　14:35
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@ServletComponentScan
public class PiliSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiliSecurityApplication.class, args);
    }
}
