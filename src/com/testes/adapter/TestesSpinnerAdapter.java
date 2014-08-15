package com.testes.adapter;

import com.testes.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TestesSpinnerAdapter extends ArrayAdapter<String> {

	private Context context;
	private String activityTitle;
	private int resourceId;
	private int textViewResourceId;
	private String[] elements;
	
	public TestesSpinnerAdapter(Context context, String activityTitle, int resourceId, int textViewResourceId, String[] elements) {
		super(context, resourceId, textViewResourceId, elements);
		
		this.activityTitle = activityTitle;
		this.context = context;
		this.resourceId = resourceId;
		this.textViewResourceId = textViewResourceId;
		this.elements = elements;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHolder viewHolder = null;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resourceId, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
//			viewHolder.subtitle = (TextView) convertView.findViewById(R.id.subtitle);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title.setText(activityTitle);
		
		//if(activityTitle.equals("Estudos") && position==0)
		//	viewHolder.subtitle.setText("Estudos");
		//else
			viewHolder.subtitle.setText(elements[position]);

		return convertView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(textViewResourceId, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if(activityTitle.equals("Estudos") && position==0)
			viewHolder.title.setText("Estudos");
		else
			viewHolder.title.setText(elements[position]);

		return convertView;
	}
	
	private class ViewHolder {
		public TextView title;
		public TextView subtitle;
	}
}
