package com.ris.mobile.ecloud.parser;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;     
import com.ris.mobile.ecloud.object.ApplyDealRecordObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class ApplyDealRecordListParser extends BaseParser<List<ApplyDealRecordObject>>  { 
		@Override
		public List<ApplyDealRecordObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<ApplyDealRecordObject> recruitList;
					recruitList=JSON.parseArray(resultData, ApplyDealRecordObject.class) ;
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