package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@MapperScan(basePackages = "com.atguigu.cloud.mapper")
public class CloudProviderPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentApplication.class);
    }
}