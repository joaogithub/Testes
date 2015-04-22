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
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.testes.android.R;
import com.testes.views.BrushViewClass;

public class ListViewActivity extends Activity {

	private ListView _listView;
	private BaseAdapter adapter;
	private ArrayList<String> titlesArray;
	private LinearLayout home_linearLayout, search_layout;
	private Button search_btn;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_hide_layout);

		titlesArray = new ArrayList<String>();
		titlesArray.add("item 1!");
		titlesArray.add("item 2!");
		titlesArray.add("item 3!");
		titlesArray.add("item 4!");

		search_btn = (Button) findViewById(R.id.search_btn);
		
		search_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				home_linearLayout.setVisibility(View.GONE);
				search_layout.setVisibility(LinearLayout.VISIBLE);
			}
		});
		
		_listView = (ListView) findViewById(R.id.bookListView);
		//		adapter = new NormalAdapter(this, titles);
		//		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,titles);

		adapter = new MyAdapter();
		_listView.setAdapter(adapter);

		_listView.setSelection(2);

		_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("listViewActivity", "CLICK "+ position);
			}
		});

		_listView.pointToPosition(200, 20);

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
				Log.i("listVIEWactivity", "LONG CLICK "+ position);
				return false;
			}
		});

		home_linearLayout=(LinearLayout)findViewById(R.id.home_linear);

		BrushViewClass view=new BrushViewClass(this);
		home_linearLayout.addView(view.btnEraseAll, view.params);

		search_layout = (LinearLayout)findViewById(R.id.search_layout);

	}

	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return titlesArray.size();
		}

		@Override
		public Object getItem(int position) {
			return titlesArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View item = convertView;

			ViewHolder viewHolder = new ViewHolder();
			if(item==null){

				item = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, parent, false);

				item.setTag(viewHolder);


			}
			else{
				viewHolder = (ViewHolder) item.getTag();
			}


			viewHolder.subTitleText = (TextView) item.findViewById(android.R.id.text2);
			viewHolder.titleText = (TextView) item.findViewById(android.R.id.text1);

			viewHolder.titleText.setText(titlesArray.get(position));

			return item;
		}

	}


	public void toggleMenu(View v){
		Log.i("Toggle method", "Toggle Method");
		home_linearLayout.setVisibility(View.GONE); // not working

	}

	public class ViewHolder{
		private TextView titleText;
		private TextView subTitleText;
	}

}
