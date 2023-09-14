package com.envelope.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.envelope.back.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 通过 userid 得到 sys_user_role 中的的 roleId
     * 再根据 roleId 去 sys_role_permission 中查 permissionId
     * 最后根据 permissionId 在 permission 表中查到当前登陆者的权限
     * @param userId
     * @return
     */
    @Select("<script>" +
            "select * from `permission` where permission.id = (" +
            "select permissionId from `sys_role_permission` where sys_role_permission.roleId in (" +
            "select roleId from `sys_user_role` where sys_user_role.userId = #{userId}" +
            ")" +
            ")" +
            "</script>")
    Permission selectPermissionByUserId(String userId);

}
