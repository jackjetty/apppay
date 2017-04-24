package com.ris.mobile.ecloud.parser;
 
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;    
import com.ris.mobile.ecloud.object.FAQObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
public class FAQListParser extends BaseParser<List<FAQObject>>  { 
		@Override
		public List<FAQObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{ 
					
					List<FAQObject> faqList;
					faqList=JSON.parseArray(resultData, FAQObject.class) ;
					return  faqList;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}