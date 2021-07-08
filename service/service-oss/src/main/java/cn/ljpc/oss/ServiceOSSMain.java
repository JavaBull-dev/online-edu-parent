package cn.ljpc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 17:11
 */
@EnableDiscoveryClient// 服务的注册
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//没有数据库的连接，所以不需要数据源的自动配置类
@ComponentScan(basePackages = "cn.ljpc")
public class ServiceOSSMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOSSMain.class, args);
    }
}
