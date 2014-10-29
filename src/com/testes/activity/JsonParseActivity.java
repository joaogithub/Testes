package com.testes.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class JsonParseActivity extends Activity{

	String TAG = "JsonParseActivity";
	int POST = 1;
	int GET = 2;
	
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
	
	public String makeServiceCall(String url, int method,
	        List<NameValuePair> params) {
		String response = null;
	    try {
	        // http client
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpEntity httpEntity = null;
	        HttpResponse httpResponse = null;

	        // Checking http request method type
	        if (method == POST) {
	            HttpPost httpPost = new HttpPost(url);
	            // adding post params
	            if (params != null) {
	                httpPost.setEntity(new UrlEncodedFormEntity(params));
	            }

	            httpResponse = httpClient.execute(httpPost);

	        } else if (method == GET) {
	            // appending params to url
	            if (params != null) {
	                String paramString = URLEncodedUtils
	                        .format(params, "utf-8");
	                url += "?" + paramString;
	            }
	            HttpGet httpGet = new HttpGet(url);

	            httpResponse = httpClient.execute(httpGet);

	        }
	        httpEntity = httpResponse.getEntity();
	        response = EntityUtils.toString(httpEntity);

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return response;

	}
	
}
