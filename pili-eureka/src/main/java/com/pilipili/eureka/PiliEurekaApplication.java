package com.pilipili.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述： runner
 *
 * @author ChenJianChuan
 * @date 2019/1/30　16:12
 */
@EnableEurekaServer
@SpringBootApplication
public class PiliEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiliEurekaApplication.class, args);
    }
}
