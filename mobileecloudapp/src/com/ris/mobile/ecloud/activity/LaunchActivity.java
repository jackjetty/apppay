package com.ris.mobile.ecloud.activity;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector; 

import roboguice.inject.InjectView;

import com.alibaba.fastjson.JSON;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.BaseDialog;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication;
 
import com.ris.mobile.ecloud.listener.UpdateApkClickListener;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
 
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.object.SystemConfigObject;
 
import com.ris.mobile.ecloud.parser.BaseParser; 
import com.ris.mobile.ecloud.parser.SystemConfigParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant; 
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.PhoneUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.AlertDialog;
import com.ris.mobile.ecloud.widget.LoadingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import android.widget.LinearLayout.LayoutParams;
public class LaunchActivity  extends BaseActivity  implements  OnClickListener {
	 
     
	
	
	@InjectView (R.id.tv_Version) 
	private TextView tvVersion;
	
	@InjectView (R.id.tv_CopyRight) 
	private TextView tvCopyRight;
	
	private PackageInfo packageInfo  ;
	//appContext = (AppContext)this.getApplicationContext();
	public static LApplication lApplication=LApplication.getInstance();
	private SystemConfigObject systemConfigObject;
	public AlertDialog mAlertDialog;
	private SharedPreferenceUtil sharedPreferenceUtil;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		initData(getIntent()); 
		initView();
		setListener();
		 
	}
	public  void initData(Intent intent){
		//super.TAG=this.TAG;
		  sharedPreferenceUtil=new SharedPreferenceUtil(LaunchActivity.this);
		  /*if(lApplication.getLoginStatus()){
			  openActivity(MainActivity.class);
			  LaunchActivity.this.finish();
		  }*/
		 
		  
		 
		 /*
		  
		  if(!PhoneUtil.isAppInstalled(LaunchActivity.this, "com.chinatelecom.bestpayplugin")){
			  
			  if(copyApkFromAssets(this, "BestpayClient.apk", lApplication.getCacheDirPath()+"/BestpayClient.apk")){
				  intent = new Intent(Intent.ACTION_VIEW);  
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                  intent.setDataAndType(Uri.parse("file://" + lApplication.getCacheDirPath()+"/BestpayClient.apk"),  
                                 "application/vnd.android.package-archive");  
                  startActivity(intent);  
			  }

			  //InputStream abpath = getClass().getResourceAsStream("/assets/文件名");

			  
		  } */
		 
		   
	} 
	
	public boolean copyApkFromAssets(Context context, String fileName, String path) {  
        boolean copyIsFinish = false;  
        try {  
            InputStream is = context.getAssets().open(fileName);  
            File file = new File(path);  
            file.createNewFile();  
            FileOutputStream fos = new FileOutputStream(file);  
            byte[] temp = new byte[1024];  
            int i = 0;  
            while ((i = is.read(temp)) > 0) {  
                fos.write(temp, 0, i);  
            }  
            fos.close();  
            is.close();  
            copyIsFinish = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return copyIsFinish;  
    }  
	
	 
	public void initView(){
		packageInfo = CommonUtil.getSoftVersion(LaunchActivity.this);
		tvVersion.setText("All rights reserved.Ver."+packageInfo.versionName);
		tvVersion.setVisibility(View.GONE);
		tvCopyRight.setVisibility(View.GONE);
		
		tvCopyRight.postDelayed(new Runnable() {
			@Override
			public void run() {
				BaseParser<SystemConfigObject> responseParser = new SystemConfigParser();
				
				HashMap<String, String> requestDataMap=new HashMap<String, String>();  
			    RequestObject vo = new RequestObject(R.string.url_systemconfig, LaunchActivity.this, requestDataMap, responseParser);
			    getDataFromServer(vo, new DataCallback<SystemConfigObject>() {
						@Override
						public void processData(SystemConfigObject paramObject, boolean paramBoolean) { 
							  LaunchActivity.this.systemConfigObject= paramObject; 
							  sharedPreferenceUtil.setAdvertList(paramObject.getAdvertList());
							  sharedPreferenceUtil.setParList(paramObject.getParList()); 
							  sharedPreferenceUtil.setRechargeAgreeUrl(paramObject.getRechargeAgreeUrl());
							  sharedPreferenceUtil.setRegisterAgreeUrl(paramObject.getRegisterAgreeUrl());
							  if(systemConfigObject.getVersionCode()>packageInfo.versionCode+1)
								  LaunchActivity.this.systemConfigObject.setForceUpdate(true);
							  if(!updateAPK())
									  return ; 
							  LaunchActivity.this.goHomeActivity();
						}
						@Override
			    		public void processError(ConnectErrorObject responseErrorVo){ 
							
							LaunchActivity.this.DisPlay("error",responseErrorVo.getErrInfo());
							 
							 
			    		}
					},false);
			}
		},10);
		
		
		
		
	}
	public void setListener(){ 
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
		   
		}
	}
	
	
	private boolean updateAPK(){ 
		//systemConfigObject.setVersionCode(8);
        if(systemConfigObject.getVersionCode()>packageInfo.versionCode ){
        	StringBuffer txtBuffer=new StringBuffer("");
			txtBuffer.append("最新版本号："+systemConfigObject.getVersionName()+"\n");
			txtBuffer.append("优化内容:");
			txtBuffer.append(systemConfigObject.getVersionInfo());
			
			//systemConfigObject.setForceUpdate(false);
			//systemConfigObject.setApkUrl("http://115.239.171.231:8899/rising-zptc-admin/resources/app/mobileecloudapp.apk");
			
			mAlertDialog=new AlertDialog(LaunchActivity.this).builder()
					.setTitle("版本升级")
					.setCancelable(false) 
			        .setMsg(txtBuffer.toString())
			        .setNegativeButton("升级", new OnClickListener() {
						 	@Override
							public void onClick(View v) {
 
						 		new UpdateApkClickListener(LaunchActivity.this,new BaseDialog(LaunchActivity.this),systemConfigObject.getApkUrl()) ; 
						 		
							}
						});
			if(!systemConfigObject.isForceUpdate()){
				
				mAlertDialog.setPositiveButton("暂不升级", new OnClickListener() {
				 	@Override
					public void onClick(View v) {
				 		LaunchActivity.this.goHomeActivity();
				 		
					}
				});
				 
			} 
			mAlertDialog.show();
			
			/*
			 
			baseDialog=BaseDialog.getDialog(LaunchActivity.this, "版本升级",
					txtBuffer.toString() );
			baseDialog.setCancelable(false);
			baseDialog.setButton1("升级",new UpdateApkClickListener(LaunchActivity.this,baseDialog,systemConfigObject.getApkUrl()));
			
			
			if(!systemConfigObject.isForceUpdate()){
				baseDialog.setButton2("暂不升级",new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) { 
						dialog.cancel();   
					 }
				});
			} 
			baseDialog.show(); */
			
			//if(systemConfigObject.isForceUpdate())
			 return false;
		 
        }
        return true;
        
	}
	
	private void goHomeActivity() { 
		 
		if(sharedPreferenceUtil.getGuideSign()){
			if(CommonUtil.trim(sharedPreferenceUtil.getUserId()).equalsIgnoreCase("")){
				openActivity(LoginActivity.class);
			}else{
				openActivity(MainActivity.class);
			}
		         
		}
		else
			     openActivity(GuideActivity.class); 
		LaunchActivity.this.finish(); 
		 
	}
	
	@Override
	public void onDestroy(){
		if(mAlertDialog!=null){ 
			 
			mAlertDialog=null;
		}
		super.onDestroy();
	}

	
	
}