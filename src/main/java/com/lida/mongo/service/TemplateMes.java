package com.lida.mongo.service;

import com.lida.mongo.model.AccessToken;
import com.lida.mongo.uril.HttpClientUtils;
import com.lida.mongo.uril.WeixinUtil;

import java.util.Map;


/**
 * 模板消息
 * @author Administrator
 *
 */
public class TemplateMes {
	
	
	String appId = "wx247b80e8e951fe79";
	// 第三方用户唯一凭证密钥
	String appSecret = "4fc4112c0abac60c04997c8ac4d87b92";

	// 调用接口获取access_token
	AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
	

	// 体验店新订单通知
	private static String NEWORDERS = "9x4PdTRa_FehBFX2HCQHMXAGRshlFj5AFg6zUe3MAA4";
	// 供货商订单发货提醒 
	private static String ORDERSSHIPMENTS="9x4PdTRa_FehBFX2HCQHMXAGRshlFj5AFg6zUe3MAA4";
	// 体验店收益提醒 Earnings
	private static String EARNINGS="n5BDnJ787UBXuYdBVhZsGdcf-yv51kyz0roptFdC3S8";
	// 商品缺货提醒 stockout
	private static String GOODSSTOCKOUT="Kfk7pITiatD9GiR84Hi99NU5jAFZ6ttBhI23elmOuIQ";
	
	private static String MESSAGE_TYPE_NEWORDERS = "neworders";
	private static String MESSAGE_TYPE_ORDERSSHIPMENTS = "ordersshipments";
	private static String MESSAGE_TYPE_EARNINGS = "earnings";
	private static String MESSAGE_TYPE_GOODSSTOCKOUT = "goodsstockout";

	public String sendWXMes(String type, Map<String, String> mesinfo)
			throws Exception {
		String jsoninfo = null;
		if (MESSAGE_TYPE_NEWORDERS.equals(type.trim())) {
			// 订单支付成功通知
			jsoninfo = TemplateMes.sendCaptcha(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_ORDERSSHIPMENTS.equals(type.trim())){
			// 订单发货提醒
			jsoninfo = TemplateMes.sendRebate(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_EARNINGS.equals(type.trim())){
			// 供货商收益提醒
			jsoninfo = TemplateMes.sendgoodcode(mesinfo);
			System.out.println(jsoninfo);
		}else if(MESSAGE_TYPE_GOODSSTOCKOUT.equals(type.trim())){
			// 供货商收益提醒
			jsoninfo = TemplateMes.sendgoodsstockout(mesinfo);
			System.out.println(jsoninfo);
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at.getToken();
		System.out.println(at.getToken());
		return HttpClientUtils.postJSON(strUrl, jsoninfo);

	}
	// 新订单通知
	private static String sendCaptcha(Map<String, String> mesinfo) {
		String jsoninfo = "{\"template_id\": \""+NEWORDERS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" }," 
				+ "\"orderno\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("orderno")+"\"}, "
				+ "\"refundno\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundno")+"\"},"
				+ "\"refundproduct\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundproduct")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	// 供货商订单发货提醒
	private static String sendRebate(Map<String,String> mesinfo){
		String jsoninfo = "{\"template_id\": \""+ORDERSSHIPMENTS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"orderno\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("orderno")+"\"}, "
				+ "\"refundno\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundno")+"\"},"
				+ "\"refundproduct\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("refundproduct")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	//收益提醒
	private static String sendgoodcode(Map<String ,String > mesinfo){
		String jsoninfo="{\"template_id\": \""+EARNINGS+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"keyword1\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("keyword1")+"\"}, "
				+ "\"keyword2\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword2")+"\"},"
				+ "\"keyword3\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword3")+"\"},"
				+ "\"keyword4\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword4")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}
	//商品缺货提醒
	private static String sendgoodsstockout(Map<String ,String > mesinfo){
		String jsoninfo="{\"template_id\": \""+GOODSSTOCKOUT+"\",\"topcolor\": \"#FF0000\",\"touser\": \""+mesinfo.get("openid")+"\",\"url\": \""+mesinfo.get("url")+"\", "
				+ "\"data\":"
				+ "{\"first\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("first")+"\" },"
				+ "\"keyword1\": {\"color\": \"#173177\",\"value\":\""+mesinfo.get("keyword1")+"\"}, "
				+ "\"keyword2\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("keyword2")+"\"},"
				+ "\"remark\": {\"color\": \"#173177\",\"value\": \""+mesinfo.get("remark")+"\" }}}";
		return jsoninfo;
	}

}
