package com.testes.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.testes.android.R;

public class AlarmsActivity extends ActionBarActivity{

	private String buttonText= "";
	private long timeInMs;
	private int imagen=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_dog);

//		setAlarm();
		setOneDayAlarm();

	}

	public void setOneDayAlarm(){
		Calendar c = Calendar.getInstance();
		c.set(2014, 9, 24);
		c.set(Calendar.HOUR_OF_DAY, 12);
		c.set(Calendar.MINUTE, 13);
		c.set(Calendar.SECOND, 0);
		Intent intentservice = new Intent(this, FirstActivity.class);

		//create a pending intent to be called at 6 AM
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intentservice, PendingIntent.FLAG_UPDATE_CURRENT);
		//schedule time for pending intent, and set the interval to day so that this event will repeat at the selected time every day

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

		//schedule time for pending intent, and set the interval to day so that this event will repeat at the selected time every day
		am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24*3600*1000, pIntent);
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

		
		Intent intent = new Intent();
		
		switch(imagen){
		
		}
		
		intent = new Intent(this, FirstActivity.class);
		PendingIntent actionIntent = PendingIntent.getActivity(this, 0,intent, Intent.FLAG_ACTIVITY_NEW_TASK);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMs, actionIntent);
	}

}
