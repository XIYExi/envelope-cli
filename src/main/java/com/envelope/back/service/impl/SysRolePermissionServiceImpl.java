package com.envelope.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.envelope.back.mapper.SysRolePermissionMapper;
import com.envelope.back.pojo.SysRolePermission;
import com.envelope.back.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

}
