package com.envelope.back.enhance.scheduler.example;

import com.envelope.back.enhance.scheduler.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleScheduler1 implements ScheduleService {
    @Override
    public void everySecond() {
        log.info("（每秒）定时任务执行了");
    }
}
