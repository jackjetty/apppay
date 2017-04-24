package com.ris.mobile.ecloud.parser;
 

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;    
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.object.SystemConfigObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
public class SystemConfigParser extends BaseParser<SystemConfigObject>  { 
		@Override
		public SystemConfigObject parseJSON(String paramString)  { 
			//paramString="{\"message\":\"成功\",\"result\":1,\"data\":{\"apkUrl\":\"http://112.17.8.198:8008/citrus/temp/mobileecloudapp.apk\",\"forceUpdate\":true,\"advertList\":[{\"advertName\":\"广告2\",\"imageUrl\":\"http://p2.qqyou.com/pifu/UploadPic/2014-10/25/2014102522382619897.jpeg\"},{\"advertName\":\"广告1\",\"imageUrl\":\"http://pic.to8to.com/attch/day_160218/20160218_6410eaeeba9bc1b3e944xD5gKKhPEuEv.png\"}],\"parList\":[{\"parName\":\"10.55元\",\"parCode\":1,\"parValue\":\"10.55\"},{\"parName\":\"20.00元\",\"parCode\":2,\"parValue\":\"20.00\"},{\"parName\":\"30.00元\",\"parCode\":3,\"parValue\":\"30.00\"},{\"parName\":\"50.00元\",\"parCode\":4,\"parValue\":\"50.00\"},{\"parName\":\"100.00元\",\"parCode\":5,\"parValue\":\"100.00\"}],\"versionName\":\"V1.0\",\"versionCode\":1,\"versionInfo\":\"初始版本，功能列表如下：。。。。\",\"rechargeAgreeUrl\":\"http://121.40.149.140:8899/rising-zptc-client//ignore/recharge_instructions.html\",\"registerAgreeUrl\":\"http://121.40.149.140:8899/rising-zptc-client//ignore/service_agreement.html\"}}";
			checkResponse(paramString); 
			 
			 
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					SystemConfigObject systemConfigObject=JSON.parseObject(resultData, SystemConfigObject.class);
					JSONObject jsonObject = new JSONObject(resultData); 
					
					String advertList= jsonObject.getString("advertList");  
					if(!CommonUtil.trim(advertList).equals("")){
						systemConfigObject.setAdvertList(JSON.parseArray(advertList, AdvertObject.class));  
					}
					String parList= jsonObject.getString("parList");  
					if(!CommonUtil.trim(parList).equals("")){
						systemConfigObject.setParList(JSON.parseArray(parList, ParObject.class));
						 
					}
					return  systemConfigObject;
				}catch(Exception ex){ 
					ex.printStackTrace();
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			} 
			return null;  
	   }  
}