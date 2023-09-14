package com.envelope.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.envelope.back.mapper.SysUserRoleMapper;
import com.envelope.back.pojo.SysUserRole;
import com.envelope.back.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

}
