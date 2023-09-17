package com.envelope.back.common.tools;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.envelope.back.common.utils.RequestUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class CaptchaTools {

    private static final String CAPTCHA = "captcha";

    /**
     * CircleCaptcha 圆圈干扰验证码
     * 定义图形验证码的长、宽、验证码字符数、干扰元素个数
     */
    public static void getCircleCaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 5, 20);
        System.err.println("qrcode print: " + lineCaptcha.getCode());
        session.setAttribute(CAPTCHA, lineCaptcha.getCode());
        writeResp(lineCaptcha, response);
    }

    /**
     * 验证码校验
     *
     * @param code 验证码
     */
    public static boolean verify(String code) {
        HttpSession session = RequestUtils.getHttpSession();

        System.err.println("session: " + session);

        String captcha = (String) session.getAttribute(CAPTCHA);



        return code.equals(captcha);
    }

    /**
     * http图片响应
     */
    private static void writeResp(AbstractCaptcha abstractCaptcha, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            abstractCaptcha.write(out);
        } finally {
            if (Objects.nonNull(out)) {
                out.close();
            }
        }
    }



}
