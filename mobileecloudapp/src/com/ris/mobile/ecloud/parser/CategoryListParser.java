package com.ris.mobile.ecloud.parser;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;    
import com.ris.mobile.ecloud.object.CategoryObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
public class CategoryListParser extends BaseParser<List<CategoryObject>>  { 
		@Override
		public List<CategoryObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<CategoryObject> faqList;
					faqList=JSON.parseArray(resultData, CategoryObject.class) ;
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