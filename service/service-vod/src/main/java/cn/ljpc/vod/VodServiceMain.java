package cn.ljpc.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-20 16:41
 */
@EnableDiscoveryClient// 服务的注册
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "cn.ljpc")
public class VodServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(VodServiceMain.class, args);
    }
}
