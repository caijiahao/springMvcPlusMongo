package com.lida.mongo.person.controller;

import com.lida.mongo.person.entity.Address;
import com.lida.mongo.person.entity.Person;
import com.lida.mongo.person.mongoDao.PersonMongoImpl;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String go() {

        personMongo.insertPerson(new Person("weiwei", 24, new Address("南王", "鑫达路", 10)));
        personMongo.removePerson("name3");
        personMongo.updatePerson();
        log.info("========================================================");
        System.out.println(personMongo.findAll());
        System.out.println(personMongo.findForRequery("weiwei"));
        log.debug("=========================================================================");
        return "mogoList";
    }

}
