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
 

import com.ris.mobile.ecloud.object.EmployObject;

import android.content.Context; 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View; 
import android.view.ViewGroup;
import android.widget.BaseAdapter; 
import android.widget.TextView; 
public class EmployAdapter extends BaseAdapter
{
	private List<EmployObject> mDataList;
	private Context mContext;  
	  
	public EmployAdapter(Context context,List<EmployObject> dataList  )
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
			convertView = View.inflate(mContext, R.layout.item_employ,
					null);
			mHolder = new ViewHolder();
			mHolder.itvTitle = (TextView) convertView.findViewById(R.id.itv_Title); 
			mHolder.itvIssueDate = (TextView) convertView.findViewById(R.id.itv_IssueDate); 
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}

		final EmployObject employObject = mDataList.get(position); 
		mHolder.itvTitle.setText(employObject.getTitle());
		mHolder.itvIssueDate.setText(employObject.getIssueTime()); 
		return convertView;
	}

	static class ViewHolder
	{ 
		private TextView itvTitle;
		private TextView itvIssueDate;
	 
		
	}  
 }