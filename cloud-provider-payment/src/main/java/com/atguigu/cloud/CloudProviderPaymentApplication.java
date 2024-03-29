package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.cloud.mapper")
public class CloudProviderPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentApplication.class);
    }
}