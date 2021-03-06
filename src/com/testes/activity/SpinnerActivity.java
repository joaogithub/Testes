package com.testes.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import com.testes.android.R;
import com.testes.android.R.id;
import com.testes.android.R.layout;
import android.annotation.SuppressLint;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class SpinnerActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.OnNavigationListener{

	Spinner regionSpinner, districtSpinner, equipmentSelectorSpinner;
	int selectionCount=0;
	Hashtable<Integer,ArrayList<String>> spinnerValues;
	private SpinnerAdapter mSpinnerAdapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_spinner_activity);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		ArrayList<String > itemsFilter = new ArrayList<String>();
		itemsFilter.add("First item");
		itemsFilter.add("Second item");
		itemsFilter.add("Third Item");
		
//		getSupportActionBar().setListNavigationCallbacks(new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, itemsFilter),this);
		
		mSpinnerAdapter = ArrayAdapter.createFromResource(this,
		        R.array.icons, android.R.layout.simple_spinner_dropdown_item);
		
		getSupportActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
		
		ArrayList<String> regions = new ArrayList<String>();
		regions.add("Analamanga");
		regions.add("Itasy");
		regions.add("ThirdRegion");

		ArrayList<String> equipmentList = new ArrayList<String>();
		equipmentList.add("90");
		equipmentList.add("91");
		equipmentList.add("92");
		equipmentList.add("93");
		
		ArrayList<String> analamangaDistricts = new ArrayList<String>();
		analamangaDistricts.add("Boeny");

		ArrayList<String> itasyDistricts = new ArrayList<String>();
		itasyDistricts.add("Central");

		ArrayList<String> thirdRegionDistricts = new ArrayList<String>();
		thirdRegionDistricts.add("District1");
		thirdRegionDistricts.add("District2");
		thirdRegionDistricts.add("District3");

		spinnerValues = new Hashtable<Integer, ArrayList<String>>();

		spinnerValues.put(0, analamangaDistricts);
		spinnerValues.put(1, itasyDistricts);
		spinnerValues.put(2, thirdRegionDistricts);

		regionSpinner = (Spinner) findViewById(R.id.spinner_region);
		if(regionSpinner != null) {

			FilterSpinnerAdapter regionAdapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, regions);

			regionAdapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
			regionSpinner.setAdapter(regionAdapter);
			regionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
						FilterSpinnerAdapter newDistrictAdapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, spinnerValues.get(position));
						newDistrictAdapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
						((TextView)view).setTextColor(Color.BLUE);
						districtSpinner.setAdapter(newDistrictAdapter);			
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) { }
			});

		}

		districtSpinner = (Spinner) findViewById(R.id.spinner_district);
		if(districtSpinner != null) {

			FilterSpinnerAdapter districtadapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, analamangaDistricts);

			districtadapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
			districtSpinner.setAdapter(districtadapter);
			districtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
					//do whatever you want here
					if(view!=null)
						((TextView)view).setTextColor(Color.MAGENTA);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) { }
				public boolean onNavigationItemSelected(int itemPosition,
						long itemId) {
					return false;
				}
			});

		}
		
		regionSpinner.setSelection(1);
		
		equipmentSelectorSpinner = (Spinner) findViewById(R.id.equipment_selector);
		FilterSpinnerAdapter equipmentAdapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, equipmentList);
		equipmentSelectorSpinner.setAdapter(equipmentAdapter);
		equipmentSelectorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
				//do whatever you want here
				if(view!=null)
					((TextView)view).setTextColor(Color.LTGRAY);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
			public boolean onNavigationItemSelected(int itemPosition,
					long itemId) {
				return false;
			}
		});
		
		//spinner with code
		Spinner viewSpinner = new Spinner(this, Spinner.MODE_DROPDOWN);
		viewSpinner.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		viewSpinner.setAdapter(equipmentAdapter);
		viewSpinner.setPrompt("Title");
//		view.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.combo_box_bg));
		viewSpinner.setDropDownWidth(android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		viewSpinner.setDropDownVerticalOffset(getResources().getDimensionPixelOffset(R.dimen.form_screen_equipment_combo_box_dropdown_vertical_offset));
		viewSpinner.setBackground(getResources().getDrawable(R.drawable.combo_box_bg));
		addContentView(viewSpinner, viewSpinner.getLayoutParams());
		
	}

	
	public class FilterSpinnerAdapter extends ArrayAdapter<String> implements SpinnerAdapter  {
		public FilterSpinnerAdapter(Context context, int resource, ArrayList<String> ys) {
			super(context, resource, ys);
		}

		@Override
		public int getCount() {
			// - 1 so that the hint (last item) isn't shown
			return super.getCount();
		}

		@Override
		public String getItem(int position) {
			return super.getItem(position);
		}

		@Override
		public long getItemId(int position) {
			return super.getItemId(position);
		}

	}

	
	
	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		return false;
	}
	
}

