//package com.wk.product.mapper;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//public class DruidCofnig {
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druidDataSource(){
//        return new DruidDataSource();
//    }
//
//    //后台监控,相当于web.xml
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
//
//
//        //后台需要有人登录，账号密码配置
//        HashMap<String,String> initParameters = new HashMap<>();
//        //增加配置
//        initParameters.put("loginUsername","admin");//登录的key是固定的 loginUsername loginPassword
//        initParameters.put("loginPassword","123456");
//
//        //允许谁可以访问
//        initParameters.put("allow","");
//
//        //禁止谁能访问
//        initParameters.put("yaoyao","192.168.11.123");
//
//        bean.setInitParameters(initParameters);//设置初始化参数
//        return bean;
//
//    }
//
//}
