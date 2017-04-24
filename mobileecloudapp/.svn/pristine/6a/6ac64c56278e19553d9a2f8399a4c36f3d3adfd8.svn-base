package com.ris.mobile.ecloud.adapter;
import java.util.HashMap;
import java.util.List; 

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.engine.SyncImageLoader;
import com.ris.mobile.ecloud.engine.SyncImageLoader.OnImageLoadListener;
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.RequestObject;
 
public class AdvertViewAdaper extends BaseAdapter implements OnImageLoadListener {

 	private Context context;
	private List<AdvertObject> galleryList;
 	private SyncImageLoader syncImageLoader;
	private Drawable[] drawables;

	public AdvertViewAdaper(Context context, List<AdvertObject> arrayList) {
		this.context = context;
		this.galleryList = arrayList;
 		syncImageLoader = new SyncImageLoader();
		int size = arrayList.size();
		drawables = new Drawable[size];
		syncImageLoader.setLoadLimit(0, size);
		
		AdvertObject advertVo;
		HashMap<String, String> requestDataMap;
		RequestObject reqObject;
		//加载图片
		for (int i = 0; i < size; i++) {
			advertVo=this.galleryList.get(i);
			requestDataMap=new HashMap<String, String>();
    		 
			reqObject = new RequestObject(advertVo.getImageUrl(), this.context, requestDataMap, null);
			reqObject.setMajorKey(advertVo.getAdvertName()); 
			syncImageLoader.loadImage(i,  reqObject, this);
		}
	}

	@Override
	public int getCount() {
		return galleryList.size();
	}

	@Override
	public Object getItem(int postion) {
		return galleryList.get(postion);
	}

	@Override
	public long getItemId(int postion) {
		return postion;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		
		if (convertView == null) { 
			viewHolder = new ViewHolder();
			viewHolder.imageView= new ImageView(context);
			convertView=viewHolder.imageView;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		viewHolder.imageView.setLayoutParams(new Gallery.LayoutParams(
	    		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		Drawable drawable = drawables[position % galleryList.size()];
		if (drawable == null)
			viewHolder.imageView.setImageResource(R.drawable.advertise_default);
		else
			viewHolder.imageView.setImageDrawable(drawable); 
		viewHolder.imageView.setBackgroundResource(R.drawable.home_backgroud);
		/*final AdvertVo advertVo = galleryList.get(position);
		viewHolder.imageView.setOnClickListener(new OnClickListener(){
         	public void onClick(View v)
         	{
         		 System.out.println(advertVo.getImgName());
         	}
          }); */
		
		return convertView;
	}

	@Override
	public void onImageLoad(Integer t, Drawable drawable) {
		this.drawables[t] = drawable;
		notifyDataSetChanged();
	}

	@Override
	public void onError(Integer t) {

	}
	 
     static class ViewHolder
	{
    	ImageView imageView;
    } 
}