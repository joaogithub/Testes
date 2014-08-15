package com.testes.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testes.android.R;

public class EditTextActivity extends ActionBarActivity{

	String buttonText= "";
	LinearLayout viewToInject;
	EditText numberEdit;
	TextView angleText;
	View line;

	float degreesSoItIsParallelToTheGround=0f;
	String phoneRotationDegrees="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_edittext);

		String [] degreesValues = new String [20];

		for(int i= 0;i<20;i++){
			degreesValues[i] = String.valueOf(i)+ (char) 0x00B0;
		}

		final EditText editText = (EditText) findViewById(R.id.editTextAct);
		
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.toString().contains(",")){
					Log.i("CONTAINS", "CONTAINS");
				}
				if(s.length()>3){
					Log.i("Content", s.toString().replace("'", "''"));
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				

			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		
		
		JSONObject obj = new JSONObject();
        String name ="Vũ Viết Kiên";
                   try {                           
                       obj.put("name", name);                          

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
		
                   try {
					Log.i("String,", ""+obj.get("name"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
                   
	}



	@Override
	protected void onPause() {

		super.onPause();

	}

	@Override
	protected void onResume() {

		super.onResume();

	}




}
