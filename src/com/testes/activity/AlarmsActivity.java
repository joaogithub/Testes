package com.testes.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
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

	    int hour = 19;
	    int minute = 00;
	    String myTime = String.valueOf(hour) + ":" + String.valueOf(minute);

	    Date date = null;

	    // today at your defined time Calendar    
	    Calendar customCalendar = new GregorianCalendar();
	    // set hours and minutes
	    customCalendar.set(Calendar.HOUR_OF_DAY, hour);
	    customCalendar.set(Calendar.MINUTE, minute);
	    customCalendar.set(Calendar.SECOND, 0);
	    customCalendar.set(Calendar.MILLISECOND, 0);
	    
	    Date customDate = customCalendar.getTime();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
	    try {

	        date = sdf.parse(myTime);

	    } catch (ParseException e) {

	        e.printStackTrace();
	    } catch (java.text.ParseException e) {
			e.printStackTrace();
		}

	    if (date != null) {
	        timeInMs = customDate.getTime();
	    }

	    Intent intent = new Intent(this, FirstActivity.class);
	    PendingIntent action = PendingIntent.getActivity(this, 0,intent, Intent.FLAG_ACTIVITY_NEW_TASK);

	    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	    am.set(AlarmManager.RTC_WAKEUP, timeInMs, action);
	}
	
}
