package com.envelope.back.controller.th;

import com.envelope.back.common.tools.CaptchaTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class ThSecurityLoginController {

    @GetMapping("/toLogin")
    public String login(){
        return "securityTemplate";
    }

    @GetMapping("/getCode")
    @ResponseBody
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        CaptchaTools.getCircleCaptcha(session, response);
    }


}
