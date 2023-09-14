package com.envelope.back.service.impl;


import cn.hutool.extra.mail.MailUtil;
import com.envelope.back.common.config.EmailConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl {

    private final EmailConfig eMailConfig;

    private final TemplateEngine templateEngine; // 注意：这个是thymeleaf的TemplateEngine不是hutool的！


    public void send(String to, String subject, String content) {
        MailUtil.send(eMailConfig.getAccount(), to, subject, content, false);
    }

    public void send(String to, String subject, String content, File...files){
        MailUtil.send(eMailConfig.getAccount(), to, subject, content, false, files);
    }

    public void send(String to, String subject, Context ctx){
        String template = templateEngine.process("emailTemplate", ctx);
        MailUtil.send(eMailConfig.getAccount(), to, subject, template, true);
    }

    public void send(String to, String subject, Context ctx, File... files) {
        String template = templateEngine.process("emailTemplate", ctx);
        MailUtil.send(eMailConfig.getAccount(), to, subject, template, true, files);
    }

}
