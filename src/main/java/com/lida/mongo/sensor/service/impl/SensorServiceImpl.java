package com.lida.mongo.sensor.service.impl;

import com.lida.mongo.sensor.dao.impl.SensorMongoImpl;
import com.lida.mongo.sensor.entity.Sensor;
import com.lida.mongo.util.StringUtil;
import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
@Repository("SensorServiceImpl")
public class SensorServiceImpl implements SensorService{
    private static Logger log = LoggerFactory.getLogger(SensorServiceImpl.class);

    @Resource
    private SensorMongoImpl sensorMongo;


    /**
     *
     * @param dataJson
     * @param station
     * @param nodeList
     * 批量插入云数据
     */
    @Override
    public void insertApi(JSONArray dataJson, Station station, List<monitoringNode> nodeList) throws ParseException {
        List<Sensor> sensors = new ArrayList<Sensor>();
        for(int i=0;i<dataJson.length();i++){
            Sensor sensor = new Sensor();

            JSONObject jo = (JSONObject)dataJson.get(i);
            String dateStr=jo.getString("date");
            dateStr= dateStr.replace("T", " ");
            sensor.setCreateDate(StringUtil.string2Date(dateStr));
            sensor.setCreateBy(station.getLoginName());
            sensor.setUpdateDate(StringUtil.string2Date(dateStr));
            sensor.setUpdateBy(station.getLoginName());
            sensor.setTimeLine(StringUtil.string2Date(dateStr).getTime());

            //初始化为未删除
            sensor.setDeleted(0);
            sensor.setActive(1);

            for (int j=0;j<nodeList.size();j++){
                monitoringNode node = nodeList.get(j);
                Double value = Double.MIN_VALUE;
                log.info(jo.get(node.getDataKey())+"===========================================");
                if(!jo.get(node.getDataKey()).toString().equals("null")) {value = Double.parseDouble(jo.get(node.getDataKey()) + "");
                }
                StringUtil.judgeField(node.getMap(),value,sensor);
            }
            sensor.setSerialNum(BigInteger.valueOf(station.getSerialNum()));
            sensors.add(sensor);
        }
        log.info("new data:"+sensors.toString()+"=====================================================");
        sensorMongo.batchInsertSensor(sensors);
    }
}
