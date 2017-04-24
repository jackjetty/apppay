package com.ris.mobile.ecloud.parser;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;     
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class ParListParser extends BaseParser<List<ParObject>>  { 
		@Override
		public List<ParObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<ParObject> parList;
					parList=JSON.parseArray(resultData, ParObject.class) ;
					return  parList;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}