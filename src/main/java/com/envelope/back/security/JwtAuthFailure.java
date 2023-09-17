package com.envelope.back.security;

import com.alibaba.fastjson.JSONObject;
import com.envelope.back.common.utils.R;
import com.envelope.back.common.utils.RestCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class JwtAuthFailure implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("认证失败，token不存在或已失效");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(R.error().code(RestCode.ERROR_Anonymous).message("认证失败，token不存在或已失效")));
    }
}