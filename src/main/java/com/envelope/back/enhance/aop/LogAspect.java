package com.envelope.back.enhance.aop;

import com.alibaba.fastjson.JSON;
import com.envelope.back.pojo.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 日志切面
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 触发条件，监听的是 @Log 注解的位置
     */
    @Pointcut("@annotation(com.envelope.back.enhance.aop.Log)")
    public void pointcut(){}


    /**
     * 环切增强
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        SysLog sysLog = this.getMethodInfo(joinPoint);
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        sysLog.setCreateTime(new Date())
                .setExecutionTime(end - start);
        log.info(JSON.toJSONString(sysLog));
        // System.out.println("触发");
        return proceed;
    }


    /**
     * 获取方法执行信息
     */
    private SysLog getMethodInfo(ProceedingJoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log customLog = method.getAnnotation(Log.class);
        // 注解上的描述
        Optional.ofNullable(customLog).ifPresent(c -> sysLog.setDesc(c.value()));
        try {
            sysLog
                    .setMethod(joinPoint.getSignature().getName())
                    .setPackageName(joinPoint.getTarget().getClass().getName())
                    .setParams(JSON.toJSONString(this.getParameters(joinPoint)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysLog;
    }

    /**
     * 获取请求参数
     */
    private Map<String, Object> getParameters(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        Map<String, Object> paramsMap = new HashMap<>(2);
        for (int i = 0; i < parameterValues.length; i++) {
            try {
                Object s = parameterValues[i];
                paramsMap.put(parameterNames[i], s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paramsMap;
    }
}
