package com.envelope.back.enhance.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaInvalidException extends AuthenticationException {

    public CaptchaInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaInvalidException(String msg) {
        super(msg);
    }
}