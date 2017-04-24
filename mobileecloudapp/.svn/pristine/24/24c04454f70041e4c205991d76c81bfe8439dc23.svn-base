package com.ris.mobile.ecloud.widget;
 

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.adapter.wheel.AbstractWheelTextAdapter;
import com.ris.mobile.ecloud.object.ItemObject;
import com.ris.mobile.ecloud.widget.wheel.OnWheelChangedListener;
import com.ris.mobile.ecloud.widget.wheel.OnWheelScrollListener;
import com.ris.mobile.ecloud.widget.wheel.WheelView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

 

/**
 * 更改封面对话框
 * 
 * @author ywl
 *
 */
public class ParDialog extends Dialog implements android.view.View.OnClickListener {

	private WheelView wvProvince;
	 
	private View lyChangeAddress;
	private View lyChangeAddressChild;
	private TextView tvShareTitle;
	
	
	private TextView btnSure;
	private TextView btnCancel;

	private Context context;
	 
 
	 
	private List<ItemObject> itemList  ;
	 
	private AddressTextAdapter provinceAdapter;
 

	private ItemObject strItemObject ;
	private String title;
	 
	private OnAddressCListener onAddressCListener;

	private int maxsize = 24;
	private int minsize = 14;

	public ParDialog(Context context,String title, List<ItemObject> itemList,ItemObject aItemObject) {
		super(context, R.style.ShareDialog);
		this.context = context;
		this.title=title;
		this.itemList=itemList;
		this.strItemObject=aItemObject;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_par);
		tvShareTitle= (TextView) findViewById(R.id.tv_share_title);
		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		 
		lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
		lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

		lyChangeAddress.setOnClickListener(this);
		lyChangeAddressChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		tvShareTitle.setText(title);

		//initJsonData();
		//initDatas();
		//initProvinces();
		//strItemObject=itemList.get(2);
		provinceAdapter = new AddressTextAdapter(context, itemList, getProvinceItem(strItemObject.getItemText()), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(getProvinceItem(strItemObject.getItemText()));

		 

		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				 
				strItemObject=itemList.get(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
				 
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		 

		 
	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		 List<ItemObject> list;

		protected AddressTextAdapter(Context context,  List<ItemObject> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_par, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index).getItemText()  ;
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSure) {
			if (onAddressCListener != null) {
				onAddressCListener.onClick(strItemObject);
			}
		} else if (v == btnCancel) {

		} else if (v == lyChangeAddressChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnAddressCListener {
		public void onClick(ItemObject itemObject);
	}

	 
	 

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		ItemObject itemObject=new ItemObject();
		itemObject.setItemText("10元");
		itemObject.setItemValue("001");
		itemList.add(itemObject);
		
		itemObject=new ItemObject();
		itemObject.setItemText("20元");
		itemObject.setItemValue("002"); 
		itemList.add(itemObject);
		
	 
		itemObject=new ItemObject();
		itemObject.setItemText("30元");
		itemObject.setItemValue("003"); 
		itemList.add(itemObject);
		
		 
		itemObject=new ItemObject();
		itemObject.setItemText("50元");
		itemObject.setItemValue("005"); 
		itemList.add(itemObject);
		
		
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	 

	 
 

	/**
	 * 返回省会索引，没有就返回默认“四川”
	 * 
	 * @param province
	 * @return
	 */
	public int getProvinceItem(String province) {
		
		
		 
		int provinceIndex = 0;
		boolean noprovince = true; 
		for(ItemObject mItemObject:itemList){
			if(mItemObject.getItemText().equalsIgnoreCase(province))
				return provinceIndex;
			provinceIndex++;
			
		}
		
		 
		 
		return provinceIndex;
	}

	 
	 

}