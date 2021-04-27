package com.wk.sys.entity.ext;

import com.wk.sys.entity.base.SysRole;
import com.wk.sys.entity.base.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author: vince
 * create at: 2021/2/1 下午5:58
 * @description: 用户扩展类
 */
@Data
public class SysUserInfoExt extends SysUser {

    private List<SysRole> sysRoles;
    private List<String> sysRoleNames;
}
