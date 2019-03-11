package com.pilipili.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 描述： runner
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
public class PiliProviderMdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiliProviderMdcApplication.class, args);
    }
}
