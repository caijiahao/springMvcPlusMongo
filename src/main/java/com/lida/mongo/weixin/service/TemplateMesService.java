package com.lida.mongo.weixin.service;


public class TemplateMesService {
    /*
	*//**
     * 体验店新订单提醒模板消息
     *//*
	public void neworders(String order_sn){
		//获取订单信息
		YlOrderInfo ylOrderInfo=ylOrderInfoDao.neworder(order_sn);
		//获取绑定该体验店的企业会员信息
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(ylOrderInfo.getService_id());
		String goodsnumber=String.valueOf(ylOrderInfo.getGoods_number());
		System.out.println(goodsnumber);
		//时间转换
		String addtime=yeEtWxBindDao.TimeStamp2Date(ylOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			//组装模板消息
			Map<String, String> mesinfo = new HashMap<String, String>();
			mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//微信openid oETn-sqq2T92ncxeAR_Xvdd12L68
			mesinfo.put("url", "http://m.ysh365.com");//点击以后跳转的链接地址
			mesinfo.put("first", "您好，云商城新收到一笔新订单等待拆分");
			mesinfo.put("orderno", order_sn);//订单号
			mesinfo.put("refundno",goodsnumber);//商品数量
			mesinfo.put("refundproduct", ylOrderInfo.getOrder_amount()+"元");//商品金额
			mesinfo.put("remark", "下单时间:"+addtime+","+"如有问题请致电，小云将第一时间为您服务！");
			try {
				String res=	templateMes.sendWXMes("neworders", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	*//**
     * 供货商发货提醒
     *//*
	public void ordersshipments(String order_sn) {
		Map<String, String> mesinfo = new HashMap<String, String>();
		//获取子订单信息
		YeOrderInfo yeOrderInfo=yeOrderInfoDao.getyeordersn(order_sn);
		//查询出该体验店绑定的会员
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(yeOrderInfo.getSupply_id());
		//时间转换
		String addtime=yeEtWxBindDao.TimeStamp2Date(yeOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			mesinfo.put("openid", list.get(i).getOpenid());//微信openid
			mesinfo.put("url", "http://m.ysh365.com");//点击以后跳转的链接地址
			mesinfo.put("first", "您好，供货商已发货，请及时收货哦~");
			mesinfo.put("orderno", order_sn);//订单号
			mesinfo.put("refundno", yeOrderInfo.getOrder_amount()+"");//消费金额
			mesinfo.put("refundproduct", addtime);//时间
			mesinfo.put("remark", "如有问题请致电~~或直接在微信留言，小云将第一时间为您服务！");
			try {
				String res=	templateMes.sendWXMes("ordersshipments", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	*//**
     *体验店收益提醒
     *//*
	public void Earnings(String order_sn) {
		YeOrderInfo yeOrderInfo= yeOrderInfoDao.getyeordersn(order_sn);
		//获取绑定该体验店的企业会员信息
		List<YeEtWxBind> list=yeEtWxBindDao.getopenid(yeOrderInfo.getSupply_id());
		//时间转换
		String addtime=yeEtWxBindDao.TimeStamp2Date(yeOrderInfo.getAdd_time());
		for(int i=0;i<list.size();i++){
			Map<String, String> mesinfo = new HashMap<String, String>();
			mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//微信openid
			mesinfo.put("url", "http://m.ysh365.com");//点击以后跳转的链接地址
			mesinfo.put("first", "尊敬的用户您好,您有一个收益订单");
			mesinfo.put("keyword1", order_sn);//订单号
			mesinfo.put("keyword2", yeOrderInfo.getOrder_amount()+"");//消费金额
			mesinfo.put("keyword3", addtime);//支付时间
			mesinfo.put("keyword4", "");//返利金额
			mesinfo.put("remark", "如有问题请致电~~或直接在微信留言，小云将第一时间为您服务！");
			try {
				String res=	templateMes.sendWXMes("earnings", mesinfo);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	*//**
     * 供货商商品缺货预警
     *//*
	public void goodsstockout(int product_id) {
		Map<String, String> mesinfo = new HashMap<String, String>();
		YeEtSupplyProducts yeEtSupplyProducts=yeEtSupplyProductsDao.stock(product_id);
		mesinfo.put("openid", "oETn-sqq2T92ncxeAR_Xvdd12L68");//微信openid
		mesinfo.put("url", "http://m.ysh365.com");//点击以后跳转的链接地址
		mesinfo.put("first", "尊敬的供货商会员您好,您有一个商品库存报警");
		mesinfo.put("keyword1", yeEtSupplyProducts.getGoods_name());//商品名称
		mesinfo.put("keyword2", "剩余库存"+yeEtSupplyProducts.getStock());//有效期
		mesinfo.put("remark", "请及时添加库存,如有问题请致电~~或直接在微信留言，小云将第一时间为您服务！");
		try {
			String res=	templateMes.sendWXMes("goodsstockout", mesinfo);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/

}
