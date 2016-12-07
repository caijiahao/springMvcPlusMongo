package com.lida.mongo.util;

import com.lida.mongo.sensor.entity.Sensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by lenovo on 2016/12/6.
 */
public class StringUtil {
    private static  final  String format = "yyyy-MM-dd HH:mm:ss";

    public static Date string2Date(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date=sdf.parse(dateStr);
        return date;
    }

    public static void judgeField(String nodeMap, Double value, Sensor sensor){
        if(nodeMap.equals("sensor1")){
            sensor.setSensor1(value);
        }
        else if(nodeMap.equals("sensor2")){
            sensor.setSensor2(value);
        }
        else if(nodeMap.equals("sensor3")){
            sensor.setSensor3(value);
        }
        else if(nodeMap.equals("sensor4")){
            sensor.setSensor4(value);
        }
        else if(nodeMap.equals("sensor5")){
            sensor.setSensor5(value);
        }
        else if(nodeMap.equals("sensor6")){
            sensor.setSensor6(value);
        }
        else if(nodeMap.equals("sensor7")){
            sensor.setSensor7(value);
        }
        else if(nodeMap.equals("sensor8")){
            sensor.setSensor8(value);
        }
        else if(nodeMap.equals("sensor9")){
            sensor.setSensor9(value);
        }
        else if(nodeMap.equals("sensor10")){
            sensor.setSensor10(value);
        }
        else if(nodeMap.equals("sensor11")){
            sensor.setSensor11(value);
        }
        else if(nodeMap.equals("sensor12")){
            sensor.setSensor12(value);
        }
        else if(nodeMap.equals("sensor13")){
            sensor.setSensor13(value);
        }
        else if(nodeMap.equals("sensor14")){
            sensor.setSensor14(value);
        }
        else if(nodeMap.equals("sensor15")){
            sensor.setSensor15(value);
        }
        else if(nodeMap.equals("sensor16")){
            sensor.setSensor16(value);
        }
        else if(nodeMap.equals("sensor17")){
            sensor.setSensor17(value);
        }
        else if(nodeMap.equals("sensor18")){
            sensor.setSensor18(value);
        }
        else if(nodeMap.equals("sensor19")){
            sensor.setSensor19(value);
        }
        else if(nodeMap.equals("sensor20")){
            sensor.setSensor20(value);
        }

    }

}
