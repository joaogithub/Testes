package com.testes.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.testes.android.R;


public class CheckListActivity extends ListActivity{
//
//
//	public class ActivityCreation extends ListActivity{
		/**
		 * @param args
		 */
		String[] presidents = {
				"Dwight D. Eisenhower",
				"John F. Kennedy",
				"Lyndon B. Johnson",
				"Richard Nixon",
				"Gerald Ford",
				"Jimmy Carter",
				"Ronald Reagan",
				"George H. W. Bush",
				"Bill Clinton",
				"George W. Bush",
				"Barack Obama"
		};

		ListView listview;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.long_layout);

			//list_view_rows    
			listview = getListView();

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_exam_list_item, R.id.examTitle, presidents);
			listview.setAdapter(adapter);

		}


		@Override
		protected void onListItemClick(ListView parent, View v, int position, long id) {
			super.onListItemClick(parent, v, position, id);
			Toast.makeText(this, "You have selected " + presidents[position],
					Toast.LENGTH_SHORT).show();
		}
//	}
}
