package com.lida.mongo.qq.controller;

import com.lida.mongo.qq.model.QQUserInfo;
import com.lida.mongo.util.QQUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stevenfen on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/result")
    public String login_result(String access_token, String openid) {
        QQUserInfo user = QQUtils.getUserInfo(access_token, openid);
        log.debug("=========================================="+user+"=============================================");
        return "mogoList";
    }

    @RequestMapping("/index")
    public String goIndex(){
        return "qq";
    }
}
