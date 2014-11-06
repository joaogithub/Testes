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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.testes.android.R;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class JsonParseActivity extends ActionBarActivity{

	int POST = 1;
	int GET = 2;
	public static String TAG = "JsonParseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_empty);
		
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
	    Log.i(TAG, "Duration in seconds:"+ time);
	    
	    
	    new AsyncTask<Void,Void, String>(){

			@Override
			protected String doInBackground(Void... params) {
				return makeServiceCall("https://api.github.com/users", 2, null);
			}
			
			protected void onPostExecute(String result) {
				try {
					result = "[{\"subject\":\"Subject One\",\"time\":\"2:00pm\"},{\"subject\":\"Subject Two\",\"time\":\"2:30pm\"}]";
					JSONArray readerArray = new JSONArray(result);
					Log.i(TAG, ((JSONArray)readerArray).toString());
					String [] items = new String[readerArray.length()];
					for(int i=0;i<readerArray.length();i++){
						JSONObject userObject = (JSONObject) readerArray.get(i);
						String login = userObject.getString("login");
						//add login to your ArrayList here
						int id = userObject.getInt("id");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			};
	    	
	    }.execute();
	    
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
