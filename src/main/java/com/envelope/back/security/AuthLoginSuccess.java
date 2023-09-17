package com.envelope.back.security;

import cn.hutool.json.JSONUtil;
import com.envelope.back.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthLoginSuccess implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO 登录成功 记录日志
        // ServletUtils.render(request, response, RestResponse.success(authentication));
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");

        response.getWriter().print(JSONUtil.toJsonStr(R.ok())); // 给前端返回成功码2000
    }
}
