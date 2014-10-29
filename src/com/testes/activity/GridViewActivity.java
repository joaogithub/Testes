package com.testes.activity;

import java.util.ArrayList;

import com.testes.adapter.TestGridAdapter;
import com.testes.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;

public class GridViewActivity extends ActionBarActivity 
{

	ArrayList<String> abc;

	TestGridAdapter testGridAdapter;

	GridView gridView;
	Button button;

	int mLastFirstVisibleItem;

	int mLastVisibleItemCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		Log.i("GridViewActivity","onCreate() home");
		
		setContentView(R.layout.home_gridview);

		gridView = (GridView) findViewById(R.id.gridView1);
		button = (Button) findViewById(R.id.nextButton);
		
		abc = new ArrayList<String>();

		for(int i=0;i<100;i++)		{
			abc.add(String.valueOf(i));
		}

		testGridAdapter = new TestGridAdapter(GridViewActivity.this,GridViewActivity.this,abc);

		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				((TabsActivity) getParent()).getTabHost().setCurrentTab(2);
				
			}
		});
		
		gridView.setAdapter(testGridAdapter);

		gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (mLastFirstVisibleItem > firstVisibleItem) {
//					Log.e(getClass().toString(), "scrolling up");
//					getSupportActionBar().show();
				} else if (mLastFirstVisibleItem < firstVisibleItem) {
//					Log.e(getClass().toString(), "scrolling down");
//					getSupportActionBar().hide();

				} else if (mLastVisibleItemCount < visibleItemCount) {
//					Log.e(getClass().toString(), "scrolling down");
//					getSupportActionBar().hide();
				} else if (mLastVisibleItemCount > visibleItemCount) {
//					Log.e(getClass().toString(), "scrolling up");
//					getSupportActionBar().show();
				}
				mLastFirstVisibleItem = firstVisibleItem;
				mLastVisibleItemCount = visibleItemCount;
			}

			public void onScrollStateChanged(AbsListView listView, int scrollState) {
			}
		});

	}

	@Override
	protected void onResume() {
		System.out.println("home resume");
		super.onResume();
	}
	
}