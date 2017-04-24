package com.rising.appserver;
 

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
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws  Exception{
		String  md5Decode = Md5.encryptByMD5With16Bit("123456");
		
		md5Decode=Base64Utils.encode(md5Decode.getBytes());
		System.out.println(md5Decode);
		 
		
	}
	public static String encryptByMD5(String plainText) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(plainText.getBytes());
		String ciphertext = new String(md5.digest());
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
