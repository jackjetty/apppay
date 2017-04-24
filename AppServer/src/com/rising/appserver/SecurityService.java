package com.rising.appserver; 

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

 
 

/**
 * 
 * 类名称：SecurityService   
 * 类描述：与应用服务器通信报文加密服务类
 * @version 1.0.0
 */
public class SecurityService {
	//从AppServer获得的公钥
	private String rsaPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKBCUDVrjCpWxxXhuY2c9j8MO8R+L1zP8qqYKiBDlcSaHmC2ncxIRQzExOBEQv4ZDkuGinuL1INteno/4dzAoZOdzNzzA2F7JDpom/aU6Fba65wECc1FsmN8tqlzOFJLxTyobHFtNYG0dGxBWKrF4xq7eiNoON3F/Tlot/mlsciQIDAQAB";
	//private String rsaPriKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALZCDaoDCJOmTDpe0h3eYZjnNQv/VWKYf+dqPmoycHqUs9OO0c1wlAYRe/Vd3inBeV9vsqhuV3IVF+t29UlTD6LF1G8GmXEtdmLtDEuHUKjDguhlm/NGOWrOj8VywyVmxz0D/D4SZ0t2SQ4ADVns8rgin2APCwQ9Z75yyVtk8bChAgMBAAECgYAqB3dxMjvjGKFRgJZBrwugoAi6mIoPL42nRvfXs4P+sJX7fl7hWkm4UIXeYmQeCvC7Ul3Hu5JMy5J1iTwtSYs8IW7aNanEdcTg+jFXR/bSBPO7GirfiAb6/S7s+3aYgGda8SML6lppJ6f/SwO9my4Co47VW4wJKBSm2nZGjp7/ZQJBANzQSa6tquRzbvh4Lxy/fFprEx3panVUVby9nTvk0FVA31zgvyceDG3PAZqEgsefjo9GhS7CFof7JTtVACYdvb8CQQDTTPS8jmva/JI7lOqCNli1w3Vwyr7WGnlUQeJii2Y19pTMSfEZOCVnNt51KzDW50wsJXabfRsaeAzokS+2uumfAkAxTK+muE93Qe58uxiJqpbw+QcDnqGOII4j3ZHFVYjN//Xenq8O5L6Rpa4N+ZfZSd5iTrRdhCfTpFbjsE6gM/OxAkBMB0ukMOa0A98mGx4KPj3LIQo3zGvAJAR2AcLQTHI5hoDhxNVAishCjCadKC5JlS3+UHiN2AURkBKs99igOj7nAkAM5ST0hktO783wW5D+VzEDRfF5qcPLoXpb1j1p581/gXXsQoAi3AKdKWyKGGEgcvcQ/Dpmpwdqt4qRt6CFA21v";
	private static SecurityService securityService;
	private byte[] encryptByPrivateKey;
	private SecurityService(){}
	private String rsaPriKey;
	private String rsab3= "ZCDaoDCJOmTDpe0h3eYZjnNQv/VWKYf+dqPmoycHqUs9OO0c1w";
	private String rsab11= "jmva/JI7lOqCNli1w3Vwyr7WGnlUQeJii2Y19pTMSfEZOCVnNt";
	private String rsab7= "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL";
	private String rsab2= "lAYRe/Vd3inBeV9vsqhuV3IVF+t29UlTD6LF1G8GmXEtdmLtDE";
	private String rsab1= "u5JMy5J1iTwtSYs8IW7aNanEdcTg+jFXR/bSBPO7GirfiAb6/S";
	private String rsab10= "uHUKjDguhlm/NGOWrOj8VywyVmxz0D/D4SZ0t2SQ4ADVns8rgi";
	private String rsab8= "n2APCwQ9Z75yyVtk8bChAgMBAAECgYAqB3dxMjvjGKFRgJZBrw";
	private String rsab15= "51KzDW50wsJXabfRsaeAzokS+2uumfAkAxTK+muE93Qe58uxiJ";
	private String rsab12= "ugoAi6mIoPL42nRvfXs4P+sJX7fl7hWkm4UIXeYmQeCvC7Ul3H";
	private String rsab5= "7s+3aYgGda8SML6lppJ6f/SwO9my4Co47VW4wJKBSm2nZGjp7/";
	private String rsab6;
	private String rsab4= "zgvyceDG3PAZqEgsefjo9GhS7CFof7JTtVACYdvb8CQQDTTPS8";
	private String rsab13= "qpbw+QcDnqGOII4j3ZHFVYjN//Xenq8O5L6Rpa4N+ZfZSd5iTr";
	private String rsab16= "RdhCfTpFbjsE6gM/OxAkBMB0ukMOa0A98mGx4KPj3LIQo3zGvA";
	private String rsab9= "JAR2AcLQTHI5hoDhxNVAishCjCadKC5JlS3+UHiN2AURkBKs99";
	private String rsab14= "igOj7nAkAM5ST0hktO783wW5D+VzEDRfF5qcPLoXpb1j1p581/";
	private String rsab17= "gXXsQoAi3AKdKWyKGGEgcvcQ/Dpmpwdqt4qRt6CFA21v";

	public static void main(String[] args) throws Exception{
		String xmlStr="<?xml version='1.0' encoding='utf-8'?><mcp type='request'><params><param id='PageIndex' value='1'/><param id='PhoneNumber' value='18072749082'/><param id='PageSize' value='20'/></params></mcp>";
		xmlStr = SecurityService.getSingleInstance().encodeMsg(
				SecurityService.getSingleInstance()
						.encodeReqMsg(xmlStr));
		System.out.println(xmlStr);
	}
	
	public static SecurityService getSingleInstance(){
		if(securityService == null){
			securityService = new SecurityService();
		}
		return securityService;
	}
	
	/**
	 * 加密请求报文
	 * @param text：内容
	 * @return
	 * @throws EncodeMsgException 
	 */
	public String encodeReqMsg(String text) throws  Exception{
		if(text != null && "".equals(text.trim())){
			text = "noMsg";
		}
		
		try {
			rsab6= "ZQJBANzQSa6tquRzbvh4Lxy/fFprEx3panVUVby9nTvk0FVA31";
			rsaPriKey = rsab7 + rsab3 + rsab2 +rsab10 + rsab8 + rsab12 + rsab1 + rsab5 + rsab6 + rsab4 + rsab11 + rsab15 + rsab13 + rsab16 + rsab9 + rsab14 + rsab17;
			encryptByPrivateKey = RSAUtils.encryptByPrivateKey(text.getBytes(), rsaPriKey);
		} catch (Exception e) {
			throw new  Exception("RSA私钥签名异常" + e.getMessage());
		}
		
		String base64Str;
		try{
			base64Str = Base64Utils.encode(encryptByPrivateKey);
		}
		catch(Exception e){
			throw new  Exception("Base64编码异常" + e.getMessage());
		}

		return base64Str;
	}
	
	/**
	 * 解密请求报文
	 * @param cmpMsg 加密报文 
	 * @return
	 */
	public String decodeMCPMsg(String cmpMsg) throws  Exception{
		
		try {
			byte[] bcmpMsg = Base64Utils.decode(cmpMsg);
			byte[] decodedData = RSAUtils.decryptByPublicKey(bcmpMsg, rsaPubKey);
	        return new String(decodedData);
		} catch (Exception e) {
			throw new  Exception("解密报文异常:"+e.getMessage());
		}
		
	}
	
	public String encodeMsg(String msg) throws  Exception{
		String encodeMsg;
		try {
			encodeMsg = URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new  Exception("消息编码异常" + e.getMessage());
		}
		return encodeMsg;
	}
	
	/**
	 * 解码请求
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String decodeMsg(String msg) throws  Exception {
		String mcpMsg;
		try {
			mcpMsg = URLDecoder.decode(msg.toString(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new  Exception("解密消息异常" + e.getMessage());
		}
		return mcpMsg;
	}
	
}
