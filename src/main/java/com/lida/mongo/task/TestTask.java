package com.lida.mongo.task;

import com.lida.mongo.sensor.service.impl.UpdateDataImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by stevenfen on 2016/11/14.
 */
@Component("annotationTask")
public class TestTask {
    @Resource
    private UpdateDataImpl updateData;
    @Scheduled(cron = "*/30 * * * * ?")
    public void print() {
        updateData.updateDataByCloud(9L);
    }
}
