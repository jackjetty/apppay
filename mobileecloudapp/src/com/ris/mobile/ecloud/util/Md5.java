package com.ris.mobile.ecloud.util;
 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类名称：MD5
 * 类描述：MD5加密 
 * 
 * @version 1.0.0
 * 
 */
public class Md5 {

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 * @return
	 */
	
	public static void main(String[] args){
		try {
			System.out.println(encryptByMD5("123456"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String encryptByMD5(String plainText)  {
		String ciphertext="";
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			
			md5.update(plainText.getBytes());
		    ciphertext = new String(md5.digest());
		}catch(Exception ex){
			
		}
		
		return ciphertext;
	}

	public static String encryptByMD5With16Bit(String plainText)
			throws NoSuchAlgorithmException {
		return encryptByMD5With32Bit(plainText).substring(8, 24);
	}

	public static String encryptByMD5With32Bit(String plainText)
			throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(plainText.getBytes());
		byte b[] = md5.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
	
	public static String digest(String value) {
		StringBuilder sb = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(value.getBytes());
			sb = new StringBuilder();
			for (byte b : result) {
				String hexString = Integer.toHexString(b & 0xFF);
				if (hexString.length() == 1) {
					sb.append("0" + hexString);// 0~F
				} else {
					sb.append(hexString);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

}
