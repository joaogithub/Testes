package com.testes.activity;

import com.testes.android.R;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LayoutActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_layout);

		View view = getLayoutInflater().inflate(R.layout.inflate_layout, null);
		if (view != null) {
			LinearLayout mContentContainer = (LinearLayout) findViewById(R.id.content);
			mContentContainer.addView(view);
			
			
		}

	}

}
