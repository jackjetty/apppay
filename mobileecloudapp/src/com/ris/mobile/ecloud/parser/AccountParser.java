package com.ris.mobile.ecloud.parser;
import org.json.JSONException;
import org.json.JSONObject; 
import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.object.AccountObject;
public class AccountParser extends BaseParser<AccountObject>  { 
		@Override
		public AccountObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					return JSON.parseObject(resultData, AccountObject.class);
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}