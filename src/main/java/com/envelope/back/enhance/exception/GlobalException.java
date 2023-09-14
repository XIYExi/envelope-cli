package com.envelope.back.enhance.exception;

import com.envelope.back.common.utils.RestCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer code = RestCode.SUCCESS_CODE;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
