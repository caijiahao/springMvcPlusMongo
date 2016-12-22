package com.lida.mongo.qq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stevenfen on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping("/result")
    public String login_result(String access_token, String openid) {
        return "qq";
    }

    @RequestMapping("/index")
    public String goIndex(){
        return "qq";
    }
}
