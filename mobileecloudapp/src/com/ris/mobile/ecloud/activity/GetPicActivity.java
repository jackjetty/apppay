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
import com.ris.mobile.ecloud.adapter.ImageBucketAdapter;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject; 
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.AlbumHelper;
import com.ris.mobile.ecloud.util.CommonUtil; 
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.vo.ImageBucket;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent; 
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import android.widget.AdapterView.OnItemClickListener;
public class GetPicActivity  extends BaseActivity  implements  OnClickListener {
	
	
	
	
 
	public static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	
	List<ImageBucket> dataList;
	
	ImageBucketAdapter adapter;// 自定义的适配器
	AlbumHelper helper;
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	private static final int IMAGE_CHOOSE = 1233;
	
	public static Bitmap bimap;
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	 
	
	@InjectView (R.id.gridview)
	private GridView gridView;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);
		initData(getIntent());
		 
		initView();
		setListener();
		 
	}
	 
    public    void initData(Intent intent){ 
    	
    	sharedPreferenceUtil=new SharedPreferenceUtil(GetPicActivity.this);
    	helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
    	dataList = helper.getImagesBucketList(false);	
		bimap=BitmapFactory.decodeResource(
				getResources(),
				R.drawable.icon_addpic_unfocused);
	}
    public void initView(){
  	  
		tvBack.setVisibility(View.VISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_feedback);
    	tvRight.setVisibility(View.INVISIBLE);
    	tvTitle.setText("相册");
    	adapter = new ImageBucketAdapter(GetPicActivity.this, dataList);
		gridView.setAdapter(adapter);

    	 
	}
    public void setListener(){
		tvBack.setOnClickListener(this); 
		tvRight.setOnClickListener(this);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * 根据position参数，可以获得跟GridView的子View相绑定的实体类，然后根据它的isSelected状态，
				 * 来判断是否显示选中效果。 至于选中效果的规则，下面适配器的代码中会有说明
				 */
				// if(dataList.get(position).isSelected()){
				// dataList.get(position).setSelected(false);
				// }else{
				// dataList.get(position).setSelected(true);
				// }
				/**
				 * 通知适配器，绑定的数据发生了改变，应当刷新视图
				 */
				// adapter.notifyDataSetChanged();
				Intent intent = new Intent(GetPicActivity.this,
						ImageGridActivity.class);
				intent.putExtra(GetPicActivity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);				
				startActivityForResult(intent,IMAGE_CHOOSE);
				//finish();
			}

		});
		
		
	}
    
    protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
		if (resultCode != RESULT_OK){
			return;
		}
		switch (requestCode) {
			case IMAGE_CHOOSE:
				
				String path = mIntent.getStringExtra("IMAGEPATH");
				Intent intent=new Intent();  
                intent.putExtra("IMAGEPATH", path);   
				setResult(RESULT_OK, intent);  
                 GetPicActivity.this.finish();
				
				break;
		}
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
			  GetPicActivity.this.finish();
			 break;
		  
		}
	}
    
    
	
}




