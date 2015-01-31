package com.testes.adapter;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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
import com.testes.database.TinyDB;

public class TestGridAdapter extends BaseAdapter
{

	private ArrayList<String> abcStringList;

	private FragmentActivity activity;

	private Context context; 
	private static final int REQUEST_TEXT_CODE=0;

	String text;

	int c = 0;

	int x = 0;

	SelectDateFragment selectDateFragment;


	public TestGridAdapter(FragmentActivity gridViewActivity , Context conte,ArrayList<String> abc)
	{
		super();
		this.activity=gridViewActivity;
		this.context = conte;
		this.abcStringList = abc;
	}

	@Override
	public int getCount() 
	{
		return abcStringList.size();
	}

	@Override
	public Object getItem(int in) 
	{       
		return abcStringList.get(in);
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
	public View getView(final int position, View convertView, ViewGroup parent) 
	{

		final SharedPreferences pref = context.getApplicationContext().getSharedPreferences("TestDate", 0);

		ViewHolder viewHolder;

		LayoutInflater inflater = activity.getLayoutInflater();

		if(convertView==null)
		{

			viewHolder = new ViewHolder();

			convertView = inflater.inflate(R.layout.test_grid_item, null);

			viewHolder.txt = (TextView) convertView.findViewById(R.id.txt);

			convertView.setTag(viewHolder);

		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		
		if(position == abcStringList.size()/2){
			convertView.setVisibility(View.INVISIBLE);
		}
		else{
			convertView.setVisibility(View.VISIBLE);
		}
		
		OnClickListener alertOnClickListener = new OnClickListener() {

			public void onClick(View v) 
			{

				Toast.makeText(activity.getApplicationContext(), abcStringList.get(position)+" - Clicked ", Toast.LENGTH_SHORT).show();

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

				if(position%2==0)
					selectDateFragment.show(activity.getSupportFragmentManager(), "DatePicker");       
				else
					new TimelineSettings().show(activity.getSupportFragmentManager(), "Timeline");
				
			}

		};

		viewHolder.txt.setOnClickListener(alertOnClickListener);

		text = String.valueOf(abcStringList.get(position));

		viewHolder.txt.setBackgroundResource(R.drawable.background_action_bar);

		viewHolder.txt.setTextSize(18);

		viewHolder.txt.setTextColor(Color.BLACK);

		viewHolder.txt.setGravity(Gravity.CENTER);    

		viewHolder.txt.setText(text);

		return convertView;

	}

	public class TimelineSettings extends DialogFragment {
	    ArrayList<String> selected_categories = new ArrayList<String>();
	    final String[]items = {"Fourniture","Nourriture","Voyages","Habillement","Médias","Autres"};
	    boolean[] itemsChecked = {false, false, false, false, false, false};
	    TinyDB tinydb;
	    private SharedPreferences sharedPreference;
	    private SharedPreferences.Editor sharedPrefEditor;

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        tinydb = new TinyDB(getActivity());
	        selected_categories = tinydb.getList("selected");
	        
	        for(int i=0;i<itemsChecked.length;i++){
	        	if(selected_categories.contains((String)String.valueOf(i)))
	        			itemsChecked[i]=true;
	        }
	        
//	        selected_categories = new ArrayList<String>();
	        
	        // Set the dialog title
	        builder.setTitle("Choisissez vos paramètres")
	                .setMultiChoiceItems(items, itemsChecked,
	                        new DialogInterface.OnMultiChoiceClickListener() {
	                            @Override
	                            public void onClick(DialogInterface dialog, int indexselected,
	                                                boolean isChecked) {
	                                if (isChecked) {
	                                    // If the user checked the item, add it to the selected items
	                                	if(!selected_categories.contains((String)String.valueOf(indexselected))){
	                                		selected_categories.add(String.valueOf(indexselected));
	                                    	itemsChecked[indexselected]=true;
	                                	}
	                                } else if (selected_categories.contains((String)String.valueOf(indexselected))) {
	                                    // Else, if the item is already in the array, remove it
	                                    selected_categories.remove((String)String.valueOf(indexselected));
	                                    itemsChecked[indexselected]=false;
	                                }
	                            }
	                        })
	                        // Set the action buttons
	                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int id) {
	                    	tinydb.putList("selected",selected_categories);
	                    }
	                })
	                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int id) {
	                    }
	                });
	        return builder.create();
	    }
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