package com.ris.mobile.ecloud.parser;
 
import org.json.JSONException;
import org.json.JSONObject; 
import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.object.ResponseObject;
public class ResponseParser extends BaseParser<ResponseObject>  { 
		@Override
		public ResponseObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					
					ResponseObject responseObject=new ResponseObject();
					responseObject.setSuccess(true);
					responseObject.setInfo(connectErrorObject.getErrInfo());
					return responseObject;
					
					//return JSON.parseObject(paramString, ResponseObject.class);
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}