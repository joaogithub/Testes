package com.testes.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.testes.android.R;

public class ViewPagerActivity extends ActionBarActivity{

	ViewPager viewPager;
	private TestesPagerAdapter _awesomePagerAdapter; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_pager_activity);

		viewPager = (ViewPager) findViewById(R.id.testesPager);
		_awesomePagerAdapter = new TestesPagerAdapter();
		viewPager.setAdapter(_awesomePagerAdapter);
		
    	int layoutWidth = viewPager.getWidth();
    	System.out.println(layoutWidth);
    	
    	viewPager.post(new Runnable() {
    	    @Override
    	    public void run() {
    	        Log.d("Check my ViewPager Width", "width " + viewPager.getMeasuredWidth());
    	        }
    	    });
		
	}
	
	public class TestesPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = null;
			
			switch(position){
			case 0:
				view = new TextView(ViewPagerActivity.this);
				break;
			case 1:
				view = new ImageView(ViewPagerActivity.this);
				break;
			case 2:
				view = new EditText(ViewPagerActivity.this);
			}
			
			return view;
			
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return false;
		}
		
		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
		
	}
	
}
