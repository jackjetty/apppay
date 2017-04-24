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
import com.ris.mobile.ecloud.object.ApplyIntroduceObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
 
import com.ris.mobile.ecloud.object.ItemObject;
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.parser.ApplyIntroduceParser;
import com.ris.mobile.ecloud.parser.BaseParser; 
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.ParDialog;
import com.ris.mobile.ecloud.widget.ParDialog.OnAddressCListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
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

public class ApplyIntroduceActivity extends BaseActivity implements OnClickListener  {

	 
	
	@InjectView (R.id.tv_Back) 
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title) 
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right) 
	private TextView tvRight;
	
	@InjectView (R.id.tv_Introduce) 
	private TextView tvIntroduce;
	
	@InjectView (R.id.btn_Next) 
	private Button btnNext;
	
	@InjectView (R.id.tv_Prompt) 
	private TextView tvPrompt; 
	
	private String applyId;
	private String applyTitle;
	private String applyType;
	
	//private WebView wvIntroduce;
	
	
	 
	 
	 
	public static LApplication lApplication = LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applyintroduce);
		initData(getIntent());
		 
		initView();
		setListener();

	}

	 

	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ApplyIntroduceActivity.this); 
		applyId = intent.getStringExtra("applyId"); 
		applyTitle = intent.getStringExtra("applyTitle"); 
		applyId=CommonUtil.trim(applyId); 
		applyTitle=CommonUtil.trim(applyTitle); 
		 

	}

	public void initView() {

		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setVisibility(View.VISIBLE);
		tvTitle.setText(applyTitle);
		tvRight.setVisibility(View.INVISIBLE); 
		 
		
		//tvIntroduce.setText("");
		tvPrompt.setText("");
		btnNext.setVisibility(View.VISIBLE); 
		 
		
		BaseParser<ApplyIntroduceObject> applyIntroduceParser = new ApplyIntroduceParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userType", new Integer(sharedPreferenceUtil.getUserType()).toString() );
		requestDataMap.put("applyId", applyId);
		
		RequestObject vo = new RequestObject(R.string.url_applyintroduce,
				ApplyIntroduceActivity.this, requestDataMap, applyIntroduceParser);
		/*
		 * theString = theString.Replace(">", "&gt;");
            theString = theString.Replace("<", "&lt;");
            theString = theString.Replace(" ", "&nbsp;");
            theString = theString.Replace("\"", "&quot;");
            theString = theString.Replace("\'", "&#39;");
            theString = theString.Replace("\\", "\\\\");//对斜线的转义
            theString = theString.Replace("\n", "\\n");
            theString = theString.Replace("\r", "\\r");
		 */
		getDataFromServer(vo, new DataCallback<ApplyIntroduceObject>() {
			@Override
			public void processData(ApplyIntroduceObject paramObject,
					boolean paramBoolean) {
				
				applyType=CommonUtil.trim(paramObject.getApplyType());
				String introduce=paramObject.getIntroduce(); 
				introduce=introduce.replace("&lt;", "<");
				introduce=introduce.replace("&gt;", ">");
	 
				 
				 //introduce="<table width=300 height=300 border=10><tr><td valign=top>12 </td></tr><tr><td  >23 </td></tr></table>";
				 tvIntroduce.setText(Html.fromHtml(introduce));
				//wvIntroduce.getSettings().setDefaultTextEncodingName("UTF-8"); 

				//wvIntroduce.loadData(introduce, "text/html; charset=UTF-8", null);
				tvPrompt.setText(paramObject.getPrompt()) ;
				 
				btnNext.setEnabled(paramObject.isApplyAble());
				 
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
		 

	}

	public void setListener() {
		tvBack.setOnClickListener(this);
		btnNext.setOnClickListener(this); 
		tvRight.setOnClickListener(this);
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
		Bundle myBundel; 
		switch (v.getId()) {
		case R.id.tv_Back:
			this.finish();
			break;
		case R.id.btn_Next:
			myBundel=new Bundle(); 
		    myBundel.putString("applyId",applyId);  
		    myBundel.putString("applyType",applyType);  
            if(applyType.equalsIgnoreCase(Constant.APPLY_JUSTPAY)) 
			    ApplyIntroduceActivity.this.openActivity(ApplyPayActivity.class,myBundel); 
            if(applyType.equalsIgnoreCase(Constant.APPLY_JUSTSIGN))
            	ApplyIntroduceActivity.this.openActivity(ApplySignActivity.class,myBundel); 
            if(applyType.equalsIgnoreCase(Constant.APPLY_SIGNPAY))
            	ApplyIntroduceActivity.this.openActivity(ApplySPActivity.class,myBundel); 
			break;
		

		}
	}

	 
}