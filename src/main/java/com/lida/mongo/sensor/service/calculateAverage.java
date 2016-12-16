package com.lida.mongo.sensor.service;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * Created by stevenfen on 2016/12/17.
 */
public interface calculateAverage {
    public Double calculateAverageOfCo2(String dateStr, BigInteger serialNum) throws ParseException;
}
