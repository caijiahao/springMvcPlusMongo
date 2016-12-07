package com.lida.mongo.log;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.mongodb.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.net.UnknownHostException;

/**
 * Created by lenovo on 2016/12/7.
 */
public abstract class MongoDBAppenderBase<E>  extends UnsynchronizedAppenderBase<E>{
    private Mongo mongo;
    private DBCollection eventsCollection;

    @Resource
    private MongoTemplate mongoTemplate;

    private String host = "172.16.31.206";
    private int port = 27017;
    private String dbName = "db";
    private String collectionName;
    private String username;
    private String password;
    protected String source;

    private int connectionsPerHost = 10;
    private int threadsAllowedToBlockForConnectionMultiplier = 5;
    private int maxWaitTime = 1000 * 60 * 2;
    private int connectTimeout;
    private int socketTimeout;
    private boolean autoConnectRetry;
    private boolean slaveOk;
    private boolean safe;
    private int w;
    private int wtimeout;
    private boolean fsync;

    protected MongoDBAppenderBase(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public void start() {
        try {
            connectToMongoDB();
            super.start();
        } catch (UnknownHostException e) {
            addError(
                    "Error connecting to MongoDB server: " + host + ":" + port,
                    e);
        }
    }

    private void connectToMongoDB() throws UnknownHostException {
        mongo = new Mongo(new ServerAddress("172.16.31.206", 27017), buildOptions());
        DB db = mongo.getDB(dbName);
        if (username != null && password != null)
            db.authenticate(username, password.toCharArray());
        eventsCollection = db.getCollection(collectionName);
    }

    private MongoOptions buildOptions() {
        final MongoOptions options = new MongoOptions();
        options.connectionsPerHost = connectionsPerHost;
        options.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
        options.maxWaitTime = maxWaitTime;
        options.connectTimeout = connectTimeout;
        options.socketTimeout = socketTimeout;
        options.autoConnectRetry = autoConnectRetry;
        options.slaveOk = slaveOk;
        options.safe = safe;
        options.w = w;
        options.wtimeout = wtimeout;
        options.fsync = fsync;
        return options;
    }

    protected abstract BasicDBObject toMongoDocument(E event);

    @Override
    protected void append(E eventObject) {
        BasicDBObject basicDBObject = toMongoDocument(eventObject);
        if(basicDBObject.get("message").toString().indexOf("=")!=-1&&basicDBObject.get("level").equals("INFO")) {
            eventsCollection.insert(toMongoDocument(eventObject));
        }
    }

    @Override
    public void stop() {
        if (mongo != null)
            mongo.close();
        super.stop();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
    }

    public void setSlaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setWtimeout(int wtimeout) {
        this.wtimeout = wtimeout;
    }

    public void setFsync(boolean fsync) {
        this.fsync = fsync;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
