package com.envelope.back.common.handler;

import cn.hutool.core.util.StrUtil;
import com.envelope.back.common.utils.R;
import com.envelope.back.common.utils.RestCode;
import com.envelope.back.enhance.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * 全局控制器异常处理器
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    /**
     * 作用于@Validated @Valid 注解，仅对于表单提交有效，对于以json格式提交将会失效
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public R handleBindException(BindException e) {
        e.printStackTrace();
        String errorMsg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        if (!StrUtil.isBlank(errorMsg)) {
            return R.error()
                    .message("表单提交异常")
                    .code(RestCode.ERROR_BIND_CODE)
                    .data("错误信息", errorMsg);
        }
        return R.error()
                .code(RestCode.ERROR_BIND_CODE)
                .message("表单提交异常")
                .data("错误信息", e.getMessage());
    }

    /**
     * 作用于 @Validated @Valid 注解，前端提交的方式为json格式有效
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        e.printStackTrace();
        String errorMsg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        if (!StrUtil.isBlank(errorMsg)) {
            return R.error()
                    .message("JSON提交异常")
                    .code(RestCode.ERROR_JSON_CODE)
                    .data("错误信息", errorMsg);
        }
        return R.error()
                .code(RestCode.ERROR_JSON_CODE)
                .message("JSON提交异常")
                .data("错误信息", e.getMessage());
    }


    /**
     * @NotBlank @NotNull @NotEmpty 注解，校验单个String、Integer、Collection等参数异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handConstraintViolationException(ConstraintViolationException e) {
        e.printStackTrace();
        ConstraintViolationException err = (ConstraintViolationException) e;
        ConstraintViolation<?> constraintViolation = err.getConstraintViolations().stream().findFirst().get();
        String messageTemplate = constraintViolation.getMessageTemplate();
        if (!StrUtil.isBlank(messageTemplate)) {
            return R.error()
                    .code(RestCode.ERROR_CONSTRAINT_CODE)
                    .message("违反约束异常")
                    .data("错误信息", messageTemplate);
        }
        return R.error()
                .code(RestCode.ERROR_CONSTRAINT_CODE)
                .message("违反约束异常")
                .data("错误信息", e.getMessage());
    }



    /**
     * Validation参数异常
     */
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody
    public R constraintViolationException(ValidationException e) {
        e.printStackTrace();
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException err = (ConstraintViolationException) e;
            ConstraintViolation<?> constraintViolation = err.getConstraintViolations().stream().findFirst().get();
            String messageTemplate = constraintViolation.getMessageTemplate();
            if (!StrUtil.isBlank(messageTemplate)) {
                return R.error().code(RestCode.ERROR_VALIDATE_CODE).message("参数错误").data("错误信息", messageTemplate);
            }
        }
        return R.error().code(RestCode.ERROR_VALIDATE_CODE).message("参数错误").data("错误信息", e.getMessage());
    }


    /**
     * 捕获我们定义的异常
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public R handleUserNotExistsException(GlobalException e) {
        log.error("捕获到自定义异常：{}", e.getMessage());
        return R.error().code(RestCode.ERROR_CUSTOM_CODE).message("自定义特殊异常").data("错误信息", e.getMessage());
    }


   /**
     * 捕获其它异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return  R.error().code(RestCode.ERROR_CODE).message("其余异常").data("错误信息", e.getMessage());
    }


}
