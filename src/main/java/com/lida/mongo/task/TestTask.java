package com.lida.mongo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by stevenfen on 2016/11/14.
 */
@Component("annotationTask")
public class TestTask {
    @Scheduled(cron = "*/5 * * * * ?")
    public void print() {
        String time = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println("定时器触发打印" + time);
    }
}
