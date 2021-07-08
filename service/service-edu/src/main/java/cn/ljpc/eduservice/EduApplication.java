package cn.ljpc.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-14 21:30
 */
@EnableDiscoveryClient// 服务的注册
@EnableFeignClients//服务的调用
@SpringBootApplication
@ComponentScan(basePackages = {"cn.ljpc"}) //需要扫描 SwaggerConfig
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}