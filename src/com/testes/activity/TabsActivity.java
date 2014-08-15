package com.testes.activity;

import com.testes.android.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabsActivity extends TabActivity 
{

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_tabhost);

		TabHost tabHost = getTabHost();
		
		TabSpec tabSpec1 = tabHost.newTabSpec("Home");
		TabSpec tabSpec2 = tabHost.newTabSpec("Second");

		tabSpec1.setIndicator("Home");
		tabSpec2.setIndicator("Second");
		Intent photosIntent = new Intent(this, Home.class);
		Intent secondactivIntent = new Intent(this, ThirdActivity.class);
		tabSpec1.setContent(photosIntent);
		tabSpec2.setContent(secondactivIntent);

		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);


	}

}