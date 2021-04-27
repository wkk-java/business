package com.wk.sys.service.base.impl;

import com.wk.sys.entity.base.UserAccount;
import com.wk.sys.entity.base.UserAccountExample;
import com.wk.sys.entity.ext.UserAccountExt;
import com.wk.sys.mapper.base.UserAccountMapper;
import com.wk.sys.service.base.UserAccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: vince
 * create at: 2021/3/24 下午4:04
 * @description: 用户账户
 */
@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccount getUserAccountByUserId(String userId) {
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        return CollectionUtils.isEmpty(userAccounts) ? null : userAccounts.get(0);
    }

    @Override
    public UserAccount getUserAccountById(String id) {
        return userAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            log.error("传入参数为空!");
            return null;
        }
        if (StringUtils.isEmpty(userAccount.getId()) && StringUtils.isEmpty(userAccount.getUserId())) {
            log.error("传入参数 id 与 userId 不可同时为空!");
        }

        UserAccount userAccountDatabase = null;
        if (!StringUtils.isEmpty(userAccount.getId())) {
            userAccountDatabase = getUserAccountById(userAccount.getId());
        }

        if (!StringUtils.isEmpty(userAccount.getUserId())) {
            userAccountDatabase = getUserAccountByUserId(userAccount.getUserId());
        }
        if (userAccountDatabase == null) {
            log.error("未查询到相关账户信息! 参数:{}", userAccount);
        }
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria()
                .andUserIdEqualTo(userAccountDatabase.getUserId())
                .andAmountEqualTo(userAccountDatabase.getAmount())
                .andBalanceEqualTo(userAccountDatabase.getBalance())
                .andFreezeEqualTo(userAccountDatabase.getBalance());
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
        return null;
    }

    @Override
    public UserAccount addBalance(String userId, BigDecimal money) {
        UserAccount userAccount = getUserAccountByUserId(userId);
        UserAccountExt userAccountExt = (UserAccountExt)userAccount;
        userAccountExt.addMoney(money);
        saveUserAccount(userAccountExt);
        return userAccountExt;
    }

    @Override
    public UserAccount commitPayMoney(String userId, BigDecimal money) {
        UserAccount userAccount = getUserAccountByUserId(userId);
        UserAccountExt userAccountExt = (UserAccountExt)userAccount;
        userAccountExt.payMoney(money);
        saveUserAccount(userAccountExt);
        return userAccountExt;
    }

    @Override
    public UserAccount commitFreezeBalance(String userId, BigDecimal money) {
        log.info("[commitFreezeBalance] 当前 XID: {}", RootContext.getXID());
        UserAccount userAccount = getUserAccountByUserId(userId);
//        UserAccountExt userAccountExt = (UserAccountExt)userAccount;
//        userAccountExt.freezeMoney(money);
        saveUserAccount(userAccount);
        return userAccount;
    }


}
