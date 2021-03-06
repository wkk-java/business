package com.wk.sys.controller;

import com.wk.entity.exception.BusinessSeataException;
import com.wk.entity.result.ResultView;
import com.wk.sys.entity.base.UserAccount;
import com.wk.sys.entity.ext.UserAccountExt;
import com.wk.sys.service.base.UserAccountService;
import io.seata.core.model.Result;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: vince
 * create at: 2021/3/24 下午5:06
 * @description:
 */
@Slf4j
@Api(tags = "用户账户API")
@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation(value = "获取账户信息")
    @GetMapping(value = "/getUserAccount1")
    public ResultView<UserAccount> getUserAccount1(@RequestParam("userId") String userId) {
        UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
        return ResultView.success(userAccount);
    }
    @ApiOperation(value = "获取账户信息")
    @GetMapping(value = "/getUserAccount")
    public UserAccount getUserAccount(@RequestParam("userId") String userId) {
        return userAccountService.getUserAccountByUserId(userId);
    }

    @ApiOperation(value = "增加余额")
    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public UserAccount addMoney(@RequestBody UserAccountExt userAccount) {
        return userAccountService.addBalance(userAccount.getUserId(), userAccount.getMoney());
    }

    @RequestMapping(value = "/payMoney", method = RequestMethod.POST)
    public UserAccount payMoney(@RequestBody UserAccountExt userAccount) throws BusinessSeataException {
        return userAccountService.commitPayMoney(userAccount.getUserId(), userAccount.getMoney());
    }

    @RequestMapping(value = "/freezeMoney", method = RequestMethod.POST)
    public UserAccount freezeMoney(@RequestBody UserAccountExt userAccount) throws BusinessSeataException {
        return userAccountService.commitFreezeBalance(userAccount.getUserId(), userAccount.getMoney());
    }


}
