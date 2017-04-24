package com.ris.mobile.ecloud.widget; 
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.util.CommonUtil;

import android.app.Dialog;
import android.content.Context; 
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

 
public class LoadingDialog   extends Dialog {

 
	private TextView tvLoading; 
	private String mText;
	private LinearLayout llLoading;
    private Context mContext;
	public LoadingDialog(Context context, String text) {
		super(context,R.style.transparentFrameWindowStyle);
		mContext=context;
		mText = text; 
		init();
	}

	private void init() {
		setContentView(R.layout.loading_dialog); 
		getWindow().getAttributes().gravity = 17;
		
		
		ImageView ivLoading = (ImageView) findViewById(R.id.iv_Loading);
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				mContext, R.anim.loading_anim);
		// 使用ImageView显示动画
		ivLoading.startAnimation(hyperspaceJumpAnimation);
		tvLoading = (TextView) findViewById(R.id.tv_Loading);
		//llLoading = (LinearLayout) findViewById(R.id.ll_Loading);
		if(!CommonUtil.trim(mText).equals(""))
		         tvLoading.setText(mText);
		
		//llLoading.getBackground().setAlpha(255);
		 
	}

	public void setText(String text) {
		mText = text;
		if(!CommonUtil.trim(mText).equals(""))
	         tvLoading.setText(mText); 
		 
		
	}

	@Override
	public void dismiss() {
		if (isShowing()) {
			super.dismiss();
		}
	}
}