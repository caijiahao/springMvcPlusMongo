package com.lida.mongo.util;

import com.lida.mongo.weixin.model.AccessToken;
import com.lida.mongo.weixin.model.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;


/**
 * ????????????????
 */

public class WeixinUtil {
//	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);  

    /**
     * ????https?????????
     *
     * @param requestUrl    ??????
     * @param requestMethod ???????GET??POST??
     * @param outputStr     ????????
     * @return JSONObject(???JSONObject.get(key)???????json??????????)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // ????SSLContext????????????????????¦É??????????  
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // ??????SSLContext?????§Ö??SSLSocketFactory????  
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // ???????????GET/POST??  
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // ??????????????  
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // ?????????????????????  
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // ???????????????????????  
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // ??????  
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e);
        }
        return jsonObject;
    }

    // ???access_token????????GET?? ??200????/??  
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * ???access_token
     *
     * @param appid     ??
     * @param appsecret ???
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // ?????????  
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                System.setProperty("javax.net.debug", "ssl,handshake");
            } catch (JSONException e) {
                accessToken = null;
                // ???token???  
                System.out.println("???token??? errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    // ?????????POST?? ??100????/??
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * ???????
     *
     * @param menu        ??????
     * @param accessToken ??§¹??access_token
     * @return 0???????????????????
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // ???????????url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // ??????????????json?????  
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // ????????????  
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                System.out.println("?????????? errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
