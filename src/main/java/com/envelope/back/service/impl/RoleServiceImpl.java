package com.envelope.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.envelope.back.mapper.RoleMapper;
import com.envelope.back.pojo.Role;
import com.envelope.back.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

}
