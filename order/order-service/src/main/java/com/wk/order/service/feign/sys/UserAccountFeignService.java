package com.wk.order.service.feign.sys;

import com.wk.sys.entity.base.UserAccount;
import com.wk.sys.entity.ext.UserAccountExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sys-server")
public interface UserAccountFeignService {

    @RequestMapping(value = "/userAccount/getUserAccount", method = RequestMethod.GET)
    UserAccount getUserAccount(@RequestParam("userId") String userId);

    @RequestMapping(value = "/userAccount/addMoney", method = RequestMethod.POST)
    UserAccount addMoney(@RequestBody UserAccountExt userAccount);

    @RequestMapping(value = "/userAccount/payMoney", method = RequestMethod.POST)
    UserAccount payMoney(@RequestBody UserAccountExt userAccount);

    @RequestMapping(value = "/userAccount/freezeMoney", method = RequestMethod.POST)
    UserAccount freezeMoney(@RequestBody UserAccountExt userAccount);


}
