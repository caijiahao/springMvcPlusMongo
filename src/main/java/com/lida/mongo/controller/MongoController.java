package com.lida.mongo.controller;

import com.lida.mongo.dao.PersonDao;
import com.lida.mongo.mongoDao.PersonMongoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by DuLida on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/goMongo")
public class MongoController {
    @Resource
    private PersonMongoImpl personMongo;
    private static Logger log = LoggerFactory.getLogger(MongoController.class);
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String go() {

        log.debug(personMongo.findAll()+"=========================================================================");
        return "mogoList";
    }

}
