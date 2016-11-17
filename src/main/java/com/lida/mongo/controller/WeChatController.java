package com.lida.mongo.controller;

import com.lida.mongo.service.CoreService;
import com.lida.mongo.uril.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Created by stevenfen on 2016/11/12.
 */
@Controller
@RequestMapping(value = "wechat")
public class WeChatController {
    @Resource
    private CoreService coreService;

    @RequestMapping(method = RequestMethod.GET)
    public void doget(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        } else {
            System.out.println("不是微信服务器发来的请求!");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void dopose(HttpServletRequest request, HttpServletResponse response) throws Exception {
            /* 消息的接收、处理、响应 */
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = coreService.processRequest(request, response);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}
