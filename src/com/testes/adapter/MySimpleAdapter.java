package com.testes.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ListActivity;
import android.sax.Element;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import com.testes.android.R;

public class MySimpleAdapter extends ListActivity{


	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		ArrayList<HashMap<String, String>> stackItems = new ArrayList<HashMap<String, String>> ();
		
		for (int i=0; i<7; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("name", "name"+i);
			map.put("val", "val"+i);
			map.put("mov", "mov"+i);
			stackItems.add(map);
		}

		ListAdapter adapter = new SimpleAdapter(this, stackItems,
				android.R.layout.simple_list_item_1,
				new String[] { "name", "val", "mov"}, new int[] {
				1,

				//                      if(stackItems.get(i)>0)
				2,
				3});

		setListAdapter(adapter);
	};



}
