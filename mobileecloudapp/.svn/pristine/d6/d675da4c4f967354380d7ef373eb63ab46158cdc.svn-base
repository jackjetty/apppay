package com.ris.mobile.ecloud.widget; 
 

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery
{
	private static final int timerAnimation = 1;
	private String direction="right";
	private boolean runing; 
	public boolean isRuning() {
		return runing;
	}

	public void setRuning(boolean runing) {
		this.runing = runing;
	}

	private final Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case timerAnimation:
				 
				// MyGallery.this.setSelection(MyGallery.this.getSelectedItemPosition() + 1);
				  
				int position = getSelectedItemPosition();
				if(position==0)
					direction="right";
				if(position==(getCount() - 1))
					direction="left"; 
				 
				if (direction.equalsIgnoreCase("left"))
				{
					onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
				} else
				{
					onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				} 
				break;

			default:
				break;
			}
		};
	};

	private final Timer timer = new Timer();
	private final TimerTask task = new TimerTask()
	{
		public void run()
		{ 
			
			if(isRuning()){
				mHandler.sendEmptyMessage(timerAnimation);
			}       
			 
		}
	};

	public MyGallery(Context paramContext)
	{
		super(paramContext);
		setRuning(true);
		timer.schedule(task, 4000, 4000);
	}

	public MyGallery(Context paramContext, AttributeSet paramAttributeSet)
	{
		super(paramContext, paramAttributeSet);
		setRuning(true);
		timer.schedule(task, 4000, 4000);

	}

	public MyGallery(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt)
	{
		super(paramContext, paramAttributeSet, paramInt);
		timer.schedule(task, 4000, 4000);

	} 
	private boolean isScrollingLeft(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2)
	{
		float f2 = paramMotionEvent2.getX();
		float f1 = paramMotionEvent1.getX();
		if (f2 > f1)
			return true;
		return false;
	}

	public boolean onFling(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
	{
		int keyCode;
		if (isScrollingLeft(paramMotionEvent1, paramMotionEvent2))
		{
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		} else
		{
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(keyCode, null);
		return true;
	}

	public void destroy()
	{
		timer.cancel();
	}
}