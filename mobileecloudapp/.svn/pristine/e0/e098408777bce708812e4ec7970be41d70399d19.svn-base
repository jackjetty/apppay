package com.ris.mobile.ecloud.adapter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.ris.mobile.ecloud.R; 
 

import com.ris.mobile.ecloud.engine.SyncImageLoader;
import com.ris.mobile.ecloud.engine.SyncImageLoader.OnImageLoadListener;
import com.ris.mobile.ecloud.object.ApplyItemObject; 
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.util.CommonUtil;

import android.content.Context; 
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View; 
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter; 
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView; 
public class ApplyAdapter extends BaseAdapter implements OnImageLoadListener
{
	private List<ApplyItemObject> mDataList;
	private Context mContext;  
	private SyncImageLoader syncImageLoader;
    private LayoutInflater mInflater;//打气筒
    private ListView mListView;
	  
	public ApplyAdapter(Context context,ListView listView,List<ApplyItemObject> dataList  )
	{
		this.mContext = context;
		this.mDataList = dataList; 
		this.mListView =listView;
		syncImageLoader = new SyncImageLoader();
		 
		
	}

	@Override
	public int getCount()
	{
		return mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{ 
		
		final ViewHolder mHolder;
		if (convertView == null)
		{
			convertView = View.inflate(mContext, R.layout.item_apply,
					null); 
			mHolder = new ViewHolder();
			mHolder.iivApply = (ImageView) convertView.findViewById(R.id.iiv_Apply); 
			mHolder.itvApplyTitle = (TextView) convertView.findViewById(R.id.itv_ApplyTitle); 
			mHolder.itvApplySurplus = (TextView) convertView.findViewById(R.id.itv_ApplySurplus); 
			mHolder.itvApplyPeriod = (TextView) convertView.findViewById(R.id.itv_ApplyPeriod);  
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		 
		final ApplyItemObject applyItemObject = mDataList.get(position); 
		HashMap<String, String> requestDataMap;
		RequestObject reqObject;
		requestDataMap=new HashMap<String, String>(); 
		reqObject = new RequestObject(applyItemObject.getApplyUrl(), this.mContext, requestDataMap, null);
		reqObject.setMajorKey(applyItemObject.getApplyId());
		
	    mHolder.iivApply.setImageResource(R.drawable.load_default);
		mHolder.iivApply.setBackgroundResource(R.drawable.home_backgroud); 
		mHolder.itvApplyTitle.setText(CommonUtil.trim(applyItemObject.getApplyTitle()));
		mHolder.itvApplySurplus.setText("名额:".concat(CommonUtil.trim(applyItemObject.getApplySurplus())));
		mHolder.itvApplyPeriod.setText( CommonUtil.trim(applyItemObject.getApplyPeriod()) );
	    
		 
          
		mHolder.iivApply.setTag(applyItemObject.getApplyId()+position); 
		syncImageLoader.loadImage(position,reqObject,this);  
	 
		return convertView;
	}
	
	@Override
	public void onImageLoad(Integer t, Drawable drawable) {
	 
		ApplyItemObject applyItemObject = mDataList.get(t);  
		ImageView imageViewByTag = (ImageView) mListView.findViewWithTag(applyItemObject.getApplyId()+t); 
        if (imageViewByTag != null) {  
            imageViewByTag.setImageDrawable(drawable); 
            //notifyDataSetChanged();
        }  
		
	}

	@Override
	public void onError(Integer t) {

	}  
	
	
	public void loadImage(){  
	    int start = mListView.getFirstVisiblePosition();  
	    int end =mListView.getLastVisiblePosition();  
	    if(end >= getCount()){  
	        end = getCount() -1;  
	    }  
	    syncImageLoader.setLoadLimit(start, end);  
	    syncImageLoader.unlock();  
	}  
	  
	public AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {  
	      
	    @Override  
	    public void onScrollStateChanged(AbsListView view, int scrollState) {  
	        switch (scrollState) {  
	            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:    
	                syncImageLoader.lock();  
	                break;  
	            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    
	                loadImage();  
	                //loadImage();  
	                break;  
	            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  
	                syncImageLoader.lock();  
	                break;  
	  
	            default:  
	                break;  
	        }  
	          
	    }  
	      
	    @Override  
	    public void onScroll(AbsListView view, int firstVisibleItem,  
	            int visibleItemCount, int totalItemCount) {  
	        // TODO Auto-generated method stub  
	          
	    } 
	};
	
	static class ViewHolder
	{ 
		private ImageView iivApply;
		private TextView itvApplyTitle;
		private TextView itvApplySurplus;
		private TextView itvApplyPeriod; 
		
	}  
 }