package com.wk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: vince
 * create at: 2019/12/31 18:39
 * @description: 应用启动类
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication//(exclude = {SeataFeignClientAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("启动成功!");
    }

//    @Bean
//    public IRule feignRule() {
//        return new RoundRobinRule();
//    }
}