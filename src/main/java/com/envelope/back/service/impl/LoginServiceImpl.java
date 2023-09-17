package com.envelope.back.service.impl;

import com.envelope.back.mapper.UserMapper;
import com.envelope.back.pojo.User;
import com.envelope.back.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;


    @Cacheable(value = "user-key")
    @Override
    public User getUser(String id) {
        return this.getUserInfo(id);
    }

    @CacheEvict(value = "user-key", allEntries = true)
    @Override
    public void delUser(String id) {
        this.delUserInfo(id);
    }

    public void delUserInfo(String id) {
        log.info("LoginServiceImpl -> delUserInfo: 删除 {} 用户数据执行了！！", id);
    }


    private User getUserInfo(String id) {
        log.info("LoginServiceImpl -> getUserInfo: 查询 {} 用户数据", id);
        return userMapper.selectById(id);
    }
}
