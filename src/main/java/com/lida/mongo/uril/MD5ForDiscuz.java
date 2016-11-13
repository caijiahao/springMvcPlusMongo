package com.lida.mongo.uril;

import java.security.MessageDigest;

/**
 * md5����
 * 
 * @author Administrator
 * 
 */
public class MD5ForDiscuz {
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String aa = "3d7b0@2a";
		String bb = "SDK-NSF-010-00039";
		MD5ForDiscuz md5 = new MD5ForDiscuz();
		String md5aa = md5.MD5(bb+aa);
		StringBuffer sb = new StringBuffer();
		sb.append(md5aa);
		sb.append(bb);
		String pwd = sb.toString();
		String md5aa1 = md5.MD5(pwd);

		System.out.println(md5aa);
		System.out.println(md5aa1);

		System.out.println(md5aa.toLowerCase());
	}
}