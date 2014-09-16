package com.testes.activity;

import com.testes.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DrawerActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_third_drawer_activity);
		
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
	
}
