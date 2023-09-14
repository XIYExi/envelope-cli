package com.envelope.back.security;

import com.envelope.back.enhance.exception.CaptchaInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginFailure extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        logger.info("登录失败");
        ex.printStackTrace();
        this.saveException(request, ex);

        // System.out.println(ex.getMessage());

        /**
         * 这里通过判断捕获登陆失败捕获异常的值判断向页面发送什么异常信息
         *
         * 登录失败的异常只会有两种：1、验证码错误 2、用户名或者密码错误
         */
        if ("qrcode error".equals(ex.getMessage())) {
            this.getRedirectStrategy().sendRedirect(request, response, "/toLogin?captchaInvalid=true");
        } else {
            this.getRedirectStrategy().sendRedirect(request, response, "/toLogin?error=true");
        }
    }
}