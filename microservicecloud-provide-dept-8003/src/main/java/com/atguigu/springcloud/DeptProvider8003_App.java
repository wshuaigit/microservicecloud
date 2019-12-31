package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangzhao
 * @date 2019/12/30 21:54
 */
@SpringBootApplication

// 本服务启动后会自动注册进 eureka 服务中
@EnableEurekaClient

// 服务发现
@EnableDiscoveryClient
public class DeptProvider8003_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8003_App.class,args);
    }
}
