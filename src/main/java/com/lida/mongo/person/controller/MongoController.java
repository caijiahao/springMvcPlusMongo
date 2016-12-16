package com.lida.mongo.person.controller;


import com.lida.mongo.log.Impl.MongoDBAppender;
import com.lida.mongo.person.mongoDao.PersonMongoImpl;
import com.lida.mongo.sensor.dao.impl.SensorMongoImpl;
import com.lida.mongo.sensor.service.impl.CalculateAverageImpl;
import com.lida.mongo.sensor.service.impl.UpdateDataImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by DuLida on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/goMongo")
public class MongoController {
    @Resource
    private PersonMongoImpl personMongo;
    @Resource
    private SensorMongoImpl sensorMongo;
    @Resource
    private UpdateDataImpl updateData;
    @Resource
    private CalculateAverageImpl calculateAverage;

    private static Logger log = LoggerFactory.getLogger(MongoController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String go() throws ParseException {
        String dateStr = "2016-11-19 19:10:00";
        log.info("==========================================="+sensorMongo.findForRequery(BigInteger.valueOf(318861587)).size());
        log.info("==========================================="+calculateAverage.calculateAverageOfCo2(dateStr,BigInteger.valueOf(318861587)));
        return "mogoList";
    }

}
