package com.wk.sys.service.base;

import com.github.pagehelper.PageInfo;
import com.wk.sys.entity.base.SysUser;

import java.util.List;

public interface OrderInfoService {
    SysUser loadUserByUsername(String loginName);

    SysUser getUserInfo(String loginName, String pwd);

    String add(SysUser sysUser);

    String addBatch(List<SysUser> sysUserList);

    List<SysUser> findUserList();

    PageInfo<SysUser> findUserListWithPage(Integer pageSize, Integer pageNum);
}
