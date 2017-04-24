package com.ris.mobile.ecloud.adapter;
 

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;
import java.util.Vector;  
public class GuidePageAdapter extends PagerAdapter {
	
	private List <View> viewList;
	public GuidePageAdapter(List  <View> viewList){
		this.viewList=viewList;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		 
		((ViewPager)container).removeView((View)viewList.get(position));
	}
	
	
	
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView((View)viewList.get(position));
		return viewList.get(position);
	}
	
}