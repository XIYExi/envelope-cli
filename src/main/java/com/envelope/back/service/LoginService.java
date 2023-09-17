package com.envelope.back.service;

import com.envelope.back.pojo.User;

public interface LoginService {
    /**
     * 缓存查询
     *
     * @param id 用户id
     * @return 用户数据
     */
    User getUser(String id);

    /**
     * 删除用户缓存
     *
     * @param id 用户id
     */
    void delUser(String id);
}
