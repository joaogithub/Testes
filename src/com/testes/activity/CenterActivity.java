package com.testes.activity;

import com.testes.android.R;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CenterActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_center);
		
		ListView listView = (ListView) findViewById(R.id.parentListContainer);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout_center_item, R.id.titleView, new String[]{"Item 1", "Item 2"});
		listView.setAdapter(arrayAdapter);
		
	}
	
}
