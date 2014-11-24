package com.testes.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testes.android.R;

public class TestActivity extends ActionBarActivity{

	private int select1;
	private TextView col1, brewingTextView;
	public static final String TAG = "TestActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

//		setContentView(R.layout.layout_image_activity);
		setContentView(R.layout.new_layout);

		col1 = (TextView) findViewById(R.id.col1TextView);
		brewingTextView = (TextView) findViewById(R.id.brewingText);

//		brewingTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				class SendSaveRequest extends AsyncTask<String, Void, String> {
//
//					private Gson gson = new GsonBuilder().create();
//					//		                String data = gson.toJson(message);
//
//					private String sendMessage(String message, String address) {
//						String url = "http://192.168.87.108:8080/MSS/"
//								+ address;
//
//						HttpPost post = new HttpPost(url);
//
//						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
//								1);
//						nameValuePairs.add(new BasicNameValuePair("report",
//								message));
//
//						try {
//							post.setEntity(new UrlEncodedFormEntity(
//									nameValuePairs));
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//							Log.i("client", "Your url encoding is shiat fail");
//						}
//
//						HttpClient client = new DefaultHttpClient();
//						HttpResponse response = null;
//						try {
//							Log.i(TAG, "Trying to execute");
//							response = client.execute(post);
//							Log.i(TAG, "Execution success");  
//
//						} catch (ClientProtocolException e) {
//							Log.i(TAG, "Problems with execute post, ClientProtocolException");
//							e.printStackTrace();
//						} catch (IOException e) {
//							Log.i(TAG, "Problems with execute post, IOException");
//							e.printStackTrace();
//							return null;
//						}
//
//						HttpEntity entity = response.getEntity();
//
//						String responseText = "";
//						try {
//							responseText = EntityUtils.toString(entity);
//						} catch (ParseException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						return responseText;
//
//					}
//
//					@Override
//					protected String doInBackground(String... params) {
//						return sendMessage(params[0], params[1]);
//					}
//
//					@Override
//					protected void onPostExecute(String result) {
//						Toast.makeText(TestActivity.this, result,
//								Toast.LENGTH_LONG).show();
//					}
//
//				}
//
////				String msg = gson.toJson(TestActivity.this
////						                    .generateSavePayload(
////						                    		TestActivity.this.circlesToArea(),
////						                    		TestActivity.this.userId,
////						                    		TestActivity.this.userPassword));
//
//				new SendSaveRequest().execute("msg", "save");
//			}
//
//		});

		ArrayList<ArrayList<String>> getQuestion = new ArrayList<ArrayList<String>>();
		ArrayList<String> choices = new ArrayList<String>();

		//first retrive elements from getQuestionArray
		String questIndex = "questIndex";
		String aIndex = "aIndex";//getQuestion.get(arrayRow).get(1);
		String bIndex = "bIndex";// = getQuestion.get(arrayRow).get(2);
		String cIndex = "cIndex";//getQuestion.get(arrayRow).get(3);
		String dIndex = "dIndex";//getQuestion.get(arrayRow).get(4);
		String answerIndex = "a";//getQuestion.get(arrayRow).get(5);


		//get the correct answer (This is Word of senteces)
		String correctAnswer = "";
		if(answerIndex.equals("a")){
			correctAnswer = aIndex;
		}else if(answerIndex.equals("b")){
			correctAnswer = bIndex;
		}else if(answerIndex.equals("c")){
			correctAnswer = cIndex;
		}else if(answerIndex.equals("d")){
			correctAnswer = dIndex;
		}

		//Im using this to shuffle my abcd I'm creating new ArrayList for that
		choices.add(aIndex);
		choices.add(bIndex);
		choices.add(cIndex);
		choices.add(dIndex);

		Collections.shuffle(choices);

		int correctIndex;
		//Find now where is our correct answer
		correctIndex = choices.indexOf((String)correctAnswer);

		//our correct letter now
		switch(correctIndex){
		case 0:
			answerIndex = "a";
			break;
		case 1:
			answerIndex = "b";
			break;
		case 2:
			answerIndex = "c";
			break;
		case 3:
			answerIndex = "d";
			break;
		}

		aIndex = choices.get(0);
		bIndex = choices.get(1);
		cIndex = choices.get(2);
		dIndex = choices.get(3);

		Log.i(TAG, "question:"+questIndex);
		Log.i(TAG, "cA "+aIndex);
		Log.i(TAG, "cB "+bIndex);
		Log.i(TAG, "cC "+cIndex);
		Log.i(TAG, "cD "+dIndex);

		if(col1!=null){
			col1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
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
				}
			});
		}

		//	    initializeActivity();
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearlayout1);           
        final RadioGroup rg = new RadioGroup(this);
        final RadioGroup rg2 = new RadioGroup(this);
        rg.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        rg.setOrientation(LinearLayout.HORIZONTAL);
        rg2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        rg2.setOrientation(LinearLayout.HORIZONTAL);
        String [] drinks = new String [] {"Mountain Dew", "7Up", "Root Beer", "Pepsi", "Cola", "Ice Tea"};
        int i = 0;
        
        while(i<drinks.length){
            final String drinkname = drinks[i];
            Log.i(TAG, "drinkname: "+drinkname);
            RadioButton rb = new RadioButton(this);
            rb.setText(drinkname);  
            if(i %2==0)
            	rg.addView(rb);
            else
            	rg2.addView(rb);
            i++;
        }
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	if(rg2.getCheckedRadioButtonId()!=-1){
            		rg2.setOnCheckedChangeListener(null);
            		rg2.check(-1);
            		rg2.setOnCheckedChangeListener(this);
            	}
            	
                int id = group.getCheckedRadioButtonId();
                RadioButton selectrb = (RadioButton) findViewById(id);
                if(selectrb!=null)
                	Log.i(TAG,selectrb.getText().toString());
            }
        });
        
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	if(rg.getCheckedRadioButtonId()!=-1){
            		rg.setOnCheckedChangeListener(null);
            		rg.clearCheck();
            		rg.setOnCheckedChangeListener(this);
            	}
            	
                int id = group.getCheckedRadioButtonId();
                RadioButton selectrb = (RadioButton) findViewById(id);
                if(selectrb!=null)
                	Log.i(TAG,selectrb.getText().toString());
            }
        });
        
        ll.addView(rg);
        ll.addView(rg2);
		
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
			Log.i(TAG, "TEST "+  "DESELECT IT");
		}

	}




}
