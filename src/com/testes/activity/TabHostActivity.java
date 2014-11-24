package com.testes.activity;

import com.testes.android.R;
import com.testes.fragment.FragmentTabsFragmentSupport;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class TabHostActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmenthost_activity);
		
		Fragment frag = new FragmentTabsFragmentSupport();
		getSupportFragmentManager().beginTransaction().add(R.id.fragment1, frag, "String").commit();
		
	}
	
}
