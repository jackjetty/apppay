package com.ris.mobile.ecloud.parser;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.ApplyPayObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;  
import com.ris.mobile.ecloud.object.SkuObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class ApplyPayParser extends BaseParser<ApplyPayObject>  { 
		@Override
		public ApplyPayObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					ApplyPayObject applyPayObject=JSON.parseObject(resultData, ApplyPayObject.class);
					JSONObject jsonObject = new JSONObject(resultData); 
					String skuList= jsonObject.getString("skuList");  
					if(!CommonUtil.trim(skuList).equals("")){
						applyPayObject.setSkuList (JSON.parseArray(skuList, SkuObject.class));  
					}
					 
					return applyPayObject;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}