package com.lida.mongo.sensor.service;

import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
public interface UpdateData {

   void updateDataByCloud(Long stationId);


}
