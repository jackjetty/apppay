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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.ris.mobile.ecloud.R;  
import com.ris.mobile.ecloud.object.ApplyDealRecordObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;

import android.content.Context; 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View; 
import android.view.ViewGroup;
import android.widget.BaseAdapter; 
import android.widget.ImageView;
import android.widget.TextView; 
public class ApplyBillAdapter extends BaseAdapter
{
	private List<ApplyDealRecordObject> mDataList;
	private Context mContext;  
	  
	public ApplyBillAdapter(Context context,List<ApplyDealRecordObject> dataList  )
	{
		this.mContext = context;
		this.mDataList = dataList; 
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
			convertView = View.inflate(mContext, R.layout.item_applybill,
					null);
			mHolder = new ViewHolder();
			mHolder.itvApplyTitle = (TextView) convertView.findViewById(R.id.itv_ApplyTitle); 
			mHolder.itvUserName = (TextView) convertView.findViewById(R.id.itv_UserName); 
			mHolder.itvDealTime = (TextView) convertView.findViewById(R.id.itv_DealTime); 
			mHolder.itvFlag = (TextView) convertView.findViewById(R.id.itv_Flag);  
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		final ApplyDealRecordObject applyDealRecordObject = mDataList.get(position); 
		
		if(applyDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_SUCCESS)){
			mHolder.itvFlag.setTextColor(mContext
					.getResources().getColor(R.color.gray));
			mHolder.itvFlag.setText("成功");
		}
		if(applyDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_FAIL)){
			mHolder.itvFlag.setTextColor(mContext
					.getResources().getColor(R.color.red));
			mHolder.itvFlag.setText("失败");
		}
		
		if(applyDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_TOPAY)){
			mHolder.itvFlag.setTextColor(mContext
					.getResources().getColor(R.color.red));
			mHolder.itvFlag.setText("未支付"); 
		}
		if(applyDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_CANCEL)){
			mHolder.itvFlag.setTextColor(mContext
					.getResources().getColor(R.color.red));
			mHolder.itvFlag.setText("取消报名"); 
		}
		
		
		mHolder.itvUserName.setText("姓名:".concat(CommonUtil.trim(applyDealRecordObject.getUserName())));
		mHolder.itvApplyTitle.setText(CommonUtil.trim(applyDealRecordObject.getApplyTitle()));
		 
		mHolder.itvDealTime.setText(CommonUtil.trim(applyDealRecordObject.getDealTime()));
	 
		return convertView;
	}

	static class ViewHolder
	{ 
		private TextView itvApplyTitle;
		private TextView itvUserName;
		private TextView itvDealTime;
		private TextView itvFlag; 
		
	}  
 }