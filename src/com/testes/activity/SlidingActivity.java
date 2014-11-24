package com.testes.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityBase;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityHelper;
import com.testes.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class SlidingActivity extends Activity implements SlidingActivityBase{

	SlidingMenu slidingMenu;
	private SlidingActivityHelper mHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new SlidingActivityHelper(this);
		mHelper.onCreate(savedInstanceState);
		setBehindContentView(R.layout.hidden_fragment_frame);
		setContentView(R.layout.long_layout);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate(savedInstanceState);
	}

	@Override
	public void setBehindContentView(View view, LayoutParams layoutParams) {
		
		mHelper.setBehindContentView(view, layoutParams);
		
	}

	@Override
	public void setBehindContentView(View view) {
		setBehindContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
	}

	@Override
	public void setBehindContentView(int layoutResID) {
		setBehindContentView(getLayoutInflater().inflate(layoutResID, null));
		
	}

	@Override
	public SlidingMenu getSlidingMenu() {
		
		return null;
	}

	@Override
	public void toggle() {
		mHelper.toggle();
		
	}

	@Override
	public void showContent() {
		mHelper.showContent();
		
	}

	@Override
	public void showMenu() {
		mHelper.showMenu();
		
	}

	@Override
	public void showSecondaryMenu() {
		mHelper.showSecondaryMenu();
		
	}

	@Override
	public void setSlidingActionBarEnabled(boolean slidingActionBarEnabled) {
		mHelper.setSlidingActionBarEnabled(slidingActionBarEnabled);
		
	}
	
}
