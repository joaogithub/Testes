package com.testes.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testes.android.R;


public class NormalAdapter extends ArrayAdapter<String> {

	private Context _context;
	private ArrayList<String> _items = null;
	private LayoutInflater _inflater;
	private boolean _grid;

	private static class ViewHolder {
		public LinearLayout background;
		public TextView name;
		public ImageView img;
	}

	public NormalAdapter(Context context, ArrayList<String> items) {

		super(context,R.layout.layout_exam_list_item, items);
		_context = context;
		_items = items;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View item = convertView;
		if(item == null){

			ViewHolder viewHolder = new ViewHolder();

			//inspire exam item
			item = _inflater.inflate(R.layout.layout_exam_list_item, parent, false);
			viewHolder.img = (ImageView) item.findViewById(R.id.listImageView);

			viewHolder.name = (TextView) item.findViewById(R.id.examTitle);
			viewHolder.background = (LinearLayout) item.findViewById(R.id.listItemBackground);
			item.setTag(viewHolder);

		}

		final ViewHolder holder = (ViewHolder) item.getTag();

		if (_items.get(position)!= null)
			holder.name.setText(_items.get(position).toUpperCase());

		holder.img.setImageResource(R.drawable.img_l1_default);
		if (_items.get(position)!= null){
			
		}

		holder.background.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		return item;
	}

	@Override
	public int getCount() {
		return _items.size();
	}

	@Override
	public String getItem(int pos) {
		return _items.get(pos);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}