package com.lida.mongo.weixin.service;

import com.lida.mongo.weixin.message.resp.TextMessage;
import com.lida.mongo.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


/**
 * 核心服务类
 */

@Repository("CoreService")
public class CoreService {
    private static Logger log = LoggerFactory.getLogger(CoreService.class);

    private static String emoji(int codePoint) {
        return String.valueOf(Character.toChars(codePoint));
    }

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */

    public String processRequest(HttpServletRequest request, HttpServletResponse response) {

        // 默认返回的文本消息内容
        String respMessage = null;
        try {
            String respContent = null;
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            String openid = requestMap.get("FromUserName");
            String Content = requestMap.get("Content");
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                if ("1".equals(Content)) {
                    respContent = "今天没啥事,看看别的吧";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
//            		// 创建图文消息
//					NewsMessage newsMessage = new NewsMessage();
//					newsMessage.setToUserName(fromUserName);
//					newsMessage.setFromUserName(toUserName);
//					newsMessage.setCreateTime(new Date().getTime());
//					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//					newsMessage.setFuncFlag(0);
//					
//					//1
//					List<Article> articleList = new ArrayList<Article>();
//					Article article1 = new Article();
//					article1.setTitle("小鸟");
//					article1.setDescription("经典小游戏");
//					article1.setPicUrl("http://resource.duopao.com/sg/image/20140221170627.jpg");
//					article1.setUrl("http://www.duopao.com/games/info?game_code=g20140212153040377809");
//					//2
//					Article article2 = new Article();
//					article2.setTitle("雷霆");
//					article2.setDescription("经典小游戏");
//					article2.setPicUrl("http://resource.duopao.com/sg/image/20140120233041.jpg");
//					article2.setUrl("http://www.duopao.com/games/info?game_code=g20140120233048400063");
//
//					articleList.add(article1);
//					articleList.add(article2);
//					newsMessage.setArticleCount(articleList.size());
//					newsMessage.setArticles(articleList);
//					respMessage = MessageUtil.newsMessageToXml(newsMessage);

                } else if ("2".equals(Content)) {
                    respContent = MessageUtil.youxi();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else if ("？".equals(Content) || "?".equals(Content)) {
                    respContent = MessageUtil.getMainMenu();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else {
                    respContent = "请输入数字";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                String MediaId = requestMap.get("MediaId");
                respContent = "图片地址是：" + MediaId;
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {

            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "请点击菜单进行操作";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 事件推送         
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = emoji(0x1F334) + "谢谢关注小小臧" + MessageUtil.getMainMenu();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("11")) {
                        respContent = emoji(0x274C) + "您当前还没有绑定体验店账户，请直接回复您的云生活企业用户名进行绑定。";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);

                    } else if (eventKey.equals("12")) {
                    } else if (eventKey.equals("13")) {
                    } else if (eventKey.equals("14")) {
                    } else if (eventKey.equals("21")) {
                    } else if (eventKey.equals("22")) {
                    } else if (eventKey.equals("23")) {
                    } else if (eventKey.equals("24")) {
                    } else if (eventKey.equals("31")) {
                    } else if (eventKey.equals("32")) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

}