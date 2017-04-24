package com.ris.mobile.ecloud.activity; 
import java.util.ArrayList;
import java.util.Random; 

import android.content.ContentValues;
import android.content.Context; 
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import roboguice.inject.InjectView;

import com.ris.mobile.ecloud.R;   
import com.ris.mobile.ecloud.adapter.ICCardBillAdapter; 
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.LApplication;
 
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory; 
import com.ris.mobile.ecloud.object.ConnectErrorObject;
  
import com.ris.mobile.ecloud.object.ICDealRecordObject;
import com.ris.mobile.ecloud.object.RequestObject; 
import com.ris.mobile.ecloud.parser.BaseParser;  
import com.ris.mobile.ecloud.parser.ICDealRecordListParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil; 
import com.ris.mobile.ecloud.widget.PullToRefreshLayout;
import com.ris.mobile.ecloud.widget.PullToRefreshLayout.OnRefreshListener; 
import com.ris.mobile.ecloud.widget.pullableview.PullableListView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;  
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;  
import android.text.InputType;
import android.view.LayoutInflater;  
import android.view.MotionEvent;
import android.view.View;  
import android.view.View.OnTouchListener;
import android.view.ViewGroup;  
import android.view.View.OnClickListener; 
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView; 
import android.widget.Toast;
      
public class ICCardBillActivity  extends BaseActivity implements      OnRefreshListener ,OnClickListener{  
 
	public static LApplication lApplication=LApplication.getInstance();
	
	@InjectView (R.id.tv_Back) 
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	
	@InjectView (R.id.refresh_view)
	private PullToRefreshLayout refreshView;
	
	@InjectView (R.id.ll_NoRecord)
	private LinearLayout llNoRecord;
	
	@InjectView (R.id.lv_ICCardBill)
	private PullableListView lvICCardBill;
	
	private Context mContext; 
	
	private List<ICDealRecordObject> data;
	private ICCardBillAdapter mAdapter; 
	  
	private int pageIndex=1;
	private int pageSize=15;
	private SharedPreferenceUtil sharedPreferenceUtil; 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iccardbill);
		initData(getIntent()); 
		initView();
		setListener();
	 
	}
	public    void initData(Intent intent){ 
		mContext=ICCardBillActivity.this;
		sharedPreferenceUtil=new SharedPreferenceUtil(mContext);
	}
	 
	
	
	public void initView(){
	  	  
		tvBack.setVisibility(View.VISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_iccardbill);
    	tvRight.setVisibility(View.INVISIBLE);
    	llNoRecord.setVisibility(View.GONE);
    	 
    	 
    	data =  new ArrayList<ICDealRecordObject>();
    	mAdapter = new ICCardBillAdapter(mContext,data);   
        lvICCardBill.setAdapter(mAdapter); 
        refreshView.autoRefresh();
        /*
    	BaseParser<List<ICDealRecordObject>> responseParser = new ICDealRecordListParser();
    	HashMap<String, String> requestDataMap=new HashMap<String, String>(); 
		requestDataMap.put("pageNo",  new Integer(pageIndex).toString() );
		requestDataMap.put("pageSize", new Integer(pageSize).toString()  ); 
		requestDataMap.put("userId",  sharedPreferenceUtil.getUserId() );
		RequestObject vo = new RequestObject(R.string.url_icdealrecordlist, mContext, requestDataMap, responseParser); 
		getDataFromServer(vo, new  DataCallback<List<ICDealRecordObject>>() {
			@Override
			public void processData(List<ICDealRecordObject> paramObject, boolean paramBoolean) { 
				 
		    	if(paramObject!=null)
		    		data.addAll(paramObject); 
		    	if(data.size()>0)
	    	        llNoRecord.setVisibility(View.GONE);
		          
				return;
			}
			@Override
    		public void processError(ConnectErrorObject responseErrorVo){ 
				 DisPlay("error",responseErrorVo.getErrInfo());
				 
    		}
		},false); */
    	 
      }
	
	 public void setListener(){
	    	tvBack.setOnClickListener(this); 
	    	refreshView.setOnRefreshListener(this);
	    	 
	    	
	    }
	 @Override
	 public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			  case R.id.tv_Back:
				 this.finish();
				 break; 
		    }
			
			
		} 
	 
	 @Override
		public void onStart() {
			super.onStart();
		
		}
	 public boolean onLoadData( final PullToRefreshLayout pullToRefreshLayout, final boolean isRefresh) {
		 
			
			if (isRefresh) {  
			    pageIndex=1;
			    data.clear();
	        } else {   
	        	pageIndex++;    
	        }  
		    pageIndex=pageIndex<1?1:pageIndex;
		    
	        BaseParser<List<ICDealRecordObject>> responseParser = new ICDealRecordListParser();
	        
			HashMap<String, String> requestDataMap=new HashMap<String, String>();
			 
			requestDataMap.put("pageNo",  new Integer(pageIndex).toString() );
			requestDataMap.put("pageSize", new Integer(pageSize).toString()  );  
			requestDataMap.put("userId",  sharedPreferenceUtil.getUserId() );
			
				
			RequestObject vo = new RequestObject(R.string.url_icdealrecordlist, mContext, requestDataMap, responseParser); 
		    getDataFromServer(vo, new  DataCallback<List<ICDealRecordObject>>() {
					@Override
					public void processData(List<ICDealRecordObject> paramObject, boolean paramBoolean) { 
						 
				    	if(paramObject!=null&&paramObject.size()>0)
				    		data.addAll(paramObject);
				    	else
				    		pageIndex--; 
				    	if(data.size()>0)
			    	        llNoRecord.setVisibility(View.GONE);
				    	else
				    		llNoRecord.setVisibility(View.VISIBLE);
				    	mAdapter.notifyDataSetChanged(); 
				    	if (isRefresh)
				    		pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
				    	else
				    		pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						return  ;
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){ 
						if (isRefresh)
				    		pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
				    	else
				    		pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						 DisPlay("error",responseErrorVo.getErrInfo());
						 
		    		}
			},false); 
		     
	    	 
	    	 
	        return false;   
		}
	 
	 @Override
		public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
		{ 
			onLoadData(pullToRefreshLayout,true); 
		}
	 
	 @Override
		public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
		{
			 
			onLoadData(pullToRefreshLayout,false); 
		}	
	 
	 
		@Override
		public void onDestroy(){
			 
			super.onDestroy();
		}
		
}
	