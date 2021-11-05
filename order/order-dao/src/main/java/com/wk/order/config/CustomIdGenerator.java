//package com.wk.order.config;
//
//import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class CustomIdGenerator extends DefaultIdentifierGenerator implements IdentifierGenerator {
//    //重写自带的雪花算法,可以加入redis或zk等中间件
//    @Override
//    public Long nextId(Object entity) {
//        long id = super.nextId(entity);
//        long random = (long) (Math.random() * 10000);
//        id = id + random;
//        //返回生成的id值即可.
//        return id;
//    }
//
//}