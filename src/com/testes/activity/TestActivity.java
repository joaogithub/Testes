package com.testes.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.android.R;

public class TestActivity extends Activity{


	private int select1;
	private TextView col1, brewingTextView;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);


	    setContentView(R.layout.layout_image_activity);

	    col1 = (TextView) findViewById(R.id.col1TextView);
	    brewingTextView = (TextView) findViewById(R.id.brewingText);

	    brewingTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 class SendSaveRequest extends AsyncTask<String, Void, String> {

		                // private Gson gson = new GsonBuilder().create();
		                // String data = gson.toJson(message);

		                private String sendMessage(String message, String address) {
		                    String url = "http://192.168.87.108:8080/MSS/"
		                            + address;

		                    HttpPost post = new HttpPost(url);

		                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
		                            1);
		                    nameValuePairs.add(new BasicNameValuePair("report",
		                            message));

		                    try {
		                        post.setEntity(new UrlEncodedFormEntity(
		                                nameValuePairs));
		                    } catch (UnsupportedEncodingException e) {
		                    	e.printStackTrace();
		                        Log.i("client", "Your url encoding is shiat fail");
		                    }

		                    HttpClient client = new DefaultHttpClient();
		                    HttpResponse response = null;
		                    try {
		                        Log.i("client", "Trying to execute");
		                        response = client.execute(post);
		                        Log.i("client", "Execution success");                           
		                    } catch (ClientProtocolException e) {
		                        Log.i("client", "Problems with execute post, ClientProtocolException");
		                        e.printStackTrace();
		                    } catch (IOException e) {
		                        Log.i("client", "Problems with execute post, IOException");
		                        e.printStackTrace();
		                    }

		                    HttpEntity entity = response.getEntity();

		                    String responseText = "";
		                    try {
		                        responseText = EntityUtils.toString(entity);
		                    } catch (ParseException e) {
		            
		                        e.printStackTrace();
		                    } catch (IOException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                    }
		                    return responseText;

		                }

		                @Override
		                protected String doInBackground(String... params) {
		                    return sendMessage(params[0], params[1]);
		                }

		                @Override
		                protected void onPostExecute(String result) {
		                    Toast.makeText(TestActivity.this, result,
		                            Toast.LENGTH_LONG).show();
		                }

		            }

//		            String msg = gson.toJson(TestActivity.this
//		                    .generateSavePayload(
//		                    		TestActivity.this.circlesToArea(),
//		                    		TestActivity.this.userId,
//		                    		TestActivity.this.userPassword));

		            new SendSaveRequest().execute("msg", "save");
		        }
				
		});
	    
//	    col1.setOnClickListener(new View.OnClickListener() {
//	        @Override
//	        public void onClick(View v) {
//	            if(select1==0)
//	            {
//	                select1=1;
//	                selectIt(v);
//	            }
//	            else
//	            {
//	                select1=0;
//	                deselectIt(v);
//	            }
//	        }
//	    });
//
//	    initializeActivity();   
	}

	private void initializeActivity()
	{
	    LayerDrawable bgDrawable = (LayerDrawable)col1.getBackground();
	    GradientDrawable shape = (GradientDrawable)  bgDrawable.findDrawableByLayerId(R.id.pat3);
	    shape.setColor(0xFFF04646);
	    select1=0;
	}


	/**
	 * changes the color of pat1
	 * @param v
	 */
	private void selectIt(View v)
	{
	    if(v.getId()==col1.getId())
	    {
	        LayerDrawable bgDrawable = (LayerDrawable)col1.getBackground();
	        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.pat1);
	        shape.mutate();
	        shape.setColor(0xFFF04646);     
	        Log.i("TEST", "SELECT IT");
	    }

	}

	private void deselectIt(View v)
	{
	    if(v.getId()==col1.getId())
	    {
	        LayerDrawable bgDrawable = (LayerDrawable)col1.getBackground();
	        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.pat1);
	        shape.mutate();
	        shape.setColor(Color.WHITE);
	        Log.i("TEST", "DESELECT IT");
	    }

	}




}
