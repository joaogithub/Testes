package com.testes.activity;


import com.testes.android.R;
import com.testes.android.R.id;
import com.testes.android.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ViewPagerActivity extends Activity{

	ViewPager viewPager;
	private TestesPagerAdapter _awesomeAdapter; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_pager_activity);

		viewPager = (ViewPager) findViewById(R.id.testesPager);

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
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
}
