package com.wk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: vince
 * create at: 2021/1/30 下午5:56
 * @description: 启动类
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication//(exclude = {SeataFeignClientAutoConfiguration.class})
public class Application {

//    @Bean
//    public IRule feignRule() {
//        return new RoundRobinRule();
//    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("启动成功");
    }
}
