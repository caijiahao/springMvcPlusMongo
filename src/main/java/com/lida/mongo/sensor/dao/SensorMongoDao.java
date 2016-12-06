package com.lida.mongo.sensor.dao;

import com.lida.mongo.sensor.entity.Sensor;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
public interface SensorMongoDao {

    List<Sensor> findAll();

    void insertSensor(Sensor sensor);

    void batchInsertSensor(List<Sensor> sensors);

    List<Sensor> findForRequery(BigInteger serialNum);

}
