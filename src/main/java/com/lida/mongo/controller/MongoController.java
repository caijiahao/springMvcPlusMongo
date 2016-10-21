package com.lida.mongo.controller;

import com.lida.mongo.dao.PersonDao;
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


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String go() {
        return "mogoList";
    }

}
