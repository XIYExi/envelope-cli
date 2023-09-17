package com.envelope.back.service.impl;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.envelope.back.common.tools.CaptchaTools;
import com.envelope.back.common.utils.RedisUtils;
import com.envelope.back.common.utils.RequestUtils;
import com.envelope.back.common.utils.SecurityUtils;
import com.envelope.back.enhance.exception.CaptchaInvalidException;
import com.envelope.back.mapper.PermissionMapper;
import com.envelope.back.mapper.UserMapper;
import com.envelope.back.pojo.Permission;
import com.envelope.back.pojo.User;
import com.envelope.back.security.AuthUser;
import com.envelope.back.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private JwtProvider jwtProvider;


    private static final String ROLE_USER = "ROLE_USER,use";

    private static final String ROLE_ADMIN = "ROLE_ADMIN,use,back";

    private static final String ROLE_ROOT = "ROLE_ROOT,use,back,system";


    /**
     * 前后端登录使用jwt令牌做无状态
     *
     * 这个地方是用户登录的主要逻辑，不用写在controller层，直接由spring-security的配置项控制跳转！
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public String loadUserByUsername(String username, String password) throws UsernameNotFoundException {

        /*
        // 先判断验证码
        HttpServletRequest request = RequestUtils.getHttpRequest();
        String qrcode = request.getParameter("qrcode");
        System.out.println("qrcode: "+ qrcode);

        if (!CaptchaTools.verify(qrcode))
            throw new CaptchaInvalidException("qrcode error"); // 这里需要和LoginFailure中的异常判断内容保持一致！
        */

        //通过username（nickname）去数据库查数据
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("nickname", username);
        User result = userMapper.selectOne(query); // 得到user数据

        // 因为要做鉴权，所以这里拿到了user数据（如果通过username查到的话）不可以直接判断密码并返回
        // 必须要先去

        // 非空判断
        if (result == null) {
            log.info("登录用户:{}，不存在", username);
            throw new UsernameNotFoundException("用户不存在");
        }

        String userId = result.getId();

        // TODO redis缓存保存逻辑
        // 这里存放的不是User，而是AuthUser，是security封装好的User类，需要存放权限信息和账号密码，至于用户其他信息可以直接通过id进行单表查询
        AuthUser redisAuthUser = RedisUtils.get(userId, AuthUser.class);
        if (redisAuthUser != null)
            return "";

        // 代码走到这里说明redis里面没有用户数据！
        // 下述代码运行会比较慢，能走redis就走redis！

        // 判断密码是否正确
        if (!SecurityUtils.passwordMatches(password, result.getPassword())){
            log.info("登录用户:{}，密码错误", username);
            throw new UsernameNotFoundException("用户密码错误");
        }


        // 这里说明用户密码正确，此时是查询用户权限然后保存一份到redis缓存，并更新jwt令牌
        Permission permission = permissionMapper.selectPermissionByUserId(userId);
        // permissionCode为角色的权限等级，分为0，1，2
        // 0为普通用户只可以使用服务
        // 1为普通管理员，可以进行数据操作，进行流量监控、推送、crud等操作
        // 2为系统级管理员，可以进入服务器级别的页面，查看项目服务的代码，进行日志查看，监控等，一般为技术性的运维人员
        Integer permissionCode = permission.getPermissionCode();

        log.info("登录者账号为：{}, 登录者权限为：{}", username, permissionCode);

        // 服务一共分为三种，use，back，system
        // use 为基础服务，只可以访问和使用基础服务
        // back 数据管理员，可以进入后台页面，并进行操作，为一级权限
        // system 系统级别运维人员，在back权限基础上再加上可以访问和操作系统级别的操作，为二级权限

        // 0级别， ROLE_USER use
        // 1级别， ROLE_ADMIN use,back
        // 2级别， ROLE_ROOT use,back,system

        // 角色和权限都在这里添加，角色以ROLE_前缀，不是ROLE_前缀的视为权限,这里添加了ROLE_ROOT角色和back、system权限

        // 这里根据permission的权限code去取值
        String authorityList;
        switch (permissionCode){
            case 1:
                authorityList = ROLE_ADMIN;
                break;
            case 2:
                authorityList = ROLE_ROOT;
                break;
            default: // 0 和 异常情况
                authorityList = ROLE_USER;
                break;
        }

        // 下述代码现在不执行，生成的GrantedAuthority列表存放入jwt之后为空列表对象，取出无法使用，等之后判断的时候再生成
        // 代码移动到JwtFilter的解析部分
        // List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityList);
        return jwtProvider.createToken(userId, authorityList);
    }


    /**
     * ssm框架做传统token + redis认证
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先判断验证码
        HttpServletRequest request = RequestUtils.getHttpRequest();
        String qrcode = request.getParameter("qrcode");
        System.out.println("qrcode: "+ qrcode);

        if (!CaptchaTools.verify(qrcode))
            throw new CaptchaInvalidException("qrcode error"); // 这里需要和LoginFailure中的异常判断内容保持一致！


        //通过username（nickname）去数据库查数据
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("nickname", username);
        User result = userMapper.selectOne(query); // 得到user数据

        // 非空判断
        if (result == null) {
            log.info("登录用户:{}，不存在", username);
            throw new UsernameNotFoundException("用户不存在");
        }

        String userId = result.getId();
        // TODO redis缓存保存逻辑
        // 这里存放的不是User，而是AuthUser，是security封装好的User类，需要存放权限信息和账号密码，至于用户其他信息可以直接通过id进行单表查询
        AuthUser redisAuthUser = RedisUtils.get(userId, AuthUser.class);
        if (redisAuthUser != null)
            return redisAuthUser;

        // 代码走到这里说明redis里面没有用户数据！
        // 下述代码运行会比较慢，能走redis就走redis！
        Permission permission = permissionMapper.selectPermissionByUserId(userId);
        // permissionCode为角色的权限等级，分为0，1，2
        // 0为普通用户只可以使用服务
        // 1为普通管理员，可以进行数据操作，进行流量监控、推送、crud等操作
        // 2为系统级管理员，可以进入服务器级别的页面，查看项目服务的代码，进行日志查看，监控等，一般为技术性的运维人员
        Integer permissionCode = permission.getPermissionCode();

        log.info("登录者账号为：{}, 登录者权限为：{}", username, permissionCode);

        // 服务一共分为三种，use，back，system
        // use 为基础服务，只可以访问和使用基础服务
        // back 数据管理员，可以进入后台页面，并进行操作，为一级权限
        // system 系统级别运维人员，在back权限基础上再加上可以访问和操作系统级别的操作，为二级权限

        // 0级别， ROLE_USER use
        // 1级别， ROLE_ADMIN use,back
        // 2级别， ROLE_ROOT use,back,system

        // 角色和权限都在这里添加，角色以ROLE_前缀，不是ROLE_前缀的视为权限,这里添加了ROLE_ROOT角色和back、system权限

        // 这里根据permission的权限code去取值
        String authorityList;
        switch (permissionCode){
            case 1:
                authorityList = ROLE_ADMIN;
                break;
            case 2:
                authorityList = ROLE_ROOT;
                break;
            default: // 0 和 异常情况
                authorityList = ROLE_USER;
                break;
        }

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityList);
        AuthUser authUser = new AuthUser(result.getNickname(), result.getPassword(), authorities);
        // 我们存放我们自定义的信息,如用户id,方便后续获取用户信息
        authUser.setUserId(result.getId());

        // 返回前封装一套数据到redis备份，防止重复查询
        RedisUtils.save(userId, authUser, 6000);

        return authUser;
    }
}