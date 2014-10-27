package com.testes.activity;


import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.testes.android.R;
import com.testes.fragment.EditTextFragment;
import com.testes.fragment.ImageFragment;
import com.testes.fragment.TextFragment;

public class ViewPagerActivity extends ActionBarActivity{

	ViewPager viewPager;
	private TestesPagerAdapter _awesomePagerAdapter; 
	private ArrayList<Fragment> fragmentsArray = new ArrayList<Fragment>();
	private ImageView imageView;
	private Fragment firstFragment, secondFragment, thirdFragment;
	private TextView textView;
	private EditText editText;
	public static final String TAG = "ViewPagerActivity";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_pager_activity);

		
		firstFragment = new ImageFragment();
		secondFragment = new TextFragment();
		thirdFragment = new EditTextFragment();
		fragmentsArray.add(secondFragment);
		fragmentsArray.add(firstFragment);
		fragmentsArray.add(thirdFragment);
		
		viewPager = (ViewPager) findViewById(R.id.testesPager);
		_awesomePagerAdapter = new TestesPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(_awesomePagerAdapter);
		
    	int layoutWidth = viewPager.getWidth();
    	Log.i(TAG, "layout width:"+ layoutWidth);
    	
    	viewPager.post(new Runnable() {
    	    @Override
    	    public void run() {
    	        Log.d(TAG,"Check my ViewPager Width: " + viewPager.getMeasuredWidth());
    	        }
    	    });
		
	}
	
	public class TestesPagerAdapter extends FragmentStatePagerAdapter{

		public TestesPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return fragmentsArray.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			Log.i(TAG, "destroyed item"+ position);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		
			return fragmentsArray.get(position);
			
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public int getItemPosition(Object object) {
			return fragmentsArray.indexOf(object);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return super.getPageTitle(position);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentsArray.get(position);
		}
		
		
	}
	
}
