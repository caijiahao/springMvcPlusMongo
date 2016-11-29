package com.lida.mongo.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @璇存槑锛欻TTP璇锋眰
 */
public class HttpClientUtils {

    private static final Logger logger = Logger
            .getLogger(HttpClientUtils.class);

    /**
     * 鎵цHTTP GET璇锋眰锛屽弬鏁板皝瑁呭湪URL
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.getParams().setContentCharset("UTF-8");

        String result = null;
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                result = method.getResponseBodyAsString();
            } else {
                logger.info("Method failed: " + method.getStatusLine());
            }
        } catch (HttpException e) {
            // 鍙戠敓鑷村懡鐨勫紓甯革紝鍙兘鏄崗璁笉瀵规垨鑰呰繑鍥炵殑鍐呭鏈夐棶棰�?
            logger.info("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            // 鍙戠敓缃戠粶寮傚父
            logger.info("鍙戠敓缃戠粶寮傚父");
            e.printStackTrace();
        } finally {
            // 閲婃斁杩炴帴
            if (method != null)
                method.releaseConnection();
            method = null;
            client = null;
        }
        return result;
    }

    /**
     * 鎵цpost璇锋眰 鍙傛暟JSON鏍煎紡
     */
    public static String postJSON(String strUrl, String param) {
//			String params=JSONObject.fromObject(param).toString();
        try {
            URL url = new URL(strUrl);// 鍒涘缓杩炴帴
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 璁剧疆璇锋眰鏂瑰紡
            connection.setRequestProperty("Accept", "application/json"); // 璁剧疆鎺ユ敹鏁版嵁鐨勬牸寮�?
            connection.setRequestProperty("Content-Type", "application/json"); // 璁剧疆鍙戦�佹暟鎹殑鏍煎紡?
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8缂栫爜
            out.append(param);
            out.flush();
            out.close();
            // 璇诲彇鍝嶅簲
            int length = (int) connection.getContentLength();// 鑾峰彇闀垮害
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8缂栫爜
                return result;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 鎵цHTTP POST璇锋眰锛屽弬鏁扮敤map瀵硅薄?
     *
     * @param url
     * @param paramMap
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected static String httpPost(String url, Map<String, Object> paramMap) {
        String content = null;
        if (url == null || url.trim().length() == 0)
            return null;
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("UTF-8");
        // 璁剧疆header
        httpClient
                .getParams()
                .setParameter(
                        HttpMethodParams.USER_AGENT,
                        "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");

        // 浠ｇ悊璁剧疆
        // httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);

        PostMethod method = new PostMethod(url);
        if (paramMap != null) {
            Iterator it = paramMap.keySet().iterator();

            while (it.hasNext()) {
                String key = it.next() + "";
                Object o = paramMap.get(key);
                if (o != null && o instanceof String) {
                    method.addParameter(new NameValuePair(key, o.toString()));
                }
                if (o != null && o instanceof String[]) {
                    String[] s = (String[]) o;
                    if (s != null)
                        for (int i = 0; i < s.length; i++) {
                            method.addParameter(new NameValuePair(key, s[i]));
                        }
                }
            }
        }

        try {
            logger.info("method.getPath=" + method.getQueryString());
            logger.info("getURI=" + method.getURI());
            httpClient.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                content = method.getResponseBodyAsString();
            } else {
                logger.info("Method failed: " + method.getStatusLine());
            }
        } catch (HttpException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (method != null)
                method.releaseConnection();
            method = null;
            httpClient = null;
        }
        return content;
    }

    /**
     * HTTP GET璇锋眰鍙傛暟灏佽,灏嗗弬鏁版坊鍔犲埌URL
     *
     * @param url
     * @param paramMap
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String urlParamPac(String url, Map<String, Object> paramMap) {
        StringBuilder stringBuilder = new StringBuilder(url + "?");
        Iterator it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next() + "";
            Object value = paramMap.get(key);
            stringBuilder.append(key + "=" + value + "&");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    @SuppressWarnings("rawtypes")
    protected static String urlParamSet(String url, Map<String, Object> paramMap) {
        StringBuilder stringBuilder = new StringBuilder(url);
        Iterator it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next() + "";
            Object value = paramMap.get(key);
            stringBuilder.append(key + "=" + value + "&");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public static void main(String[] args) {
/*//		String URL = "http://116.204.64.165:7880/IWMS/PayNotify/ccbpayCallBack";
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("success", true);
//		map.put("code", "123");
//		System.out.println(HttpClientUtils.httpPost(URL, map));
		
		String Url="http://119.79.224.116:20014/JLESServer/POST_SCM_DJ?Type=Post_EsOrder&USERNAME=JLSOFT&PASSWORD=88888&SIGNXML={\"Parms\":\"JSONXML\",\"Sign\":\"4d4d7828d59fd5f797ea7bccae99c9ec\"}&JSONXML={\"OrderId\":\"201312050001\",\"created\":\"2013-11-11\",\"modified\":\"2013-11-11\",\"Pay_time\":\"2013-11-11\",\"end_time\":\"2013-11-11\",\"Status\":\"绛夊緟鍗栧鍙戣揣\",\"shipping_type\":\"express\",\"title\":\"骞跨櫨瀹樻柟鍟嗗煄\",\"seller_nick\":\"骞跨櫨瀹樻柟鍟嗗煄\",\"buyer_nick\":\"lizhiqiang\",\"total_fee\":\"2000\",\"payment\":\"1800\",\"adjust_fee\":\"0\",\"discount_fee\":\"200\",\"point_fee\":\"0\",\"buyer_obtain_point_fee\":\"0\",\"post_fee\":\"100\",\"seller_memo	\":\"宸插\",\"buyer_message\":\"璇峰敖蹇畨鎺掔墿娴佸彂璐э紝璋㈣阿\",\"receiver_name\":\"鏉庡織寮篭",\"receiver_state\":\"婀栧寳鐪乗",\"receiver_city\":\"姝︽眽甯俓",\"receiver_district\":\"纭氬彛鍖篭",\"receiver_address\":\"姹夋锟�?90鍙穃",\"receiver_zip\":\"430033\",\"receiver_mobile\":\"13419644646\",\"receiver_phone\":\" \",\"invoice\":\"1\",\"invoice_name\":\"姝︽眽閲戝姏杞欢鏈夐檺鍏徃\",\"SysTemCon\":\"1\",\"SPLIST\":[{\"OrderItemId\":\"20131205000101\",\"sku_id\":\"123456789\",\"ProductID\":\"000001\",\" sp_title \":\"鏍煎姏2鍖圭┖璋僜",\"Price \":\"2000\",\"num\":\"1\",\"total_fee\":\"0\",\" adjust_fee_ITEM\":\"2000\",\"discount_fee_item\":\"200\",\"payment_item\":\"1800,\"WhNo \":\"01019901\" }]}";
		System.out.println(HttpClientUtils.httpGet(Url));*/
    }
}
