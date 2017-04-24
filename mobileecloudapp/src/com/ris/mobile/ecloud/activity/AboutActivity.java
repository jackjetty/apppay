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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent; 
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
public class AboutActivity  extends BaseActivity  implements  OnClickListener {
	
	
	//private static final String TAG = AboutActivity.class.getSimpleName(); 
	
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	 
	@InjectView (R.id.tv_AboutVersion)
	private TextView tvAboutVersion;
	public static LApplication lApplication=LApplication.getInstance();
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initData(getIntent());
	 
		initView();
		setListener();
		 
	}
	 
	public    void initData(Intent intent){ 
    	 
	}
	public void initView(){
	  	  
			tvBack.setVisibility(View.VISIBLE);
	    	tvTitle.setVisibility(View.VISIBLE);
	    	tvTitle.setText(R.string.title_about);
	    	tvRight.setVisibility(View.INVISIBLE);
	    	
	    	 
	    	
	    	tvAboutVersion.setText(getString(R.string.app_name).concat(" V").concat(CommonUtil.getSoftVersion(AboutActivity.this).versionName)); 
	    	
	    	 
	}
	public void setListener(){
		tvBack.setOnClickListener(this); 
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
				  AboutActivity.this.finish();
				 break;
	         }
	 }
	 
}