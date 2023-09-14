package com.envelope.back.common.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = EmailConfig.PRE) // 脱裤子放了个屁，直接写prefix="email"也是可以的
public class EmailConfig {

    public final static String PRE = "email";

    private String host;
    private Integer port;
    private String from ;
    private String pass;

    public MailAccount getAccount() {
        MailAccount account = new MailAccount();
        account.setAuth(true);
        account.setHost(host);
        account.setPort(port);
        account.setFrom(from);
        account.setUser(from);
        account.setPass(pass);
        return account;
    }
}
