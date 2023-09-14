package com.envelope.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.envelope.back.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> select();

}
