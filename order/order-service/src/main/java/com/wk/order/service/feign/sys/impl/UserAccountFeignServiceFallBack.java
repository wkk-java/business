//package com.wk.order.service.feign.sys.impl;
//
//import com.wk.entity.com.wk.entity.exception.BusinessRuntimeException;
//import com.wk.entity.com.wk.entity.exception.ExceptionType;
//import com.wk.order.service.feign.sys.UserAccountFeignService;
//import com.wk.sys.entity.base.UserAccount;
//import com.wk.sys.entity.ext.UserAccountExt;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * @author: vince
// * create at: 2021/3/24 下午5:38
// * @description:
// */
//@Slf4j
//@Service
//public class UserAccountFeignServiceFallBack implements UserAccountFeignService , FallbackFactory<UserAccountFeignService> {
//
//    private Throwable throwable;
//
//    @Override
//    public UserAccountFeignService create(Throwable throwable) {
//        this.throwable = throwable;
//        return null;
//    }
//    @Override
//    public UserAccount getUserAccount(String userId) {
//        log.error("获取账户信息失败了!");
//        return null;
//    }
//
//    @Override
//    public UserAccount addMoney(UserAccountExt userAccount) {
//        log.error("账户加值失败了!");
//        return null;
//    }
//
//    @Override
//    public UserAccount payMoney(UserAccountExt userAccount) {
//        log.error("账户提交支付失败了!");
//        return null;
//    }
//
//    @Override
//    public UserAccount freezeMoney(UserAccountExt userAccount) {
//        log.error("账户冻结金额失败了!");
//        throw new BusinessRuntimeException(ExceptionType.REMARK, "账户冻结金额失败了!");
//    }
//}
