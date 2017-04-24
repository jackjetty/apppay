package com.ris.mobile.ecloud.parser;
import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.ApplyPayDetailObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;  
public class ApplyPayResParser extends BaseParser<ApplyPayDetailObject>  { 
		@Override
		public ApplyPayDetailObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					return JSON.parseObject(resultData, ApplyPayDetailObject.class);
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}