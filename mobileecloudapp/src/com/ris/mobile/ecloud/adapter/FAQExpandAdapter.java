package com.ris.mobile.ecloud.adapter;
 
import java.util.HashMap;
import java.util.List;  

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.object.FAQObject; 
import com.ris.mobile.ecloud.util.CommonUtil;

import android.view.*; 
import android.widget.*;
import android.content.Context;  
public class FAQExpandAdapter extends BaseExpandableListAdapter{   
    private Context context;  
    private List<FAQObject> data;  
    private HandleClick handleClick;   
    public interface HandleClick  
    {  
        public void handleClick(int type);  
        
    }  
      
    public void setHnadleClick(HandleClick hc)  
    {  
        this.handleClick = hc;  
    }  
      
    public FAQExpandAdapter(Context context,List<FAQObject> data)  
    {  
        this.context = context;
        this.data=data;
    }  
      
    public boolean isChildSelectable(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
      
    @Override  
    public boolean hasStableIds() {  
        // TODO Auto-generated method stub  
        return false;  
    }  
      
    @Override  
    public View getGroupView(int groupPosition, boolean isExpanded,  
            View convertView, ViewGroup parent) {  
          
        //convertView = (RelativeLayout)RelativeLayout.inflate(context, R.layout.faq_item, null); 
    	convertView = (FrameLayout)FrameLayout.inflate(context, R.layout.item_faq_title, null);
         
        TextView itvTitle = (TextView)convertView.findViewById(R.id.itv_Title); 
        ImageView iivArrow= (ImageView)convertView.findViewById(R.id.iiv_Arrow);
        
        
       
        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
        	iivArrow.setImageResource(R.drawable.arrow_up);
        } else {
        	iivArrow.setImageResource(R.drawable.arrow_down);
        }
     
        
        FAQObject faqObject=data.get(groupPosition); 
        itvTitle.setText( CommonUtil.trim(faqObject.getTitle()) ); 
         
        return convertView;  
    }  
      
    @Override  
    public long getGroupId(int groupPosition) {  
        // TODO Auto-generated method stub  
        return 0;  
    }  
      
    @Override  
    public int getGroupCount() {  
        // TODO Auto-generated method stub  
        return data.size();  
    }  
      
    @Override  
    public Object getGroup(int groupPosition) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
      
    @Override  
    public int getChildrenCount(int groupPosition) {  
        // TODO Auto-generated method stub  
        return 1;  
    }  
      
    @Override  
    public View getChildView(int groupPosition, int childPosition,  
            boolean isLastChild, View convertView, ViewGroup parent) {
    	// android.widget.LinearLayout cannot be cast to android.widget.FrameLayout

        convertView = (LinearLayout)LinearLayout.inflate(context, R.layout.item_faq_answer, null);   
           
       
        TextView itvAnswer = (TextView)convertView.findViewById(R.id.itv_Answer);   
        itvAnswer.setVisibility(View.VISIBLE); 
        //大果 ：数量20万斤        价格10元/斤"
        FAQObject faqObject=data.get(groupPosition); 
        TextView tvStatistic;
         
        itvAnswer.setText("    ".concat(CommonUtil.trim(faqObject.getContent())) ); 
        return convertView;  
    }  
      
    public long getChildId(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return 0;  
    }  
      
    @Override  
    public Object getChild(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
}  


