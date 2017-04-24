package com.ris.mobile.ecloud.adapter;
 
import java.util.ArrayList;

import com.ris.mobile.ecloud.fragment.ArticlePageFragment;
import com.ris.mobile.ecloud.object.CategoryObject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;  
public class ArticleFragmentPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private FragmentManager fm;
	private ArrayList<CategoryObject> categorys;

	public ArticleFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public ArticleFragmentPagerAdapter(FragmentManager fm,
			ArrayList<CategoryObject> categorys) {
		super(fm);
		this.fm = fm;
		this.categorys=categorys;
		ArticlePageFragment newInstance = null;
		for (CategoryObject itme : categorys) {
			newInstance = ArticlePageFragment.newInstance(itme.getCategoryId());
			fragments.add(newInstance);
		}
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	 

	@Override
	public Fragment getItem(int position) {
		
		
		 
        ArticlePageFragment fragment=(ArticlePageFragment)fragments.get(position);
         
        
		return fragment;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
 
	public void setFragments(ArrayList<Fragment> fragments) {
		if (this.fragments != null) {
			FragmentTransaction ft = fm.beginTransaction();
			for (Fragment f : this.fragments) {
				ft.remove(f);
			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
		this.fragments = fragments;
		notifyDataSetChanged();
	} 

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		 Object obj = super.instantiateItem(container, position);
		 return obj;
		
		 /*
	        Fragment fragment = fragments.get(position);
	        if(!fragment.isAdded()){ // 如果fragment还没有added
	            FragmentTransaction ft = fm.beginTransaction();
	            ft.add(fragment, fragment.getClass().getSimpleName());
	            ft.commit();
	             
	            fm.executePendingTransactions();
	        }

	        if(fragment.getView().getParent() == null){
	            container.addView(fragment.getView()); // 为viewpager增加布局
	        }

	        return fragment.getView();*/
	    }
		
	 

}

 