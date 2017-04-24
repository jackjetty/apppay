package com.ris.mobile.ecloud.activity;
import java.util.HashMap;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
public class FindUpdatePasswordActivity  extends BaseActivity  implements  OnClickListener {
	 
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	
	@InjectView (R.id.et_Password)
	private EditText etPassword;
	
	@InjectView (R.id.et_ConfirmPassword)
	private EditText etConfirmPassword;
	
	@InjectView (R.id.btn_FindUpdatePW)
	private Button btnFindUpdatePW;
	
	
	private String phoneNumber,verifyCode;
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	public static LApplication lApplication=LApplication.getInstance();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findupdatepassword);
		initData(getIntent()); 
		initView();
		setListener();
		 
	}
	public    void initData(Intent intent){ 
		phoneNumber = CommonUtil.trim( intent.getStringExtra("phoneNumber"));
		verifyCode = CommonUtil.trim(intent.getStringExtra("verifyCode"));
   	 
	}
	public void initView(){
		    sharedPreferenceUtil=new SharedPreferenceUtil(FindUpdatePasswordActivity.this);
			tvBack.setVisibility(View.VISIBLE);
	    	tvTitle.setVisibility(View.VISIBLE);
	    	tvTitle.setText(R.string.title_findupdatepassword);
	    	tvRight.setVisibility(View.INVISIBLE); 
	    	
	    	 
	}
	
	public void setListener(){
		tvBack.setOnClickListener(this); 
		btnFindUpdatePW.setOnClickListener(this); 
	}
	@Override
	public void onConfigurationChanged(Configuration config) {
			 super.onConfigurationChanged(config); 
			if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			 
			} 
			if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			 
			 
			}
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		HashMap<String, String> requestDataMap;
		 
		switch (v.getId()) {
		  case R.id.tv_Back:
			  FindUpdatePasswordActivity.this.finish();
			 break;
		   
		  
		  case R.id.btn_FindUpdatePW:  
			   phoneNumber=CommonUtil.trim(phoneNumber);
			   verifyCode=CommonUtil.trim(verifyCode);
			  
			  if(!CommonUtil.isValidMobiNumber(phoneNumber)){
				  DisPlay("error","传递的手机号有误！！"); 
				  return;
			  }
			  if(!CommonUtil.isInteger(verifyCode)){
				  DisPlay("error","传递的验证码有误！！");
				    
				  return;
			  }
			  String  password=CommonUtil.trim(etPassword.getText().toString());
			  String  confirmPassword=CommonUtil.trim(etConfirmPassword.getText().toString());
			  if(password.equals("")){
				  DisPlay("error","请输入密码！！");
				  etPassword.requestFocus();
				  return;
			  } 
			  
			  if(confirmPassword.equals("")||(!confirmPassword.equals(password))){
				  DisPlay("error","请输入正确确认密码！！");
				  etConfirmPassword.requestFocus();
				  return;
			  }
			  
			  requestDataMap=new HashMap<String, String>(); 
			  requestDataMap.put("mobile", phoneNumber);
			  requestDataMap.put("verifyCode",verifyCode );
			  requestDataMap.put("newPassword",password );  
			  
			  
              BaseParser<ResponseObject> responseParser = new ResponseParser();
			  
		      RequestObject vo = new RequestObject(R.string.url_retrieveupdatepassword, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<ResponseObject>() {
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) {
						  if(paramObject.isSuccess()){
							  DisPlay("correct",CommonUtil.trim(paramObject.getInfo())); 
							  FindUpdatePasswordActivity.this.finish();
							  
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  } 
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						 
						DisPlay("error",responseErrorVo.getErrInfo());
		    		}
				}); 
			  break;
		}
	}
	 
	
	
}