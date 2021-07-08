package cn.ljpc.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-22 19:20
 */
@EnableDiscoveryClient//nacos服务的注册
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "cn.ljpc")
public class EduMsmMain {
    public static void main(String[] args) {
        SpringApplication.run(EduMsmMain.class, args);
    }
}