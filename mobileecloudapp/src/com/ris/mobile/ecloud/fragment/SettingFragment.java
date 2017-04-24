package com.ris.mobile.ecloud.fragment;
 


import java.util.HashMap;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import com.ris.mobile.ecloud.BaseDialog;
import com.ris.mobile.ecloud.BaseFragment;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.R.id;
import com.ris.mobile.ecloud.activity.AboutActivity;
import com.ris.mobile.ecloud.activity.FAQActivity;
import com.ris.mobile.ecloud.activity.MainActivity;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.listener.UpdateApkClickListener;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.object.SystemConfigObject;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.parser.SystemConfigParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.AlertDialog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView; 
import android.widget.Toast;
      
public class SettingFragment  extends BaseFragment implements  OnClickListener{
 
	private View fragmentView;
	 
	private MainActivity mainActivity;
	private LApplication lApplication; 
	private Context mContext;
	private PackageInfo packageInfo  ;
	private SharedPreferenceUtil sharedPreferenceUtil;
	 
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle; 
	
	@InjectView (R.id.rl_Faq)
	private RelativeLayout rlFaq;
	
	@InjectView (R.id.rl_Update)
	private RelativeLayout rlUpdate;
	
	@InjectView (R.id.rl_About)
	private RelativeLayout rlAbout;
	
	@InjectView (R.id.tv_Version)
	private TextView tvVersion;
	
	private SystemConfigObject systemConfigObject; 
	public AlertDialog mAlertDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setRetainInstance(true); 
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {        
     
    	fragmentView= inflater.inflate(R.layout.fragment_setting, container, false);
    	mContext=this.getActivity();
    	mainActivity=(MainActivity)this.getActivity();
    	lApplication=LApplication.getInstance();
    	sharedPreferenceUtil=new SharedPreferenceUtil(mContext);
    	
    	return   fragmentView;      
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(); 
    	initView();
    	setListener();
    }
    private void initData(){
    	packageInfo = CommonUtil.getSoftVersion(mContext);
    }
     
    private void initView(){
    	tvBack.setVisibility(View.INVISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_setting); 
    	tvVersion.setText("V".concat(packageInfo.versionName));
    	
    	
    	
    }
    private void setListener(){
    	rlFaq.setOnClickListener(this);
    	rlUpdate.setOnClickListener(this); 
    	rlAbout.setOnClickListener(this); 
    }
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		  case R.id.tv_Back:
			 //this.finish();
			 break; 
		  case R.id.rl_About:	  
			 mainActivity.openActivity(AboutActivity.class );
			 break; 
		  case R.id.rl_Faq:	  
			  mainActivity.openActivity(FAQActivity.class );
			  break;
			  
		  case R.id.rl_Update:
			   BaseParser<SystemConfigObject> responseParser = new SystemConfigParser();
			    HashMap<String, String> requestDataMap=new HashMap<String, String>();  
			    RequestObject vo = new RequestObject(R.string.url_systemconfig, mContext, requestDataMap, responseParser); 
			    mainActivity.getDataFromServer(vo, new MainActivity.DataCallback<SystemConfigObject>() {
						@Override
						public void processData(SystemConfigObject paramObject, boolean paramBoolean) { 
							SettingFragment.this.systemConfigObject= paramObject; 
							if(systemConfigObject.getVersionCode()>packageInfo.versionCode+1)
								SettingFragment.this.systemConfigObject.setForceUpdate(true);
							if(!updateAPK())
									  return ;
							else
								mainActivity.DisPlay("success","已经最新版了！！"); 
						}
						@Override
			    		public void processError(ConnectErrorObject responseErrorVo){ 
							mainActivity.DisPlay("error",responseErrorVo.getErrInfo());
							 
							 
			    		}
					});
			    break; 
		 
	    }
		
		
	} 
	
	private boolean updateAPK(){ 
		 
        if(systemConfigObject.getVersionCode()>packageInfo.versionCode ){
        	StringBuffer txtBuffer=new StringBuffer("");
			txtBuffer.append("最新版本号："+systemConfigObject.getVersionName()+"\n");
			txtBuffer.append("优化内容:");
			txtBuffer.append(systemConfigObject.getVersionInfo());
			
			
			mAlertDialog=new AlertDialog(mainActivity).builder()
					.setTitle("版本升级")
					.setCancelable(false) 
			        .setMsg(txtBuffer.toString())
			        .setNegativeButton("升级", new OnClickListener() {
						 	@Override
							public void onClick(View v) {
 
						 		new UpdateApkClickListener(mainActivity,new BaseDialog(mainActivity),systemConfigObject.getApkUrl()) ; 
						 		
							}
						});
			
			if(!systemConfigObject.isForceUpdate()){
				
				mAlertDialog.setPositiveButton("暂不升级", new OnClickListener() {
				 	@Override
					public void onClick(View v) {
				 		 
				 		
					}
				});
				 
			} 
			mAlertDialog.show();
			
			 
			 return false;
		 
        }
        return true;
        
	}
	 
	@Override
	public void onStart() {
		super.onStart();
	
	}
}  