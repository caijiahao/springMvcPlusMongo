package com.lida.mongo.util;

import com.lida.mongo.person.entity.Person;
import com.lida.mongo.sensor.entity.Sensor;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/12/7.
 */
public class MongodbUtil {
    public static void main(String[] args) throws UnknownHostException {
        Mongo  mongo = new Mongo(new ServerAddress("172.16.31.206", 27017));
        //MongoClientURI mongoURI = new MongoClientURI("mongodb://172.16.31.206:27017/mongoLida");
        List<String> lists =  mongo.getDatabaseNames();
        DB db = mongo.getDB("mongoLida");
        DBCollection collection = db.getCollection("sensor");
        System.out.println(collection.count());
    }
    private static MongoClientOptions getConfOptions() {
        return new MongoClientOptions.Builder().socketKeepAlive(true) // 是否保持长链接
                .connectTimeout(5000) // 链接超时时间
                .socketTimeout(5000) // read数据超时时间
                .readPreference(ReadPreference.primary()) // 最近优先策略
                .autoConnectRetry(false) // 是否重试机制
                .connectionsPerHost(30) // 每个地址最大请求数
                .maxWaitTime(1000 * 60 * 2) // 长链接的最大等待时间
                .threadsAllowedToBlockForConnectionMultiplier(50) // 一个socket最大的等待请求数
                .writeConcern(WriteConcern.NORMAL).build();
    }
}
