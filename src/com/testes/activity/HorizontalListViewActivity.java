package com.testes.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.testes.android.R;

public class HorizontalListViewActivity extends Activity {

	private ListView _listView;
	private GreenAdapter adapter;
	private ArrayList<String> txts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrolldata);

		ArrayList<String> titles = new ArrayList<String>();
		txts =  new ArrayList<String>();
		titles.add("item 1!");
		titles.add("item 2!");
		titles.add("item 3!");
		titles.add("item 4!");

		_listView = (ListView) findViewById(R.id.DataRows);
		//		adapter = new NormalAdapter(this, titles);
		adapter = new GreenAdapter(this, R.layout.record,titles);
		_listView.setAdapter(adapter);

		_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("listViewActivity", "CLICK "+ position);
			}
		});


	}

	public class GreenAdapter extends BaseAdapter{

		public GreenAdapter(
				HorizontalListViewActivity horizontalListViewActivity,
				int record, ArrayList<String> titles) {
		}

		@Override
		public int getCount() {

			return 1;
		}

		@Override
		public Object getItem(int position) {

			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View item = convertView;
			
			if(item==null){
				
				ViewHolder viewHolder = new ViewHolder();
				item = getLayoutInflater().inflate(R.layout.record, parent, false);
				
				item.setTag(viewHolder);
			}
			else{
				final ViewHolder holder = (ViewHolder) item.getTag();
			}
				
			return item;
		}

	}
	
	private static class ViewHolder {
		public LinearLayout background;
		public TextView name;
		public ImageView img;
	}
}
