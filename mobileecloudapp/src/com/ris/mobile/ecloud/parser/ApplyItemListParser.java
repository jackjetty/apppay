package com.ris.mobile.ecloud.parser; 
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;    
import com.ris.mobile.ecloud.object.ApplyItemObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
public class ApplyItemListParser extends BaseParser<List<ApplyItemObject>>  { 
		@Override
		public List<ApplyItemObject> parseJSON(String paramString)  { 
			 
			checkResponse(paramString);   
			if (this.connectErrorObject.getErrCode()==0) {   
				try{  
					List<ApplyItemObject> applyItemList;
					applyItemList=JSON.parseArray(resultData, ApplyItemObject.class) ;
					return  applyItemList;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}