package com.testes.adapter;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.android.R;

public class TestGridAdapter extends BaseAdapter
{

	private ArrayList<String> abc;

	private FragmentActivity activity;

	private Context context; 
	private static final int REQUEST_TEXT_CODE=0;

	String text;

	int c = 0;

	int x = 0;

	SelectDateFragment selectDateFragment;


	public TestGridAdapter(FragmentActivity activity , Context cont,ArrayList<String> abc)
	{

		super();
		this.activity=activity;
		this.context = cont;
		this.abc = abc;
	}

	@Override
	public int getCount() 
	{
		return abc.size();
	}

	@Override
	public Object getItem(int in) 
	{       
		return abc.get(in);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return 0;
	}

	public class ViewHolder
	{
		public TextView txt;
	}


	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) 
	{

		final SharedPreferences pref = context.getApplicationContext().getSharedPreferences("TestDate", 0);

		ViewHolder view;

		LayoutInflater inflator = activity.getLayoutInflater();

		if(arg1==null)
		{

			view = new ViewHolder();

			arg1 = inflator.inflate(R.layout.test_grid_item, null);

			view.txt = (TextView) arg1.findViewById(R.id.txt);

			arg1.setTag(view);

		}
		else
		{
			view = (ViewHolder) arg1.getTag();
		}

		OnClickListener alertOnClickListener = new OnClickListener() {

			public void onClick(View v) 
			{

				Toast.makeText(activity.getApplicationContext(), abc.get(arg0)+" - Clicked ", Toast.LENGTH_SHORT).show();

				selectDateFragment = new SelectDateFragment();

				Bundle args = new Bundle();

				selectDateFragment.setArguments(args);

				selectDateFragment.setTargetFragment(selectDateFragment, REQUEST_TEXT_CODE);
				
				selectDateFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
				{

					@Override
					public void onDateSet(DatePicker view, int yy, int mm, int dd)
					{
						if(yy<2014)
							return;
						String saved_date = pref.getString("SavedDate", null);

						Toast.makeText(activity.getApplicationContext(), "SavedDate : "+saved_date,Toast.LENGTH_LONG).show(); 
					}

				});

				selectDateFragment.show(activity.getSupportFragmentManager(), "DatePicker");       

			}

		};

		view.txt.setOnClickListener(alertOnClickListener);

		text = String.valueOf(abc.get(arg0));

		view.txt.setBackgroundResource(R.drawable.background_action_bar);

		view.txt.setTextSize(18);

		view.txt.setTextColor(Color.BLACK);

		view.txt.setGravity(Gravity.CENTER);    

		view.txt.setText(text);

		return arg1;

	}

	@SuppressLint("ValidFragment")
	public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener 
	{

		private DatePickerDialog.OnDateSetListener externalListener;

		public void setOnDateSetListener(DatePickerDialog.OnDateSetListener listener)
		{
			this.externalListener = listener;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) 
		{
			final Calendar calendar = Calendar.getInstance();

			int yy = calendar.get(Calendar.YEAR);

			int mm = calendar.get(Calendar.MONTH);

			int dd = calendar.get(Calendar.DAY_OF_MONTH);

			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}


		public void onDateSet(DatePicker view, int yy, int mm, int dd)
		{

			SharedPreferences preferences = context.getApplicationContext().getSharedPreferences("TestDate", 0);
//			SharedPreferences preferencesO = PreferenceManager.getDefaultSharedPreferences(this);
			preferences.edit().putString("SavedDate", String.valueOf(dd+"/"+mm+"/"+yy)).commit();
			int coinsValue = 0;
			preferences.edit().putInt("coinsValue", coinsValue).commit();

			Log.d("SavedDate : ", String.valueOf(dd+"/"+mm+"/"+yy));

			if(externalListener != null)


				externalListener.onDateSet(view, yy, mm, dd);




		}


		public void onFinishEditDialog(String inputText) 
		{



		}


	}


}