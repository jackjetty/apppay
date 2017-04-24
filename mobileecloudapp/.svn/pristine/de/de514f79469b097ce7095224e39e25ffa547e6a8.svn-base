package com.ris.mobile.ecloud.parser;
import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.ApplySignDetailObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;  
public class ApplySignResParser extends BaseParser<ApplySignDetailObject>  { 
		@Override
		public ApplySignDetailObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					return JSON.parseObject(resultData, ApplySignDetailObject.class);
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}