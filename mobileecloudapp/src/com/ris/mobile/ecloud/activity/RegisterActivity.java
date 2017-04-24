package com.ris.mobile.ecloud.activity; 
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import roboguice.inject.InjectView;

import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject; 
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.Md5;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;  
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;  
public class RegisterActivity  extends BaseActivity  implements  OnClickListener {
	public static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	
	@InjectView (R.id.et_PhoneNumber)
	private EditText etPhoneNumber;
	
	@InjectView (R.id.et_VerifyCode)
	private EditText etVerifyCode;
	
	@InjectView (R.id.et_Password)
	private EditText etPassword;
	
	@InjectView (R.id.et_ConfirmPassword)
	private EditText etConfirmPassword;
	
	@InjectView (R.id.et_UserName)
	private EditText etUserName;
	
	@InjectView (R.id.et_IDCard)
	private EditText etIDCard;
	
	@InjectView (R.id.cb_Agree)
	private CheckBox cbAgree;
	
	@InjectView (R.id.tv_ServiceAgree)
	private TextView tvServiceAgree;
	 
	@InjectView (R.id.btn_Verify)
	private Button  btnVerify; 
	
	@InjectView (R.id.btn_Register)
	private Button  btnRegister;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initData(getIntent());
	 
		initView();
		setListener();
		 
	}
	 
	public    void initData(Intent intent){ 
		sharedPreferenceUtil=new SharedPreferenceUtil(RegisterActivity.this);
		
	}
	public void initView(){
	  
		tvBack.setVisibility(View.VISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_register);
    	tvRight.setVisibility(View.INVISIBLE);
    	 
    	 
	}
	public void setListener(){
		tvBack.setOnClickListener(this); 
		btnVerify.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		tvServiceAgree.setOnClickListener(this);
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
		String phoneNumber;
		switch (v.getId()) {
		  case R.id.tv_Back:
			 RegisterActivity.this.finish();
			 break;
		  case R.id.btn_Verify:
			  if(!btnVerify.isEnabled()){
					return;
				}
			   phoneNumber=CommonUtil.trim(etPhoneNumber.getText().toString());
			  if(!CommonUtil.isValidMobiNumber(phoneNumber)){
				  DisPlay("error","请输入正确的手机号码！！");
				  etPhoneNumber.requestFocus();
				  return;
			  }
			  requestDataMap=new HashMap<String, String>();
			  requestDataMap.put("mobile",  phoneNumber);
			  
              BaseParser<ResponseObject> responseParser1 = new ResponseParser();
			  
		      RequestObject vo1 = new RequestObject(R.string.url_verifyregister, this, requestDataMap, responseParser1);
				 
			  getDataFromServer(vo1, new DataCallback<ResponseObject>() {  
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) {
						if(paramObject.isSuccess()){
							new Thread(new Times()).start();
							  
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  }
						return; 

					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						  
						 DisPlay("error",responseErrorVo.getErrInfo());
						 
		    		}
				},true);
				
				break;
		  case R.id.tv_ServiceAgree:	 
			 Bundle myBundel=new Bundle(); 
			 myBundel.putString("wapUrl", sharedPreferenceUtil.getRegisterAgreeUrl()); 
			 myBundel.putString("wapTitle","服务协议"); 
			 openActivity(WebViewActivity.class,myBundel);
			 break;	
		  case R.id.btn_Register:  
			   phoneNumber=CommonUtil.trim(etPhoneNumber.getText().toString());
			  String verifyCode=CommonUtil.trim(etVerifyCode.getText().toString());
			  String password=CommonUtil.trim(etPassword.getText().toString());
			  String confirmPassword=CommonUtil.trim(etConfirmPassword.getText().toString());
			  String userName=CommonUtil.trim(etUserName.getText().toString());
			  String idCard=CommonUtil.trim(etIDCard.getText().toString());
			  if(!CommonUtil.isValidMobiNumber(phoneNumber)){
				  DisPlay("error","请输入正确的手机号码！！");
				  etPhoneNumber.requestFocus();
				  return;
			  }
			  if(!CommonUtil.isInteger(verifyCode)){
				  DisPlay("error","请输入正确验证码！！");
				  etVerifyCode.requestFocus();
				  return;
			  }
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
			  if(userName.equals("")){
				  DisPlay("error","请输入姓名！！");
				  etUserName.requestFocus();
				  return;
			  } 
			  
			  if(!CommonUtil.isIDNum(idCard) ){
				  DisPlay("error","请输入身份证号码！！");
				  etVerifyCode.requestFocus();
				  return;
			  }
			  
			  if(!cbAgree.isChecked()){
				  DisPlay("error","请勾选服务协议！！"); 
				  return;
				  
			  }
			  requestDataMap=new HashMap<String, String>(); 
			  
			  requestDataMap.put("mobile", phoneNumber);
			  requestDataMap.put("verifyCode",verifyCode );
			  requestDataMap.put("password", Md5.digest(password));
			  requestDataMap.put("confirmPassword", Md5.digest(confirmPassword));
			  requestDataMap.put("realName", userName);
			  requestDataMap.put("idCard", idCard); 
			  
			  
			  
			  	 

			  
              BaseParser<ResponseObject> responseParser = new ResponseParser();
			  
		      RequestObject vo = new RequestObject(R.string.url_register, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<ResponseObject>() {
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) { 
						   
						  
						  if(paramObject.isSuccess()){
							  DisPlay("correct",CommonUtil.trim(paramObject.getInfo()));
							  sharedPreferenceUtil.setUserAccount (CommonUtil.trim(etPhoneNumber.getText().toString()));
							  sharedPreferenceUtil.setUserPassword(CommonUtil.trim(etPassword.getText().toString()));
							  sharedPreferenceUtil.setPasswodRemeber(true);
							  openActivity(LoginActivity.class);
							  RegisterActivity.this.finish(); 
							  
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  }
							 
						 
						 
						  
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						 
						RegisterActivity.this.DisPlay("error",responseErrorVo.getErrInfo());
		    		}
				}); 
			  break;
		}
	}
	
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg2) {
			case 0: 
				btnVerify.setEnabled(false);
				btnVerify.setTextColor(getResources().getColor(R.color.text_blue));
				btnVerify.setText(new Integer(msg.arg1).toString() ); 
				btnVerify.setBackgroundDrawable(null);
				btnVerify.setText("    "+new Integer(msg.arg1).toString()+"秒    " ); 
				btnVerify.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_verify_disable));   
				
				//btnVerify.setTextSize(getResources().getDimension(R.dimen.textSize_normal));	
				if (msg.arg1 == 1) { 
							 
					// btnVerify.setTextSize(getResources().getDimension(R.dimen.textSize_normal));			 
					 btnVerify.setEnabled(true);
					 btnVerify.setText("发送验证码"); 
					 btnVerify.setTextColor(getResources().getColor(R.color.white));
					 btnVerify.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_verify));
					 				 				 
				}
				break;
				 
			 
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	private class Times implements Runnable{
		@Override
		public void run() {

			int a = 60;
			while(true){
				try {
					
					Message message = new Message();
					message.arg2 = 0;
					message.arg1 = a;
					handler.sendMessage(message);
					if (a == 1) {
						break;
					}
					a = a-1;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
					
		}
	}
}












