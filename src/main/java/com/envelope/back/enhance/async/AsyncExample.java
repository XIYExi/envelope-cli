package com.envelope.back.enhance.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncExample {

    @Async("envelopeExecutor")
    public void run(){
        log.info("当前线程名称为：{}", Thread.currentThread().getName());
    }

}

