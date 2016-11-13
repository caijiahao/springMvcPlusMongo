package com.lida.mongo.uril;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/** 
 * ����У�鹤���� 
 *  
 */  
public class SignUtil {
    private static String token = "co3oopVScm6zzoQWb04_Nlss9r04Ag4B8GfBUrbbvgFbxNvXK15WXqNEFjIHO07obMyya_tHkwTw7NJekr-hhMn_dTnVPGN7pMg0oZGcsXh6iBQhoFYUF8cGPKdbtRqYEVYbAGAOFI";
  
    /** 
     * ��֤ǩ�� 
     *  
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
  
    /** 
     * ���ֽ�����ת��Ϊʮ�������ַ��� 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * ���ֽ�ת��Ϊʮ�������ַ��� 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
}  