package com.envelope.back;

import com.envelope.back.security.AuthUser;
import com.envelope.back.security.th.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@ComponentScan(
        basePackages = {"com.envelope"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = {AuthUser.class, LoginFailure.class, LoginSuccess.class, LogoutSuccess.class, WebSecurityConfig.class})}
)
@Slf4j
@EnableScheduling
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

    /**
     * 这一段动不得，删了就没法获得 HttpServletRequest
     */
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
