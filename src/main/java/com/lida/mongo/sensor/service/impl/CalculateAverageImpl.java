package com.lida.mongo.sensor.service.impl;

import com.lida.mongo.sensor.Enum.SerialNum;
import com.lida.mongo.sensor.dao.impl.SensorMongoImpl;
import com.lida.mongo.sensor.entity.Sensor;
import com.lida.mongo.sensor.service.calculateAverage;
import com.lida.mongo.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stevenfen on 2016/12/17.
 */
@Repository("CalculateAverageImpl")
public class CalculateAverageImpl implements calculateAverage {
    @Resource
    private SensorMongoImpl sensorMongo;

    @Override
    public Double calculateAverageOfCo2(String dateStr, BigInteger serialNum) throws ParseException {

        Date startDate = StringUtil.string2Date(dateStr);
        Date endDate = StringUtil.plusOneHour(startDate);

        List<Sensor> sensors = sensorMongo.findByCreateDate(startDate,endDate,serialNum);
        Double sumOfCo2 = 0.0;

        if(serialNum.equals(SerialNum.stationNine.getValue())){
            for(Sensor sensor : sensors){
                sumOfCo2+=sensor.getSensor6();
            }
            return sumOfCo2<=0?0:sumOfCo2/sensors.size();
        }
        else if(serialNum.equals(SerialNum.stationSeven.getValue())){
            for(Sensor sensor : sensors){
                sumOfCo2+=sensor.getSensor13();
            }
            return sumOfCo2<=0?0:sumOfCo2/sensors.size();
        }
        else if(serialNum.equals(SerialNum.stationThree.getValue())){
            for(Sensor sensor : sensors){
                sumOfCo2+=sensor.getSensor24();
            }
            return sumOfCo2<=0?0:sumOfCo2/sensors.size();
        }
        return 0.0;
    }
}
