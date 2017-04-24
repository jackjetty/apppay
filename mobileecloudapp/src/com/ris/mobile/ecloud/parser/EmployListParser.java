package com.ris.mobile.ecloud.parser;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;     
import com.ris.mobile.ecloud.object.EmployObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class EmployListParser extends BaseParser<List<EmployObject>>  { 
		@Override
		public List<EmployObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<EmployObject> recruitList;
					recruitList=JSON.parseArray(resultData, EmployObject.class) ;
					return  recruitList;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}