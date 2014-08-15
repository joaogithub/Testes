package com.testes.activity;

import java.util.ArrayList;
import java.util.Hashtable;

import com.testes.android.R;
import com.testes.android.R.id;
import com.testes.android.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SpinnerActivity extends Activity{

	Spinner spinnerRegion, spinnerDistrict;
	int selectionCount=0;
	Hashtable<Integer,ArrayList<String>> spinnerValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_spinner_activity);

		ArrayList<String> regions = new ArrayList<String>();
		regions.add("Analamanga");
		regions.add("Itasy");
		regions.add("ThirdRegion");

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

		spinnerRegion = (Spinner) findViewById(R.id.spinner_region);
		if(spinnerRegion != null) {

			FilterSpinnerAdapter regionadapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, regions);

			regionadapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
			spinnerRegion.setAdapter(regionadapter);
			spinnerRegion.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
						FilterSpinnerAdapter newDistrictAdapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, spinnerValues.get(position));
						newDistrictAdapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
						spinnerDistrict.setAdapter(newDistrictAdapter);			
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) { }
			});

		}

		spinnerDistrict = (Spinner) findViewById(R.id.spinner_district);
		if(spinnerDistrict != null) {

			FilterSpinnerAdapter districtadapter = new FilterSpinnerAdapter(getApplicationContext(), R.layout.movie_detail_spinner_item, analamangaDistricts);

			districtadapter.setDropDownViewResource(R.layout.layout_simple_spinner_dropdown_item);
			spinnerDistrict.setAdapter(districtadapter);
			spinnerDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
					//do whatever you want here
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) { }
			});

		}
		
		spinnerRegion.setSelection(1);
		
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
}

