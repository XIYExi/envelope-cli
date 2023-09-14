package com.envelope.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.envelope.back.pojo.Permission;

public interface PermissionService extends IService<Permission> {

    Permission selectPermissionByUserId(String userId);

}
