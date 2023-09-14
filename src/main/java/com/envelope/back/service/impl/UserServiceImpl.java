package com.envelope.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.envelope.back.mapper.UserMapper;
import com.envelope.back.pojo.User;
import com.envelope.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> select() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectList(queryWrapper);
    }
}
