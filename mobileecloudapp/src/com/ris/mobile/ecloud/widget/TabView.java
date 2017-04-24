package com.ris.mobile.ecloud.widget;
 
 
import com.ris.mobile.ecloud.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
* @author Administrator
*
*/
public class TabView extends LinearLayout implements OnClickListener {

   private OnTabChangeListener mOnTabChangedListener;

   private static int mState = 0;

   private final Button btnTabHome;
   private final Button btnTabMine;
   private final Button btnTabSetting;
   

   public TabView(Context context) {
       this(context, null);
   }

   public TabView(Context context, AttributeSet attrs) {
       this(context, attrs, 0);
   }

   /**
    * @param context
    * @param attrs
    * @param defStyle
    */
   public TabView(Context context, AttributeSet attrs, int defStyle) {
       super(context, attrs, defStyle);
       inflate(context, R.layout.view_tab, this);
       btnTabHome = (Button) findViewById(R.id.btn_TabHome);
       btnTabMine = (Button) findViewById(R.id.btn_TabMine);
       btnTabSetting = (Button) findViewById(R.id.btn_TabSetting); 

       btnTabHome.setOnClickListener(this);
       btnTabMine.setOnClickListener(this);
       btnTabSetting.setOnClickListener(this); 
   }

   public void setOnTabChangeListener(OnTabChangeListener listener) {
       mOnTabChangedListener = listener;
   }

   public void setCurrentTab(int index) {
       switchState(index);
   }

   private void switchState(int state) {
	   
	   btnTabHome.setSelected(false);
       btnTabMine.setSelected(false);
       btnTabSetting.setSelected(false); 
	   
      if (mState == state) {
    	  switch (mState) {
    	     case 0:
    	    	 btnTabHome.setSelected(true);
    	    	 break;
    	     case 1:
    	    	 btnTabMine.setSelected(true);
    	    	 break;	 
    	     case 2:
    	    	 btnTabSetting.setSelected(true);
    	    	 break;
    	  }
    	  
           return;
       }  

       mState = state;
       btnTabHome.setSelected(false);
       btnTabMine.setSelected(false);
       btnTabSetting.setSelected(false); 

       Object tag = null;

       switch (mState) {
           case 0:
        	   btnTabHome.setSelected(true);
               tag = btnTabHome.getTag();
               break;

           case 1:
        	   btnTabMine.setSelected(true);
               tag = btnTabMine.getTag();
               break;

           case 2:
        	   btnTabSetting.setSelected(true);
               tag = btnTabSetting.getTag();
               break;

            

           default:
               break;
       }

       if (mOnTabChangedListener != null) {
           if (tag != null && mOnTabChangedListener != null) {
               mOnTabChangedListener.onTabChange(tag.toString());
           } else {
               mOnTabChangedListener.onTabChange(null);
           }
       } // else ignored
   }


   /* (non-Javadoc)
    * @see android.view.View.OnClickListener#onClick(android.view.View)
    */
   @Override
   public void onClick(View v) {

       // TODO Auto-generated method stub
       switch (v.getId()) {
           case R.id.btn_TabHome:
               switchState(0);
               break;

           case R.id.btn_TabMine:
        	   
               switchState(1);
               break;

           case R.id.btn_TabSetting:
               switchState(2);
               break;

           

           default:
               break;
       }
   }

   public static interface OnTabChangeListener {
       public void onTabChange(String tag);
   }
}
