package com.lida.mongo.task;

import com.lida.mongo.sensor.service.impl.UpdateDataImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by stevenfen on 2016/12/7.
 */
@Component("updateDataTask")
public class UpdateDataTask {
    @Resource
    private UpdateDataImpl updateData;

    /**
     * 每个9个小时更新一下站点9的数据
     */
    @Scheduled(cron = "* * */9 * * ?")
    public void updataStaionNine(){
        updateData.updateDataByCloud(9L);
    }
}
