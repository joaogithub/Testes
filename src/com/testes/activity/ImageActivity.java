package com.testes.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Attendees;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testes.android.R;

public class ImageActivity extends ActionBarActivity{

	String buttonText= "";
	LinearLayout viewToInject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_weight);
		
//		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)  mViewHolder.startDate.getLayoutParams();
//	    RelativeLayout.LayoutParams paramsImage = (RelativeLayout.LayoutParams) imgView.
//	   double conversion = minute*2.7;
//	   int topmargin = (int)conversion;
//	  // params.setMargins(0,topmargin,0,0);
//	   params.topMargin = topmargin;
//	   paramsImage.topMargin = topmargin+15;
	       SimpleDateFormat df = new SimpleDateFormat("h:mm a");
	       String date = df.format(Calendar.getInstance().getTime());
//	       if(hour == myList.get(position).getPosition())
//	       {
//	           mViewHolder.startDate.setLayoutParams(params);
//	           mViewHolder.startDate.setTextColor(Color.BLUE);
//	           mViewHolder.startDate =detail(convertView, R.id.datetitle, date);
//	           if(imgView != null)
//	               imgView.setLayoutParams(paramsImage);
//	               imgView.setVisibility(1);
//	                imgView.bringToFront();
//	       }
//	       else{
//	           params.topMargin=0;
//	           mViewHolder.startDate.setLayoutParams(params);
//	           mViewHolder.startDate.setTextColor(Color.BLACK);
//	           imgView.setVisibility(View.INVISIBLE);
//	       }
		
	       
	}


}
