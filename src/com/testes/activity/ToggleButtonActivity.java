package com.testes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.testes.android.R;


public class ToggleButtonActivity extends Activity{

		protected static final String TAG = "ToggleButtonActivity";

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.graphics_view);

			ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle_btn_sunday);
			final TextView num = (TextView) findViewById(R.id.num);
			
			toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						Log.i(TAG, "Button Value: " + Integer.toString(12));
						num.setText( Integer.toString(12));
					}
					else{
						 // The toggle is disabled
                       Log.i(TAG, "Is this called?");
					}
					 Log.i(TAG, "Check changed listener called");
				}
			});
			
		}

}
