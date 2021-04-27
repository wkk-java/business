package com.wk.sys.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.exception.ExceptionType;
import com.wk.sys.entity.base.SysRole;
import com.wk.sys.entity.base.SysRoleExample;
import com.wk.sys.entity.base.SysUser;
import com.wk.sys.entity.base.SysUserExample;
import com.wk.sys.entity.base.SysUserRole;
import com.wk.sys.entity.base.SysUserRoleExample;
import com.wk.sys.entity.ext.SysUserInfoExt;
import com.wk.sys.mapper.base.SysRoleMapper;
import com.wk.sys.mapper.base.SysUserMapper;
import com.wk.sys.mapper.base.SysUserRoleMapper;
import com.wk.sys.service.base.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: vince
 * create at: 2021/2/1 下午6:13
 * @description:
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser loadUserByUsername(String loginName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }
        SysUser sysUser = sysUsers.get(0);
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return sysUser;
        }
        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(roleIdList);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<String> sysRoleNames = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());

        SysUserInfoExt sysUserExt = new SysUserInfoExt();
        BeanUtils.copyProperties(sysUser, sysUserExt);
        sysUserExt.setSysRoles(sysRoles);
        sysUserExt.setSysRoleNames(sysRoleNames);

        return sysUserExt;
    }

    @Override
    public SysUser getUserInfo(String loginName, String pwd) {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            log.error("错误信息：{0}", ex);
        }
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }
        SysUser sysUser = sysUsers.get(0);
        if ("1".equals(sysUser.getIsFirstLoginModifyPasswrod())) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "还没修改密码...");
        }
        if (!pwd.equals(sysUser.getPassword())) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "密码错误");
        }
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return sysUser;
        }
        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(roleIdList);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<String> sysRoleNames = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());


        SysUserInfoExt sysUserExt = new SysUserInfoExt();
        BeanUtils.copyProperties(sysUser, sysUserExt);
        sysUserExt.setSysRoles(sysRoles);
        sysUserExt.setSysRoleNames(sysRoleNames);

        return sysUserExt;
    }

    @Override
    public String add(SysUser sysUser) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(sysUser.getLoginName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }

        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setDelFlag(false);
        sysUserMapper.insertSelective(sysUser);
        return "success";
    }

    @Override
    public String addBatch(List<SysUser> sysUserList) {
        if (sysUserList != null) {
            for (SysUser sysUser : sysUserList) {
                sysUser.setId(UUID.randomUUID().toString());
                sysUser.setDelFlag(false);
                sysUserMapper.insertSelective(sysUser);
            }
        }
        return "success";
    }

    @Override
    public List<SysUser> findUserList() {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andDelFlagEqualTo(false);
        return sysUserMapper.selectByExample(sysUserExample);
    }

    @Override
    public PageInfo<SysUser> findUserListWithPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andDelFlagEqualTo(false);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return new PageInfo<>(sysUsers);
    }


}
