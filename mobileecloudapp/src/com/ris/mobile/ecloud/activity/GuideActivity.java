package com.ris.mobile.ecloud.activity; 
import java.util.ArrayList;

import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.adapter.GuidePageAdapter;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class GuideActivity   extends BaseActivity  implements  OnClickListener ,OnTouchListener,OnPageChangeListener {

 
	
	private   ArrayList<View> viewList ;
	private   ImageView[] arrPoints;
	private   ViewPager mViewPager;	
	private static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	private int currIndex = 0;
	
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_guide);
		initData(getIntent());
		 
		findViewById();
		initView();
		setListener();
		
	}
	
	
	public  void initData(Intent intent){
		sharedPreferenceUtil=new SharedPreferenceUtil(GuideActivity.this);
	}
	
	public void findViewById(){
		mViewPager = (ViewPager)findViewById(R.id.whatsnew_viewpager);
		arrPoints=new ImageView[3];
		arrPoints[0]=(ImageView)findViewById(R.id.page0);
		arrPoints[1]=(ImageView)findViewById(R.id.page1);
		arrPoints[2]=(ImageView)findViewById(R.id.page2); 
		
		LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.fragment_guide1, null);
        View view2 = mLi.inflate(R.layout.fragment_guide2, null);
        View view3 = mLi.inflate(R.layout.fragment_guide3, null); 
        
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
         view3.setTag(1); 
		
	}
	
	public void initView(){
        PagerAdapter mPagerAdapter = new GuidePageAdapter(viewList) ;
		
		mViewPager.setAdapter(mPagerAdapter);
		
	}
	public void setListener(){
		
		viewList.get(2).setOnClickListener(this);
		mViewPager.setOnTouchListener(this);
		mViewPager.setOnPageChangeListener(this); 
	}
	
	@Override
	public void onClick(View v) {
		int tag = (Integer) v.getTag();   
		switch (tag){    
        case 1:    
        	goHome();
        break;  
       }   
	}
    
	private void goHome(){
		sharedPreferenceUtil.setGuideSign(true);
		/*Bundle myBundel=new Bundle(); 
		myBundel.putString("wapUrl","http://www.baidu.com"); 
		myBundel.putString("wapTitle","标题"); 
		openActivity(WebViewActivity.class,myBundel);*/
		openActivity(LoginActivity.class);
		this.finish();
	}
	 


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE: 
			if( currIndex== viewList.size()-1){
				 
				goHome();
			}
			break;
		default:
			break;
		}
		return false; 
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int arg0) {
		 
		// TODO Auto-generated method stub
		arrPoints[0].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
		arrPoints[1].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
		arrPoints[2].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
		 
		switch (arg0) {
		case 0:				
			arrPoints[0].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
			 
			break;
		case 1:
			arrPoints[1].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
			break;
		case 2:
			arrPoints[2].setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
			break; 
		 
		}
		currIndex = arg0;
	}
}
