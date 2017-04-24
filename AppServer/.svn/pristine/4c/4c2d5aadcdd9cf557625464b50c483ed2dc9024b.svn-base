package com.rising.appserver;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.*;
import java.util.Map;

import javax.xml.namespace.QName; 
import javax.xml.rpc.ServiceException; 

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.utils.Options;

 

public class HttpInvoker {
	 public static final  String nameSpace="http://service.web.rising.com";  
	 public static final  String endpoint="http://115.239.134.175/AppServer/services/HBpayService"; 
	 public static final  String soapActionURI="http://service.web.rising.com/rechargeQQ"; 
	  
	// http://115.239.134.175/AppServer/services/HBpayService
	// ?wsdl
	 
	 public   void sendCarMoveMessage(String access ,String xmlStr ) {
		 
		 try  
		    {   
		        Service service = new Service(); 
		         
	            
	            
		        Call call = new Call(service);  
		        call.setTargetEndpointAddress(new java.net.URL(endpoint));   
		        call.setUseSOAPAction(true);  
		        call.setSOAPActionURI(soapActionURI);   
		        call.setOperationName(new QName(nameSpace, "rechargeQQ")); 
		        call.setTimeout(30000);
		        //输入参数设置   
		        call.addParameter(new QName(nameSpace, "in0"),  
		            org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		        call.addParameter(new QName(nameSpace, "in1"),  
			            org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);  
		      

		        call.setReturnType(new QName(nameSpace, "out"), String.class);   
		        //执行  
		          
		        String output = (String) call.invoke(new Object[]  {access,xmlStr}); 
		        String inMsgXml=URLDecoder.decode(output, "utf-8"); 
		        Map<String, String> map = null;  
		        System.out.println(inMsgXml);
				/*map = XmlUtil.xml2Map(inMsgXml);  
				String msg=map.get("root.body.item.res");
				if(!msg.equalsIgnoreCase("0")){
					 errCodeBean.setErrcode("1");
					 errCodeBean.setErrmsg(msg);
				} */
		       
		  
		    }  
		    catch (Exception e)  
		    {  
		    	 e.printStackTrace();
		    }  
		 
		 
		 
	 } 
	 

	public static void main(String[] args) {
		new HttpInvoker().sendCarMoveMessage("FAC6F9F1F06D414A7F1D7D2EE628" ,"4ljPBKxsvmOu3XcbotYJNOn9Z0Zo1K2RNG0oqPYYR0mdBW1SD/tpVQgm5WSBpPI4HwyJRe4cQg0Y5sJU5s/9MtPsTYP5CEzioyvvF/i0622NHHiFbWf3wDmOF9lC+KnJgnI9YXTFWNy7e/D6Sgdgge289sdPKfQorOavhjgtpx5qxAl55L52ssGzBUxLwz6wUDGFanIvGrpPWHIByQib7NfuI0shXon+zpNGB0LEocDhC9fxLlA7duzWKE7QTn2rmjETlqx3wwAJg5jcFEVcvBerdxZ9tldDY/BHOKXM0PT9gIMCFIRqBV8KzimlkpGaDpmF7/u3VOT8fgPNtqh0OxW2zE+qVfNXySZGbmdgESIbgJ3vUohP5hVQQxR6LnQOotvSl1kDhGMjyUFx9MGod/ZHM6i/5/cG0eu1mL1L5hZqLy5QlR6H+Z3Hkz1On+IqGCVqLBEUVWTb3kbbCvrO/58m/GVG2tAM79sgVy+qhrrbV/3APu36MnEQCWbK3RYX6LIov5VQIqJuT4+ba05OSjNp5Pg+rkEHL/BOhxDso6/hZnHfBqgrOEEHWw8adYvt58xO7XO9YJ4=");
		   
	}
	
	 
}