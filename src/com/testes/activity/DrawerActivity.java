package com.testes.activity;

import com.testes.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DrawerActivity extends ActionBarActivity{

	private DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_third_drawer_activity);

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	
		drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

		getSupportActionBar().setHomeButtonEnabled(true);
		
		load();

	}

	public void load(){

		//		RelativeLayout usersLinear = (RelativeLayout) findViewById(R.id.loading_layout);
		LinearLayout usersLinear = (LinearLayout) findViewById(R.id.linLayout);
		RelativeLayout userRelative = new RelativeLayout(this);
		userRelative.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
		userRelative.setPadding(0,5,5,0);
		userRelative.setBackgroundResource(R.drawable.border);
		//		usersLinear.setBackgroundResource(R.drawable.border);
		usersLinear.addView(userRelative);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
//			if(!drawer.isDrawerOpen(Gravity.LEFT))
//				drawer.openDrawer(Gravity.LEFT);
//			else
//				drawer.closeDrawer(Gravity.LEFT);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
