package com.testes.activity;


import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
	
	private ImageView imageView;
	private Fragment firstFragment, secondFragment, thirdFragment;
	private TextView textView;
	private EditText editText;
	public static final String TAG = "ViewPagerActivity";
	private ArrayList<Fragment> fragmentsArray = new ArrayList<Fragment>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_pager_activity);

		firstFragment = new ImageFragment();
		secondFragment = new TextFragment();
		thirdFragment = new EditTextFragment();
		
		viewPager = (ViewPager) findViewById(R.id.testesPager);
		_awesomePagerAdapter = new TestesPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(_awesomePagerAdapter);
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				Log.i(TAG, "changed to page " + position);
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
				
			}
		});
		
    	int layoutWidth = viewPager.getWidth();
    	Log.i(TAG, "viewpager layout width:"+ layoutWidth);
    	
//    	viewPager.post(new Runnable() {
//    	    @Override
//    	    public void run() {
//    	        Log.i(TAG,"Check my ViewPager Width: " + viewPager.getMeasuredWidth());
//    	        }
//    	    });
		
	}
	
	public class TestesPagerAdapter extends FragmentStatePagerAdapter{

		
		public TestesPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			fragmentsArray.add(secondFragment);
			fragmentsArray.add(firstFragment);
			fragmentsArray.add(thirdFragment);
		}

		@Override
		public int getCount() {
			return fragmentsArray.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
			Log.i(TAG, "destroyed item"+ position);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			if(!fragmentsArray.isEmpty())
				return fragmentsArray.get(position);
			else return null;
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
