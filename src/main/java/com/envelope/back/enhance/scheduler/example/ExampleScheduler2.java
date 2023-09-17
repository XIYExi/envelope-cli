package com.envelope.back.enhance.scheduler.example;


import com.envelope.back.enhance.scheduler.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleScheduler2 implements ScheduleService {

    @Override
    public void everyMinute() {
        log.info("（每分钟）定时任务执行了");
    }

}