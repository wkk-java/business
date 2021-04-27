package com.wk.sys.service.base;

import com.wk.sys.entity.base.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {
    UserAccount getUserAccountByUserId(String userId);

    UserAccount getUserAccountById(String id);

    UserAccount saveUserAccount(UserAccount userAccount);

    UserAccount addBalance(String userId, BigDecimal money);

    UserAccount commitPayMoney(String userId, BigDecimal money);

    UserAccount commitFreezeBalance(String userId, BigDecimal money);

}
