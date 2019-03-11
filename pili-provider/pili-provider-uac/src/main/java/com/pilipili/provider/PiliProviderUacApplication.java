package com.pilipili.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 描述： runner
 *
 * @author ChenJianChuan
 * @date 2019/1/31　14:22
 */
@EnableJpaAuditing
@SpringBootApplication
@EnableEurekaClient
public class PiliProviderUacApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiliProviderUacApplication.class, args);
    }
}
