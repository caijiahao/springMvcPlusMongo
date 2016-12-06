package com.lida.mongo.sensor.dao.impl;

import com.lida.mongo.sensor.dao.SensorMongoDao;
import com.lida.mongo.sensor.entity.Sensor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lenovo on 2016/12/6.
 */
@Repository("SensorMongoImpl")
public class SensorMongoImpl implements SensorMongoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Sensor> findAll() {
        return mongoTemplate.findAll(Sensor.class, "sensor");
    }

    @Override
    public void insertSensor(Sensor sensor) {
        mongoTemplate.insert(sensor,"sensor");
    }

    @Override
    public void batchInsertSensor(List<Sensor> sensors) {
        mongoTemplate.insert(sensors,"sensor");
    }

    @Override
    public List<Sensor> findForRequery(BigInteger serialNum) {
        return mongoTemplate.find(Query.query(Criteria.where("serialNum").is(serialNum)),Sensor.class);
    }
}
