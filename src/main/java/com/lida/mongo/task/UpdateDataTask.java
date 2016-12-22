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
    @Scheduled(cron = "*/5 * * * * ?")
    public void updataStaionNine(){
        //updateData.updateDataByCloud(9L);
    }

    /**
     * 每隔12小时更新一下站点3,5,7的数据
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void updatStationSeven(){
        //updateData.updateDataByCloud(7L);
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void updatStationFive(){
        //updateData.updateDataByCloud(5L);
    }
    @Scheduled(cron = "*/5 * * * * ?")
    public void updatStationThree(){
        //updateData.updateDataByCloud(3L);
    }


}
