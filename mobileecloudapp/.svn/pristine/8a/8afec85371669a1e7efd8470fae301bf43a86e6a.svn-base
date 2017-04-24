package com.ris.mobile.ecloud.parser;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;     
import com.ris.mobile.ecloud.object.ArticleObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class ArticleListParser extends BaseParser<List<ArticleObject>>  { 
		@Override
		public List<ArticleObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<ArticleObject> recruitList;
					recruitList=JSON.parseArray(resultData, ArticleObject.class) ;
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