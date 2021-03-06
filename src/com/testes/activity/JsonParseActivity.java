package com.testes.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.JsonObject;
import com.testes.android.Exam;
import com.testes.android.R;















import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
		
		String contentsAsString = "[{\"Lng\":\"-1.5908601\",\"Lat\":\"53.7987816\"},{\"Lng\":\"-2.5608601\",\"Lat\":\"54.7987816\"}]";
//		{"Lng":"-3.5608601","Lat":"55.7987816"}
//		{"Lng":"-4.5608601","Lat":"56.7987816"}
//		{"Lng":"-5.560837","Lat":"57.7987816"}
//		{"Lng":"-6.5608294","Lat":"58.7987772"}
//		{"Lng":"-7.5608506","Lat":"59.7987823"}";
		
		JSONObject jsonObject = null;
		JSONArray jsonArray;
		try {
			jsonObject = new JSONObject(contentsAsString);
			jsonArray = new JSONArray(contentsAsString);
			Log.i(TAG, jsonArray.get(0).toString());
			Iterator<String> iter = jsonObject.keys();
			  while(iter.hasNext())
			    {
			       String key = iter.next();
			         try{
			             Object value = jsonObject.get(key);
			             Log.i(TAG,"Value :- "+ value);
			         }catch(JSONException e)
			          {
			              //error
			        	 e.printStackTrace();
			          }
			    }
			
			
		} catch (JSONException e1) {
			e1.printStackTrace();
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
	    
	    JsonFactory factory = new JsonFactory();
	    factory.canHandleBinaryNatively();
	    
	    String jsonString = "[{\"name\":\"foo\",\"slug\":\"foo2\"}]";
	    JSONObject object = null;
	    JSONArray jsonArr = null;
		try {
			jsonArr = new JSONArray(jsonString);
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	    Iterator<String> keys = null;
	    if(object!=null)
	    	keys = object.keys();

	    if(keys!=null){
	    while (keys.hasNext()){

	        String keyValue = (String)keys.next();
	        try {
				jsonString = jsonString + object.getString(keyValue);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	    }
	    }

	    int count = 3;
	    
	    jsonString= jsonString.substring(1, jsonString.length()-1);
	    ObjectMapper mp = new ObjectMapper();
	    try {
			Object response = mp.readValue(jsonString, Exam.class);
		} catch (JsonParseException je) {
			je.printStackTrace();
		} catch (JsonMappingException me) {
			me.printStackTrace();
		} catch (IOException ie) {
			
			ie.printStackTrace();
		}
	    
	    LinearLayout linear = null, layout = null;
	    LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("Sweat", "Sweat");
	    map.put("tshirt", "tshirt");
	    map.put("hat", "hat");
	    Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(count%3==0){
                if(linear!=null){
                    layout.addView(linear);
                }
                linear = new LinearLayout(getApplicationContext());
                linear.setOrientation(LinearLayout.HORIZONTAL);
                linear.setLayoutParams(layoutParams);
            }
            LinearLayout linear2 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, 
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            lp1.setMargins(15, 0, 15, 0);
            linear2.setLayoutParams(lp1);
            linear2.setOrientation(LinearLayout.VERTICAL);
            final String finalstring = (String)pair.getKey();
            Button button = new Button(getApplicationContext());
            button.setText((String)pair.getKey());
            button.setClickable(false);
            button.setBackgroundColor(getResources().getColor(R.color.friking_blue));

            ImageView image = new ImageView(getApplicationContext());
//            new DownloadImageTask(image).execute((String)pair.getValue());

            linear2.addView(image);
            linear2.addView(button);
            linear.addView(linear2);
            linear2.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("category", finalstring);
                    startActivity(intent);
                }
            });
            count++;
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        addContentView(linear, layoutParams);
	    
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
