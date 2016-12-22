package com.lida.mongo.util;


import com.lida.mongo.weixin.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * �˵���������
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // �������û�Ψһƾ֤
        String appId = "wx796e5452641c1b3e";
        // �������û�Ψһƾ֤��Կ
        String appSecret = "af9ebd266419f224d51e95bde72d727d";

        // ���ýӿڻ�ȡaccess_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        System.out.println("token:" + at.getToken());


        if (null != at) {
            // ���ýӿڴ����˵�
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // �жϲ˵��������
            if (0 == result)
                log.info("�˵������ɹ���");
            else
                log.info("�˵�����ʧ�ܣ������룺" + result);
        }
    }

    /**
     * ��װ�˵�����
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
//		btn14.setName("ɨһɨ");
//		btn14.setType("scancode_push");
//		btn14.setKey("14");
//		btn14.setSub_button("scancode_push");
//		
//		pic_sysphotoButton btn15 = new pic_sysphotoButton();
//		btn15.setName("���ջ�����ᷢͼ");
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
        btn33.setName("С��");
        btn33.setType("view");
        btn33.setUrl("http://www.duopao.com/games/info?game_code=g20140212153040377809");

        ViewButton btn34 = new ViewButton();
        btn34.setName("����");
        btn34.setType("view");
        btn34.setUrl("http://www.duopao.com/games/info?game_code=g20140120233048400063");


        ViewButton btn35 = new ViewButton();
        btn35.setName("��Ȩ");
        btn35.setType("view");
        btn35.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx55cbb77820db8240&redirect_uri=http%3A%2F%2Fyw.ysh365.com%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("����");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("ɨһɨ");
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("����");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34, btn35});

        /**
         * ÿ��һ���˵����ж����˵���<br>
         *
         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
