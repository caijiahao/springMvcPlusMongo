package com.lida.mongo.sensor.service.impl;

import light.mvc.pageModel.sensor.Station;
import light.mvc.pageModel.sensor.monitoringNode;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
public interface SensorService {
    void insertApi(JSONArray dataJson, Station station, List<monitoringNode> nodeList) throws ParseException;
}
