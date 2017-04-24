package com.rising.appserver.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.*;
import java.util.Map; 

import javax.xml.namespace.QName; 
import javax.xml.rpc.ServiceException;  

import org.apache.axis.client.Call; 
import org.apache.axis.utils.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils; 

import com.rising.appserver.common.BaseService;
import com.rising.appserver.common.CommonUtil; 
import com.rising.appserver.common.XmlUtil;
@Service("billBalanceService")
public class BillBalanceService extends BaseService {
	
	 public static final  String telecom_nameSpace="http://zj.189.cn/balancebenzeng";  
	 public static final  String telecom_endpoint="http://122.224.205.10/uap_ess_api_ocs/services/balancebenzengService"; 
	 public static final  String telecom_soapActionURI="http://zj.189.cn/balancebenzeng/balance_info_benzeng_query"; 
	 
	 public HashMap<String,Object> telecomBalance(String phoneNumber, String  cityCode) {
		//调用webservice查询了啊
		HashMap<String,Object>  resultMap=new HashMap<String,Object> ();
		 
		try  
	    {   
			org.apache.axis.client.Service wsdlService = new org.apache.axis.client.Service();  
	        Call call = new Call(wsdlService);  
	        call.setTargetEndpointAddress(new java.net.URL(telecom_endpoint));   
	        call.setUseSOAPAction(true);  
	        call.setSOAPActionURI(telecom_soapActionURI);   
	        call.setOperationName(new QName(telecom_nameSpace, "balance_info_benzeng_query")); 
	        call.setTimeout(30000);
	        //输入参数设置   
	        call.addParameter(new QName(telecom_nameSpace, "in0"),  
	            org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
	        
	      

	        call.setReturnType(new QName(telecom_nameSpace, "out"), String.class); 
	        StringBuffer soapDataBuffer=new StringBuffer("");
	        String accounttype="Serial Number";
	        String srcsysid="168CZB";
	        String inputtime=CommonUtil.doDateForm(new Date(),"yyyyMMddHHmmss");
	        String clientip="115.239.137.226";
	        
	        
	        soapDataBuffer.append("<?xml version='1.0' encoding='UTF-8'?>\n");
	        soapDataBuffer.append("<ROOT FuncName='balance_info_benzeng_query' City='WT'>\n");
	        soapDataBuffer.append("<accountid>").append(phoneNumber).append("</accountid>\n");
	        soapDataBuffer.append("<accounttype>").append(accounttype).append("</accounttype>\n");
	        soapDataBuffer.append("<areaid>").append(cityCode).append("</areaid>\n");
	        soapDataBuffer.append("<srcsysid>").append(srcsysid).append("</srcsysid>\n");
	        soapDataBuffer.append("<inputtime>").append(inputtime).append("</inputtime>\n");
	        soapDataBuffer.append("<clientip>").append(clientip).append("</clientip>\n"); 
			 
			String key = "ndf6l4onxqr4lbvc2dexzonf5ntvkrfp";
			//String key = "w168CZBPtR7scVM1Miv4gD5"; 
			String parameter = "accountid" + phoneNumber + "accounttype"
					+ accounttype + "srcsysid" + srcsysid + "inputtime" + inputtime
					+ "clientip" + clientip + key;
			String sign = DigestUtils.md5Hex(parameter);
			soapDataBuffer.append("<sign>").append(sign).append("</sign>\n"); 
			soapDataBuffer.append("<sign_type>MD5</sign_type>\n"); 
			soapDataBuffer.append("</ROOT>");  
	        String output = (String) call.invoke(new Object[]  {soapDataBuffer.toString()}); 
	        String inMsgXml=URLDecoder.decode(output, "utf-8"); 
	        Map<String, String> map = null;  
	         
			map = XmlUtil.xml2Map(inMsgXml);   
			String retCode=CommonUtil.trim(map.get("ROOT.ResponseMessage.retCode"));
			String errMsg=CommonUtil.trim(map.get("ROOT.ResponseMessage.errMsg"));
			if(retCode.equalsIgnoreCase("0")){
				resultMap.put("retCode", retCode); 
				resultMap.put("balance", CommonUtil.trim(map.get("ROOT.ResponseMessage.balance")) );
				resultMap.put("benjin", CommonUtil.trim(map.get("ROOT.ResponseMessage.benjin")) );
				resultMap.put("zengjin", CommonUtil.trim(map.get("ROOT.ResponseMessage.zengjin")) ); 
			}else{
				resultMap.put("retCode", retCode); 
				resultMap.put("errMsg", errMsg );
			} 
	       
	  
	    }  
	    catch (Exception e)  
	    {    
	    	 resultMap.put("retCode", "99"); 
			 resultMap.put("errMsg", "超时查询出错！！" );
	    	 e.printStackTrace();
	    }  
		
		return resultMap;
	}
	
	
}