package com.ris.mobile.ecloud.widget;  

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.PhoneUtil;
import com.ris.mobile.ecloud.widget.ApplyPayPromptDialog.Builder;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager; 
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class  ApplySignPromptDialog extends Dialog {

	private Context context;

	public static final int VIEW_STYLE_NORMAL = 0x00000001;
	public static final int VIEW_STYLE_TITLEBAR = 0x00000002;
	public static final int VIEW_STYLE_TITLEBAR_SKYBLUE = 0x00000003;
	public static final int BUTTON_COUNT_ZERO = 0x00000000;
	public static final int BUTTON_COUNT_ONE = 0x00000001;
	public static final int BUTTON_COUNT_TWO = 0x00000002; 

	public static final int BUTTON_1 = 0x00000001;
	public static final int BUTTON_2 = 0x00000002; 
	

	protected ApplySignPromptDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	protected ApplySignPromptDialog(Context context) {
		this(context, R.style.PromptDialogStyle);
	}

	protected ApplySignPromptDialog(Context context, boolean cancelableOnTouchOutside) {
		this(context);
		this.setCanceledOnTouchOutside(cancelableOnTouchOutside);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = this.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		int marginBorder = PhoneUtil.dip2px(context, 15);
		params.width = PhoneUtil.getScreenWidth(context) - marginBorder * 2;
		window.setAttributes(params);
		
		
		
		
		
	}

	@SuppressLint({ "NewApi", "InflateParams" })
	public static class Builder {

		private ApplySignPromptDialog dialog;
		private Context context;

		private CharSequence title; 
		private CharSequence button1Text; 
		private CharSequence button2Text;
		private int button1TextColor; 
		private int button2TextColor;
		private int titleColor; 
		private float button1Size; 
		private float button2Size;
		private float titleSize; 
		private ColorStateList titleColorStateList; 
		private ColorStateList button1ColorStateList; 
		private ColorStateList button2ColorStateList;
		
		private CharSequence applyTitle;
		private CharSequence skuLabel;
		private CharSequence skuName;
		private CharSequence applyAmount;
		
		private CharSequence userId;
		private CharSequence userName;
		private CharSequence idNum;
		private CharSequence phoneNumber;
		private CharSequence labelOther1;
		private CharSequence valueOther1;
		private CharSequence labelOther2;
		private CharSequence valueOther2;
		private CharSequence remark;
		private int titlebarGravity;

		private Drawable icon;
		private boolean cancelable = true;
		private boolean canceledOnTouchOutside;
		private View view;
		private ApplySignPromptDialog.OnClickListener button1Listener; 
		private ApplySignPromptDialog.OnClickListener button2Listener;
		private ApplySignPromptDialog.OnLinearClickListener linearListener;
	    
		 

		public Builder(Context context, int theme) {
			dialog = new ApplySignPromptDialog(context, theme);
			this.context = context;
			initData();
		}

		public Builder(Context context) {
			dialog = new ApplySignPromptDialog(context);
			this.context = context;
			initData();
		}

		private void initData() {
			this.button1TextColor = Color.parseColor("#808080"); 
			this.button2TextColor = Color.parseColor("#808080"); 
			this.titleColor = Color.BLACK;

			this.button1Size = 16; 
			this.button2Size = 16; 
			this.titleSize = 18;

			this.titlebarGravity = Gravity.CENTER;
		}

		public Context getContext() {
			return context;
		}

		public Builder setTitleBarGravity(int titlebarGravity) {
			this.titlebarGravity = titlebarGravity;
			return this;
		}

		public Builder setTitle(CharSequence title) {
			this.title = title;
			return this;
		}
		

		public Builder setTitle(int titleResId) {
			this.title = context.getResources().getString(titleResId);
			return this;
		}
		
		public Builder setApplyTitle(CharSequence applyTitle) {
			this.applyTitle = applyTitle;
			return this;
		} 
		
		public Builder setSkuLabel(CharSequence skuLabel) {
			this.skuLabel = skuLabel;
			return this;
		} 
		
		public Builder setSkuName(CharSequence skuName) {
			this.skuName = skuName;
			return this;
		} 
		
		
		
		public Builder setApplyAmount(CharSequence applyAmount) {
			this.applyAmount = applyAmount;
			return this;
		}
		
		 
		public Builder setUserId(CharSequence userId) {
			this.userId = userId;
			return this;
		}
		public Builder setIDNum(CharSequence idNum) {
			this.idNum = idNum;
			return this;
		}
		public Builder setPhoneNumber(CharSequence phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public Builder setUserName(CharSequence userName) {
			this.userName = userName;
			return this;
		}
		public Builder setLabelOther1(CharSequence labelOther1) {
			this.labelOther1 = labelOther1;
			return this;
		}
		public Builder setValueOther1(CharSequence valueOther1) {
			this.valueOther1 = valueOther1;
			return this;
		}
		public Builder setLabelOther2(CharSequence labelOther2) {
			this.labelOther2 = labelOther2;
			return this;
		}
		public Builder setValueOther2(CharSequence valueOther2) {
			this.valueOther2 = valueOther2;
			return this;
		}
		public Builder setRemark(CharSequence remark) {
			this.remark = remark;
			return this;
		}

		public Builder setTitleColor(int titleColor) {
			this.titleColor = titleColor;
			return this;
		}

		public Builder setTitleColor(ColorStateList titleColor) {
			this.titleColorStateList = titleColor;
			return this;
		}

		public Builder setTitleSize(float titleSize) {
			this.titleSize = titleSize;
			return this;
		}

		public Builder setIcon(Drawable icon) {
			this.icon = icon;
			return this;
		}

		public Builder setIcon(int iconResId) {
			this.icon = context.getResources().getDrawable(iconResId);
			return this;
		}

		 

	 
		 

		 
 

		 

	 

		public Builder setButton1(CharSequence text,
				ApplySignPromptDialog.OnClickListener listener) {
			this.button1Text = text;
			this.button1Listener = listener;
			 
			return this;
		}

		public Builder setButton1(int textId,
				ApplySignPromptDialog.OnClickListener listener) {
			this.button1Text = context.getResources().getString(textId);
			this.button1Listener = listener;
			 
			return this;
		}

		public Builder setButton1TextColor(int color) {
			this.button1TextColor = color;
			return this;
		}

		public Builder setButton1TextColor(ColorStateList color) {
			this.button1ColorStateList = color;
			return this;
		}

		public Builder setButton1Size(float button1Size) {
			this.button1Size = button1Size;
			return this;
		}

		 

		 
		 
		 

		 

		public Builder setButton2(CharSequence text,
				ApplySignPromptDialog.OnClickListener listener) {
			this.button2Text = text;
			this.button2Listener = listener;
			 
			return this;
		}

		public Builder setButton2(int textId,
				ApplySignPromptDialog.OnClickListener listener) {
			this.button2Text = context.getResources().getString(textId);
			this.button2Listener = listener;
			 
			return this;
		}

		public Builder setButton2TextColor(int color) {
			this.button2TextColor = color;
			return this;
		}

		public Builder setButton2TextColor(ColorStateList color) {
			this.button2ColorStateList = color;
			return this;
		}

		public Builder setButton2Size(float button3Size) {
			this.button2Size = button3Size;
			return this;
		}

		public Builder setCancelable(boolean cancelable) {
			this.cancelable = cancelable;
			return this;
		}

		public Builder setCanceledOnTouchOutside(boolean canceled) {
			this.canceledOnTouchOutside = canceled;
			return this;
		}

		public Builder setView(View view) {
			this.view = view;
			return this;
		}

		@SuppressLint("InflateParams")
		public ApplySignPromptDialog create() {

			if (dialog == null) {
				return null;
			}
			this.titleColor = Color.WHITE;
			this.titlebarGravity = Gravity.LEFT;
			View mView = null;
			LinearLayout mDialog = null;
			LinearLayout mTitleBar = null;
			TextView mTitle = null;
			 
			TextView btnLeft = null; 
			TextView btnRight = null;
			LinearLayout addView = null; 
			LinearLayout btnView = null;
			View btnDivider  = null; 
			View msgBtnDivider = null;
			
			TextView tvApplyTitle=null;
			TextView tvSkuLabel=null;
			TextView tvSkuName=null;
			LinearLayout llApplyAmount=null;
			TextView tvApplyAmount=null;
			TextView tvUserId=null;
			TextView tvUserName=null;
			TextView tvIDNum=null;
			TextView tvPhoneNumber=null;
			LinearLayout llOther1=null;
			TextView tvLabelOther1=null;
			TextView tvValueOther1=null;
			LinearLayout llOther2=null;
			TextView tvLabelOther2=null;
			TextView tvValueOther2=null;
			TextView tvRemark=null;
			ScrollView svDialog=null;
			 
			mView = LayoutInflater.from(context).inflate(
					R.layout.dialog_prompt_applysign, null);
			mTitleBar = (LinearLayout) mView.findViewById(R.id.titlebar);
			mTitle = (TextView) mView.findViewById(R.id.title);
			mDialog=(LinearLayout) mView.findViewById(R.id.dialog);
			svDialog = (ScrollView) mView.findViewById(R.id.sv_Dialog);
			addView = (LinearLayout) mView.findViewById(R.id.layout_addview);
			/* 
			addView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (linearListener != null) {
						linearListener.onClick(0);
					}
				}
			});
			*/
			
			
			
			
			
			
			
			btnLeft = (TextView) mView.findViewById(R.id.button_left); 
			btnRight = (TextView) mView.findViewById(R.id.button_right);
			btnDivider = (View) mView.findViewById(R.id.btn_divider); 
			msgBtnDivider = (View) mView.findViewById(R.id.msg_btn_divider);
			btnView = (LinearLayout) mView.findViewById(R.id.btn_view);
			tvApplyTitle=(TextView) mView.findViewById(R.id.tv_ApplyTitle);
			tvSkuLabel=(TextView) mView.findViewById(R.id.tv_SkuLabel);
			tvSkuName=(TextView) mView.findViewById(R.id.tv_SkuName);
			tvApplyAmount=(TextView) mView.findViewById(R.id.tv_ApplyAmount);
			llApplyAmount=(LinearLayout) mView.findViewById(R.id.ll_ApplyAmount);
			tvUserId=(TextView) mView.findViewById(R.id.tv_UserId);
			tvUserName=(TextView) mView.findViewById(R.id.tv_UserName);
			tvIDNum=(TextView) mView.findViewById(R.id.tv_IDNum);
			tvPhoneNumber=(TextView) mView.findViewById(R.id.tv_PhoneNumber);
			llOther1=(LinearLayout) mView.findViewById(R.id.ll_Other1);
			tvLabelOther1=(TextView) mView.findViewById(R.id.tv_LabelOther1);
			tvValueOther1=(TextView) mView.findViewById(R.id.tv_ValueOther1);
			llOther2=(LinearLayout) mView.findViewById(R.id.ll_Other2);
			tvLabelOther2=(TextView) mView.findViewById(R.id.tv_LabelOther2);
			tvValueOther2=(TextView) mView.findViewById(R.id.tv_ValueOther2);
			tvRemark=(TextView) mView.findViewById(R.id.tv_Remark);
			 
			
			
			
			if(applyTitle!=null){
				tvApplyTitle.setText(applyTitle);
			}
			if(skuLabel!=null){
				tvSkuLabel.setText(skuLabel);
			}
			if(skuName!=null){
				tvSkuName.setText(skuName);
			}
			
			if(!CommonUtil.trim(applyAmount).equals("")){
				tvApplyAmount.setText(applyAmount);
				llApplyAmount.setVisibility(View.VISIBLE);
			}else{
				llApplyAmount.setVisibility(View.GONE);
			}
			 
			if(userId!=null){
				tvUserId.setText(userId);
			}
			if(userName!=null){
				tvUserName.setText(userName);
			}
			if(idNum!=null){
				tvIDNum.setText(idNum);
			}
			if(phoneNumber!=null){
				tvPhoneNumber.setText(phoneNumber);
			}
			
			if(!CommonUtil.trim(labelOther1).equals("")){
				tvLabelOther1.setText(labelOther1);
				llOther1.setVisibility(View.VISIBLE);
			}else{
				llOther1.setVisibility(View.GONE);
			}
			
			if(valueOther1!=null){
				tvValueOther1.setText(valueOther1);
			}
			if(!CommonUtil.trim(labelOther2).equals("")){
				tvLabelOther2.setText(labelOther2);
				llOther2.setVisibility(View.VISIBLE);
			}else{
				llOther2.setVisibility(View.GONE);
			}
			if(valueOther2!=null){
				tvValueOther2.setText(valueOther2);
			}
			if(remark!=null){
				tvRemark.setText(remark);
			}
			
			
			
			if ((title != null) || (icon != null)) {
				mTitle.setVisibility(View.VISIBLE);
				mTitle.setText(title);
				mTitle.setTextSize(titleSize);
				mTitle.setTextColor(titleColor);
				if (titleColorStateList != null) {
					mTitle.setTextColor(titleColorStateList);
				}
				mTitle.setCompoundDrawables(icon, null, null, null);
				mTitleBar.setGravity(titlebarGravity);
			} else {
				mTitle.setVisibility(View.GONE);
			}

			 
			if (view != null) {
				addView.removeAllViews();
				addView.addView(view);
				addView.setGravity(Gravity.CENTER);
			} 
				 
				btnLeft.setVisibility(View.VISIBLE);
				btnRight.setVisibility(View.VISIBLE);
				 

				if (button1Text != null) {
					btnLeft.setText(button1Text);
					btnLeft.setTextSize(button1Size);
					btnLeft.setTextColor(button1TextColor);

					if (button1ColorStateList != null) {
						btnLeft.setTextColor(button1ColorStateList);
					}

					if (button1Listener != null) {
						btnLeft.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								button1Listener.onClick(dialog, BUTTON_1);
							}
						});
					}
				}

				if (button2Text != null) {
					btnRight.setText(button2Text);
					btnRight.setTextSize(button2Size);
					btnRight.setTextColor(button2TextColor);

					if (button2ColorStateList != null) {
						btnRight.setTextColor(button2ColorStateList);
					}

					if (button2Listener != null) {
						btnRight.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								button2Listener.onClick(dialog, BUTTON_2);
							}
						});
					}
				}
				 
			dialog.setCancelable(cancelable);
			dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);

			dialog.setContentView(mView);
			int screenHeight=PhoneUtil.getScreenHeight(  context); 
			int curHeight=PhoneUtil.getHeight(svDialog);
			if(curHeight>screenHeight*0.7){
				LayoutParams lp =svDialog.getLayoutParams();
				lp.height=(int) (screenHeight*0.7);
				svDialog.setLayoutParams(lp);
			} 
			
			/*
			int marginBorder = PhoneUtil.dip2px(context, 15);
			params.width = PhoneUtil.getScreenWidth(context) - marginBorder * 2;
			window.setAttributes(params);*/
			
			
			return dialog;
		}

		public ApplySignPromptDialog show() {
			create().show();
			return dialog;
		}
	}

	public interface OnClickListener {
		void onClick(Dialog dialog, int which);
	}

	public interface OnLinearClickListener {
		void onClick(int which);
	}
}
