package com.ris.mobile.ecloud.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List; 

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.object.ICDealRecordObject;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;

/**
 * Created by WuXiaolong on 2015/9/14.
 */
public class ICCardBillRecycleAdapter extends RecyclerView.Adapter<ICCardBillRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<ICDealRecordObject> mDataList;

    public List<ICDealRecordObject> getDataList() {
        return mDataList;
    }

    public ICCardBillRecycleAdapter(Context context,List<ICDealRecordObject> dataList ) {
        this.mDataList = dataList;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        
        
        public ImageView iivFlag;
        public TextView itvDealNumber;
        public TextView itvParName;
        public TextView itvAccountId;
        public TextView itvDealTime;

        public ViewHolder(View itemView) {
            super(itemView);
             iivFlag = (ImageView) itemView.findViewById(R.id.iiv_Flag); 
			 itvDealNumber = (TextView) itemView.findViewById(R.id.itv_DealNumber); 
			 itvParName = (TextView) itemView.findViewById(R.id.itv_ParName); 
			 itvAccountId = (TextView) itemView.findViewById(R.id.itv_AccountId); 
			 itvDealTime = (TextView) itemView.findViewById(R.id.itv_DealTime);  
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_iccardbill, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        
        
        final ICDealRecordObject icDealRecordObject = mDataList.get(position); 
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_SUCCESS)){
			holder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_success));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_FAIL)){
			holder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_fail));
		}
		if(icDealRecordObject.getStatus().equalsIgnoreCase(Constant.STATUS_TOPAY)){
			holder.iivFlag.setImageDrawable(mContext
					.getResources().getDrawable(R.drawable.bill_iccard_topay));
			
		}
		holder.itvDealNumber.setText(CommonUtil.trim(icDealRecordObject.getTradeNo()));
		holder.itvParName.setText(CommonUtil.trim(icDealRecordObject.getParName()));
		holder.itvAccountId.setText("卡号:".concat(CommonUtil.trim(icDealRecordObject.getCardNo())));
		holder.itvDealTime.setText(CommonUtil.trim(icDealRecordObject.getRechTime()));
        
         
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}