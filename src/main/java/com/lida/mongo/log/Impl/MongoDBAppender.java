package com.lida.mongo.log.Impl;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.lida.mongo.log.MongoDBAppenderBase;
import com.mongodb.BasicDBObject;
import jodd.datetime.JDateTime;

/**
 * Created by lenovo on 2016/12/7.
 */
public class MongoDBAppender extends MongoDBAppenderBase<ILoggingEvent> {
    public MongoDBAppender() {
        super("loggingEvents");
    }

    @Override
    protected BasicDBObject toMongoDocument(ILoggingEvent eventObject) {
        final BasicDBObject doc = new BasicDBObject();
        doc.append("date", new JDateTime(eventObject.getTimeStamp()).toString("YYYY-MM-DD hh:mm:ss"));
        doc.append("source", source);
        doc.append("level", eventObject.getLevel().toString());
        doc.append("logger", eventObject.getLoggerName());
        doc.append("thread", eventObject.getThreadName());
        doc.append("message", eventObject.getFormattedMessage());
        if (eventObject.getMdc() != null && !eventObject.getMdc().isEmpty())
            doc.append("mdc", eventObject.getMdc());
        //...
        return doc;
    }
}
