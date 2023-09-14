package com.envelope.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.envelope.back.mapper.PermissionMapper;
import com.envelope.back.pojo.Permission;
import com.envelope.back.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission selectPermissionByUserId(String userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }
}
