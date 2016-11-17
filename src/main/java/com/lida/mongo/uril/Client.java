package com.lida.mongo.uril;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    /*
     * webservice����������
     */
    // �������е�Demo��������GBK�����²��Եġ�
    //�������ϵͳ��utf-8,����ע�᷽�����ܲ��ɹ���
    //java.io.IOException: Server returned HTTP response code: 400 for URL: http://sdk2.zucp.net:8060/webservice.asmx��
    //�����������400������ο���105�С�
    //�������ϵͳ��utf-8���յ��Ķ��ſ��������룬��ο���298��
    //���Ը���������Ҫ���н�������ĵ�ַ
    //http://sdk2.zucp.net:8060/webservice.asmx?wsdl
    private String serviceURL = "http://sdk.entinfo.cn:8060/webservice.asmx";
    private String sn = "";// ���к�
    private String password = "";// ����
    private String pwd = "";

    /*
     * ���캯��
     */
    public Client(String sn, String password)
            throws UnsupportedEncodingException {
        this.sn = sn;
        this.password = password;
        this.pwd = this.getMD5(sn + password);
    }

    /*
     * �������ƣ�getMD5
     * ��    �ܣ��ַ���MD5����
     * ��    ������ת���ַ���
     * �� �� ֵ������֮���ַ���
     */
    public String getMD5(String sourceStr) throws UnsupportedEncodingException {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            // resultStr = new String(md5.digest());
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * �������ƣ�register
     * ��    �ܣ�ע��
     * ��    ������Ӧ���� ʡ�ݣ����У���ҵ����ҵ���ƣ���ϵ�ˣ��绰���ֻ����������䣬���棬��ַ���ʱ�
     * �� �� ֵ��ע������String��
     */
    public String register(String province, String city, String trade,
                           String entname, String linkman, String phone, String mobile,
                           String email, String fax, String address, String postcode, String sign) {
        String result = "";
        String soapAction = "http://tempuri.org/Register";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<Register xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<province>" + province + "</province>";
        xml += "<city>" + city + "</city>";
        xml += "<trade>" + trade + "</trade>";
        xml += "<entname>" + entname + "</entname>";
        xml += "<linkman>" + linkman + "</linkman>";
        xml += "<phone>" + phone + "</phone>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<email>" + email + "</email>";
        xml += "<fax>" + fax + "</fax>";
        xml += "<address>" + address + "</address>";
        xml += "<postcode>" + postcode + "</postcode>";
        xml += "<sign>" + sign + "</sign>";
        xml += "</Register>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//			bout.write(xml.getBytes());
            bout.write(xml.getBytes("GBK"));
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<RegisterResult>(.*)</RegisterResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            return new String(result.getBytes(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * �������ƣ�chargeFee
     * ��    �ܣ���ֵ
     * ��    ������ֵ���ţ���ֵ����
     * �� �� ֵ�����������String��
     */
    public String chargeFee(String cardno, String cardpwd) {
        String result = "";
        String soapAction = "http://tempuri.org/ChargUp";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<ChargUp xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<cardno>" + cardno + "</cardno>";
        xml += "<cardpwd>" + cardpwd + "</cardpwd>";
        xml += "</ChargUp>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";
        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<ChargUpResult>(.*)</ChargUpResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            // return result;
            return new String(result.getBytes(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * �������ƣ�getBalance
     * ��    �ܣ���ȡ���
     * ��    ������
     * �� �� ֵ����String��
     */
    public String getBalance() {
        String result = "";
        String soapAction = "http://tempuri.org/balance";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<balance xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "</balance>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<balanceResult>(.*)</balanceResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            return new String(result.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * �������ƣ�mt
     * ��    �ܣ����Ͷ��� ,������ֻ��ž���Ⱥ����һ���ֻ��ž��ǵ����ύ
     * ��    ����mobile,content,ext,stime,rrid(�ֻ��ţ����ݣ���չ�룬��ʱʱ�䣬Ψһ��ʶ)
     * �� �� ֵ��Ψһ��ʶ���������дrrid������ϵͳ���ɵ�
     */
    public String mt(String mobile, String content, String ext, String stime,
                     String rrid) {
        String result = "";
        String soapAction = "http://tempuri.org/mt";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mt xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "</mt>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//			bout.write(xml.getBytes());
//			�������ϵͳ��utf-8,������ĳ�
            bout.write(xml.getBytes("GBK"));

            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();
            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mtResult>(.*)</mtResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * �������ƣ�mo
     * ��    �ܣ����ն���
     * ��    ������
     * �� �� ֵ�����յ�����Ϣ
     */
    public String mo() {
        String result = "";
        String soapAction = "http://tempuri.org/mo";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mo xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "</mo>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStream isr = httpconn.getInputStream();
            StringBuffer buff = new StringBuffer();
            byte[] byte_receive = new byte[10240];
            for (int i = 0; (i = isr.read(byte_receive)) != -1; ) {
                buff.append(new String(byte_receive, 0, i));
            }
            isr.close();
            String result_before = buff.toString();
            int start = result_before.indexOf("<moResult>");
            int end = result_before.indexOf("</moResult>");
            result = result_before.substring(start + 10, end);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
 * �������ƣ�gxmt �� �ܣ����͸��Զ��� ��������ͬ���ֻ��ŷ��Ͳ�ͬ�����ݣ��ֻ��ź�������Ӣ�ĵĶ��Ŷ�Ӧ��
     * ������mobile,content,ext,stime,rrid(�ֻ��ţ����ݣ���չ�룬��ʱʱ�䣬Ψһ��ʶ) �� ��
     * ֵ��Ψһ��ʶ���������дrrid������ϵͳ���ɵ�
     */
    public String gxmt(String mobile, String content, String ext, String stime,
                       String rrid) {
        String result = "";
        String soapAction = "http://tempuri.org/gxmt";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<gxmt xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "</gxmt>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<gxmtResult>(.*)</gxmtResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public String UnRegister() {
        String result = "";
        String soapAction = "http://tempuri.org/UnRegister";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<UnRegister xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "</UnRegister>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";
        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<UnRegisterResult>String</UnRegisterResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            return new String(result.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /*
     * �������ƣ�UDPPwd
     * ��    �ܣ��޸�����
     * ��    ����������
     * �� �� ֵ�����������String��
     */
    public String UDPPwd(String newPwd) {
        String result = "";
        String soapAction = "http://tempuri.org/UDPPwd";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<UDPPwd  xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + password + "</pwd>";
        xml += "<newpwd>" + newPwd + "</newpwd>";
        xml += "</UDPPwd>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern
                        .compile("<UDPPwdResult>(.*)</UDPPwdResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            in.close();
            // return result;
            return new String(result.getBytes(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}