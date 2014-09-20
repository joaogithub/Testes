package com.testes.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testes.android.R;

public class AlarmsActivity extends ActionBarActivity{

	String buttonText= "";
	LinearLayout viewToInject;
	private long timeInMs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dog);

		setAlarm();
		
	}

	public void setAlarm(){

	    int hour = 18;
	    int minute = 15;
	    String myTime = String.valueOf(hour) + ":" + String.valueOf(minute);

	    Date date = null;

	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {

	        date = sdf.parse(myTime);

	    } catch (ParseException e) {

	        e.printStackTrace();
	    } catch (java.text.ParseException e) {
			e.printStackTrace();
		}

	    if (date != null) {
	        timeInMs = date.getTime() + System.currentTimeMillis();
	    }

	    Intent intent = new Intent(this, FirstActivity.class);
	    PendingIntent action = PendingIntent.getActivity(this, 0,intent, 0);

	    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	    am.set(AlarmManager.RTC_WAKEUP, timeInMs, action);
	}
	
}
