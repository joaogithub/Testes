package com.testes.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class JsonParseActivity extends Activity{

	String TAG = "JsonParseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		JSONObject obj = new JSONObject();
		String name ="Vũ Viết Kiên";
		try {                           
			obj.put("name", name);                          

		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			Log.i("JsonParseActivity", ""+obj.get("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// Declarations
		Calendar cal;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm:ss" );
		String result;
		
		long timeInSecs = 9;
		
		// Simple Date class usage
		Date date = new Date( timeInSecs * 1000 );
		result = simpleDateFormat.format( date );             // WRONG result: "01:00:09"
		
		// Calendar - Timezone GMT
		cal = new GregorianCalendar( TimeZone.getTimeZone( "GMT" ) );

		cal.setTimeInMillis( timeInSecs * 1000 );
		result = simpleDateFormat.format( cal.getTime() );    // WRONG result: "01:00:09"

//		cal.set( 1970, Calendar.JANUARY, 1, 0, 0, timeInSecs );
		result = simpleDateFormat.format( cal.getTime() );    // WRONG result: "01:00:09"
		
		// Calendar - Timezone UTC
		cal = new GregorianCalendar( TimeZone.getTimeZone( "UTC" ) );

		cal.setTimeInMillis( timeInSecs * 1000 );
		result = simpleDateFormat.format( cal.getTime() );    // WRONG result: "01:00:09"

//		cal.set( 1970, Calendar.JANUARY, 1, 0, 0, timeInSecs );
		result = simpleDateFormat.format( cal.getTime() );    // WRONG result: "01:00:09"
		
		int millis = 9 * 1000;
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	    df.setTimeZone(tz);
	    String time = df.format(new Date(millis));
	    Log.i("Duration in seconds: ", time);
	 
	}
	
	public long getLongFromPreferences(String key,Context mContext){
	    SharedPreferences sharedPreferences = mContext.getSharedPreferences(TAG,Context.MODE_PRIVATE);
	    long isString = sharedPreferences.getLong(key, -1);
	    return isString;       
	}
	
}
