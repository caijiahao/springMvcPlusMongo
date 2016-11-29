package com.lida.mongo.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * ͨ�ù�����
 */
public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    // ƾ֤��ȡ��GET��
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * ����https����
     *
     * @param requestUrl    �����ַ
     * @param requestMethod ����ʽ��GET��POST��
     * @param outputStr     �ύ������
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
     */
    public static JSONObject httpsRequest(String requestUrl,
                                          String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // ������SSLContext�����еõ�SSLSocketFactory����
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // ��������ʽ��GET/POST��
            conn.setRequestMethod(requestMethod);

            // ��outputStr��Ϊnullʱ�������д����
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // ע������ʽ
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // ����������ȡ��������
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // �ͷ���Դ
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("���ӳ�ʱ��{}", ce);
        } catch (Exception e) {
            log.error("https�����쳣��{}", e);
        }
        return jsonObject;
    }

    /**
     * URL���루utf-8��
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    // String oauthUrl="http://yw.ysh365.com/coreServlet";

    /**
     * �������������ж��ļ���չ��
     *
     * @param contentType ��������
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }

    public static void main(String[] args) {
        String oauthUrl = "http://yw.ysh365.com/oauthServlet";
        System.out.println(urlEncodeUTF8(oauthUrl));
    }
}