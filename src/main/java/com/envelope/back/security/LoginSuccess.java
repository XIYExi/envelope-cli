package com.envelope.back.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录认证成功");
        // 这里写你登录成功后的逻辑，可以验证其他信息。
        // 重定向 这里可以重定向到前后端分离的前端去，携带验证成功的信息即可
        // 再前端判断token过期时间，再主动跳转到登录页面
        this.getRedirectStrategy().sendRedirect(request, response, "/toIndex");
    }
}
