package com.testes.activity;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import com.testes.android.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TableLayoutActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_scroll);

		TableLayout tableLayout=(TableLayout) findViewById(R.id.table_layout);
		TableRow row= new TableRow(this);
		TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(rowLayoutParams);
		TableRow row2= new TableRow(this);
		TextView tv1 = new TextView(this);
		tv1.setSingleLine(false);
		tv1.setEllipsize(null);
		tv1.setTextSize(getResources().getDimension(R.dimen.textsize));
		tv1.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
		tv1.setHorizontallyScrolling(false);
		tv1.setText("THIS LINE OF THEXT SHOULD BE SINGFLE. DOUBLE MAYBE?");
		
		TextView tv2 = new TextView(this);
		tv2.setSingleLine(false);
		tv2.setEllipsize(null);
		tv2.setTextSize(getResources().getDimension(R.dimen.textsize));
		tv2.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
		tv2.setHorizontallyScrolling(false);
		tv2.setText("THIS LEINBE HAS to be multiline also?");
		
		row.addView(tv1);
		row2.addView(tv2);
		tableLayout.addView(row,2);
		tableLayout.addView(row2,3);

	}


}
