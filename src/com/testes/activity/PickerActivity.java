package com.testes.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.testes.android.R;

public class PickerActivity extends ActionBarActivity{

	String buttonText= "";
	ImageView startImage;
	LinearLayout viewToInject;
	EditText numberEdit;
	TextView angleText;
	View line;

	float degreesSoItIsParallelToTheGround=0f;
	String phoneRotationDegrees="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_pickers);

		String [] degreesValues = new String [20];

		for(int i= 0;i<20;i++){
			degreesValues[i] = String.valueOf(i)+ (char) 0x00B0;
		}

		NumberPicker picker = (NumberPicker) findViewById(R.id.numberPicker);
//		picker.setMinValue(0);
//		picker.setMaxValue(19);
//		picker.setDisplayedValues(degreesValues);
		
		int year = 2014;
		int month = 6;
		int day = 27;

		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		 
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(year, month, day);
		Time time = new Time();
		time.set(day, month, year);
		
//		datePicker.updateDate(0, 0, 0);
//		datePicker.setMinDate(calendar.getTimeInMillis()-1000);
//		datePicker.setMinDate(time.toMillis(true)-1000);
		
		numberEdit = (EditText) findViewById(R.id.somenumberEditText);
		numberEdit.requestFocus();
	}



	@Override
	protected void onPause() {

		super.onPause();

	}

	@Override
	protected void onResume() {

		super.onResume();

	}



//You can do it by setting a Calendar with the values from the Intent, and inside onDateSet() callback, compare that Calendar with another Calendar with the input values from the user,like this:
//
//public void onDateSet(DatePicker view, int y, int m, int d)
//    {
//        //the Calendar with the values retrieved from Intent
//        Calendar intentCalendar = Calendar.getInstance();
//
//        //the Calendar with the picker input values
//        Calendar inputCalendar = Calendar.getInstance();
//
//        //set the correspondent values to each of the Calendars
//        intentCalendar.set(year, month, day);
//        inputCalendar.set(y, m, d);
//
//        //check if input values date is after intent values date
//        if(inputCalendar.after(intentCalendar)){
//               //do what you have to do
//        }
//        //the values are before the intent date - warn user
//        else{
//               //show a message to user or something
//        }
//     }



}
