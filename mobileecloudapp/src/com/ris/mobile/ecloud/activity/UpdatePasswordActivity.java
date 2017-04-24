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
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;  
public class UpdatePasswordActivity  extends BaseActivity  implements  OnClickListener {
	 
	public static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	
	@InjectView (R.id.et_CurPassword)
	private EditText etCurPassword;
	
	@InjectView (R.id.et_NewPassword)
	private EditText etNewPassword;
	
	@InjectView (R.id.et_NewPassword2)
	private EditText etNewPassword2;
	
	@InjectView (R.id.btn_UpdatePassword)
	private Button  btnUpdatePassword; 
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatepassword);
		initData(getIntent());
		 
		initView();
		setListener();
		 
	}
	 
	public    void initData(Intent intent){ 
		sharedPreferenceUtil=new SharedPreferenceUtil(UpdatePasswordActivity.this);
		
	}
	public void initView(){
	  
		tvBack.setVisibility(View.VISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_updatepassword);
    	tvRight.setVisibility(View.INVISIBLE);
    	 
    	 
	}
	public void setListener(){
		tvBack.setOnClickListener(this); 
		btnUpdatePassword.setOnClickListener(this);
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
		switch (v.getId()) {
		  case R.id.tv_Back:
			 UpdatePasswordActivity.this.finish();
			 break;
		  case R.id.btn_UpdatePassword:
			  String curPassword=CommonUtil.trim(etCurPassword.getText().toString());
			  String newPassword=CommonUtil.trim(etNewPassword.getText().toString());
			  String newPassword2=CommonUtil.trim(etNewPassword2.getText().toString());
			  if(curPassword.equals("")){
				  DisPlay("error","请输入当前密码！！");
				  etCurPassword.requestFocus();
				  return;
			  }
			  if(newPassword.equals("")){
				  DisPlay("error","请输入正确新密码！！");
				  etNewPassword.requestFocus();
				  return;
			  }
			  if(newPassword2.equals("")||(!newPassword.equalsIgnoreCase(newPassword2))){
				  DisPlay("error","请输入正确确认密码！！");
				  etNewPassword2.requestFocus();
				  return;
			  }
			  
			  
			  HashMap<String, String> requestDataMap=new HashMap<String, String>(); 
			  requestDataMap.put("userId", sharedPreferenceUtil.getUserId());
			  requestDataMap.put("oldPassword",Md5.digest( curPassword ));
			  requestDataMap.put("newPassword",Md5.digest(  newPassword));
			  
			   

              BaseParser<ResponseObject> responseParser = new ResponseParser();
			  
		      RequestObject vo = new RequestObject(R.string.url_updatepassword, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<ResponseObject>() {
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) { 
						   
						  
						  if(paramObject.isSuccess()){
							  DisPlay("correct",CommonUtil.trim(paramObject.getInfo()));
							  sharedPreferenceUtil.setPasswodRemeber(false);
							  sharedPreferenceUtil.setUserPassword(CommonUtil.trim(etNewPassword.getText().toString()));
							  UpdatePasswordActivity.this.finish(); 
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  }
							 
						 
						  //openActivity(MainActivity.class);
						  
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						UpdatePasswordActivity.this.DisPlay("error",responseErrorVo.getErrInfo());
						
						 
		    		}
				});
			 
			  
			   
			  
			  break;
		}
	}
	
	
}
