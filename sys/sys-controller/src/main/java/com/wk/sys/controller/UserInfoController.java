package com.wk.sys.controller;

import com.wk.order.entity.OrderInfo;
import com.wk.order.entity.ext.OrderInfoExt;
import com.wk.sys.controller.config.PropertiesMap;
import com.wk.sys.entity.base.SysUser;
import com.wk.sys.service.base.OrderInfoService;
import com.wk.sys.service.feign.OrderInfoFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author: vince
 * create at: 2019/12/31 18:29
 * @description: 用户控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private OrderInfoFeignService orderInfoFeignService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private PropertiesMap config;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/getConfig")
    public String getConfig() {
        log.info("getConfig:{}", config.getMask());
        return config.getMask();
    }

    @GetMapping(value = "getIp")
    public String getIpInfo(@RequestHeader("token") String token, @RequestHeader("userId") String userId) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://" + address.getHostAddress() + ":" + this.port + ",token:" + token + ",userId:" + userId;
    }

    @GetMapping(value = "/findOrderList")
    public List<OrderInfo> findOrderList() {
        return orderInfoFeignService.findOrderList();
    }

    @GetMapping(value = "/findOrderDetailList")
    public List<OrderInfoExt> findOrderDetailList() {
        log.info("请求过来了,time: {}, threadName: {}, threadId: {}", System.currentTimeMillis(), Thread.currentThread().getName(), Thread.currentThread().getId());
        return orderInfoFeignService.findOrderDetailList();
    }

    @GetMapping(value = "/loadUserByUsername")
    public SysUser loadUserByUsername(@RequestParam("loginName") String loginName) {
        return orderInfoService.loadUserByUsername(loginName);
    }

    @GetMapping(value = "/getUserInfo")
    public SysUser getUserInfo(@RequestParam("loginName") String loginName, @RequestParam("pwd") String pwd) {
        return orderInfoService.getUserInfo(loginName, pwd);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody SysUser sysUser) {
        return orderInfoService.add(sysUser);
    }

    @PostMapping(value = "/addBatch")
    public String addBatch(@RequestBody List<SysUser> sysUserList) {
        return orderInfoService.addBatch(sysUserList);
    }

    @GetMapping(value = "/findUserList")
    public List<SysUser> findUserList() {
        return orderInfoService.findUserList();
    }

//    @GetMapping(value = "/getUserPageList")
//    public PageInfo<SysUser> findUserListWithPage(@RequestParam(value = "pageSize") Integer pageSize,
//                                                  @RequestParam(value = "pageNum") Integer pageNum) {
//        return orderInfoService.findUserListWithPage(pageSize, pageNum);
//    }


}
