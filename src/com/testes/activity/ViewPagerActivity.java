package com.testes.activity;


import java.util.ArrayList;

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
	private ArrayList<View> viewsArray = new ArrayList<View>();
	private ImageView imageView;
	private TextView textView;
	private EditText editText;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_pager_activity);

		textView = new TextView(ViewPagerActivity.this);
		textView.setText("My textview");
		imageView = new ImageView(ViewPagerActivity.this);
		imageView.setImageResource(R.drawable.ic_launcher);
		
		editText =  new EditText(ViewPagerActivity.this);
		editText.setHint("Myedittext");
		
		viewsArray.add(textView);
		viewsArray.add(imageView);
		viewsArray.add(editText);
		
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
			return viewsArray.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			Log.i("Viewpageractiviy", "destryed item"+ position);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		
			return viewsArray.get(position);
			
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public int getItemPosition(Object object) {
			return viewsArray.indexOf(object);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return super.getPageTitle(position);
		}
		
		
		
	}
	
}
