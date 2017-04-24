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
 

import com.ris.mobile.ecloud.object.ICDealRecordObject; 
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
public class ICCardBillAdapter extends BaseAdapter
{
	private List<ICDealRecordObject> mDataList;
	private Context mContext;  
	  
	public ICCardBillAdapter(Context context,List<ICDealRecordObject> dataList  )
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
			convertView = View.inflate(mContext, R.layout.item_iccardbill,
					null);
			mHolder = new ViewHolder();
			mHolder.iivFlag = (ImageView) convertView.findViewById(R.id.iiv_Flag); 
			mHolder.itvDealNumber = (TextView) convertView.findViewById(R.id.itv_DealNumber); 
			mHolder.itvParName = (TextView) convertView.findViewById(R.id.itv_ParName); 
			mHolder.itvAccountId = (TextView) convertView.findViewById(R.id.itv_AccountId); 
			mHolder.itvDealTime = (TextView) convertView.findViewById(R.id.itv_DealTime); 
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		final ICDealRecordObject icDealRecordObject = mDataList.get(position); 
		icDealRecordObject.setCardType(CommonUtil.trim(icDealRecordObject.getCardType()));
		
		
		
		
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_SUCCESS)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_CARD)){
			
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_success));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_SUCCESS)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_PHONE)){
			
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_icphone_success));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_FAIL)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_CARD)){
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_fail));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_FAIL)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_PHONE)){
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_icphone_fail));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_TOPAY)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_CARD)){
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_fail));
			
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_TOPAY)
				&& icDealRecordObject.getCardType().equalsIgnoreCase(Constant.IC_TYPE_PHONE)){
			mHolder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_icphone_fail));
			
		}
		mHolder.itvDealNumber.setText(CommonUtil.trim(icDealRecordObject.getTradeNo()));
		mHolder.itvParName.setText(CommonUtil.trim(icDealRecordObject.getParName()));
		mHolder.itvAccountId.setText("卡号:".concat(CommonUtil.trim(icDealRecordObject.getCardNo())));
		mHolder.itvDealTime.setText(CommonUtil.trim(icDealRecordObject.getRechTime()));
	 
		return convertView;
	}

	static class ViewHolder
	{ 
		private ImageView iivFlag;
		private TextView itvDealNumber;
		private TextView itvParName;
		private TextView itvAccountId;
		private TextView itvDealTime;
		
	}  
 }