package com.testes.activity;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.testes.adapter.ImageAndTextAdapter;
import com.testes.adapter.NormalAdapter;
import com.testes.android.R;

public class ListViewActivity extends Activity {

	private ListView _listView;
	private ArrayAdapter<String> adapter;
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_hide_layout);
		
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("item 1!");
		titles.add("item 2!");
		titles.add("item 3!");
		titles.add("item 4!");
		
		_listView = (ListView) findViewById(R.id.bookListView);
//		adapter = new NormalAdapter(this, titles);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titles);
		_listView.setAdapter(adapter);
		
		_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("listViewActivity", "CLICK "+ position);
			}
		});
		
		_listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);              

		_listView.setMultiChoiceModeListener(new MultiChoiceModeListener() { 

//		    @Override                                                                
//		    public void deleteSelectedItems() {
//		        for (int i = 0; i < adapter.getCount(); i++) {              
//		            if (_listView.isItemChecked(i)) {                            
//		                //Some actions                          
//		            }                                                                
//		        }                                                                    
//		    }

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				Log.i("LISTVIEW", "onActionItemClicked" + item.toString());
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				
				
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				Log.i("listview", "Checked "+ position);
				
			}                                                                        
		});         
		
		_listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("listv iwactivi", "LONG CLICK "+ position);
				return false;
			}
		});
	}
}
