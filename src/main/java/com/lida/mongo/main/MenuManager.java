package com.lida.mongo.main;


import com.lida.mongo.model.*;
import com.lida.mongo.uril.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 菜单管理器类
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wx796e5452641c1b3e";
        // 第三方用户唯一凭证密钥
        String appSecret = "af9ebd266419f224d51e95bde72d727d";

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        System.out.println("token:" + at.getToken());


        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("11");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("12");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("13");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("14");
        btn14.setType("click");
        btn14.setKey("14");
//		Scancode_waitmsgButton btn14 = new Scancode_waitmsgButton();
//		btn14.setName("扫一扫");
//		btn14.setType("scancode_push");
//		btn14.setKey("14");
//		btn14.setSub_button("scancode_push");
//		
//		pic_sysphotoButton btn15 = new pic_sysphotoButton();
//		btn15.setName("拍照或者相册发图");
//		btn15.setType("pic_photo_or_album");
//		btn15.setKey("15");


        CommonButton btn21 = new CommonButton();
        btn21.setName("21");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("22");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("23");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("24");
        btn24.setType("click");
        btn24.setKey("24");


        CommonButton btn25 = new CommonButton();
        btn25.setName("25");
        btn25.setType("click");
        btn25.setKey("25");

        ViewButton btn31 = new ViewButton();
        btn31.setName("31");
        btn31.setType("view");
        btn31.setUrl("http://160d6436o1.51mypc.cn/mongo/goMongo/list");

        ViewButton btn32 = new ViewButton();
        btn32.setName("32");
        btn32.setType("view");
        btn32.setUrl("http://www.m.ysh365.com/article/33.html");


        ViewButton btn33 = new ViewButton();
        btn33.setName("小鸟");
        btn33.setType("view");
        btn33.setUrl("http://www.duopao.com/games/info?game_code=g20140212153040377809");

        ViewButton btn34 = new ViewButton();
        btn34.setName("雷霆");
        btn34.setType("view");
        btn34.setUrl("http://www.duopao.com/games/info?game_code=g20140120233048400063");


        ViewButton btn35 = new ViewButton();
        btn35.setName("授权");
        btn35.setType("view");
        btn35.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx55cbb77820db8240&redirect_uri=http%3A%2F%2Fyw.ysh365.com%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("读物");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("扫一扫");
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("娱乐");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34, btn35});

        /**
         * 每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
