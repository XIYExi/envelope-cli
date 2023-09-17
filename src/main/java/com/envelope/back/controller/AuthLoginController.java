package com.envelope.back.controller;

import com.envelope.back.common.utils.R;
import com.envelope.back.controller.vo.AuthLoginForm;
import com.envelope.back.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*")
public class AuthLoginController {

    @Autowired
    private UserDetailsServiceImpl loginService;


    /**
     * 登录
     * TODO 需要修改！逻辑应该为，前端有token的时候
     */
    @PostMapping(value = "/login")
    public R login(AuthLoginForm authLoginForm) {

        String token = loginService.loadUserByUsername(authLoginForm.getUsername(), authLoginForm.getPassword());

        if ("".equals(token))
            return R.error().data("token设置错误", "");


        return R.ok().data("token", token);
    }

    @PostMapping(value = "/anotherInterface")
    @PreAuthorize("hasAuthority('system')")
    public R anotherInterface(){

        return R.ok().data("二级权限", "通过！！！");
    }

}
