package cn.ljpc.educms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-21 20:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.ljpc")
public class EduCmsMain {
    public static void main(String[] args) {
        SpringApplication.run(EduCmsMain.class, args);
    }
}