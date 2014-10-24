package com.testes.adapter;

import java.util.ArrayList;




import com.testes.android.R;
import com.testes.android.R.drawable;
import com.testes.android.R.id;
import com.testes.android.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridAdapter extends ArrayAdapter<String> {

	private Context _context;
	private ArrayList<String> _items = null;
	Activity _activity;
	private LayoutInflater _inflater;
	private boolean _grid;

	private static class ViewHolder {
		public RelativeLayout background;
		public TextView name;
		public TextView category;
		public ImageView img;
	}
	
	public GridAdapter(Context context, ArrayList<String> items, Activity activity) {
		
		super(context,(Integer) null, items);
		_context = context;
		_items = items;
		_activity = activity;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
	
		View item = convertView;
		if(item == null){
			
			ViewHolder viewHolder = new ViewHolder();
			
	
			//inspire grid item
				item = _inflater.inflate(R.layout.grid_item_layout, parent, false);
				viewHolder.img = (ImageView) item.findViewById(R.id.inspireItemImage);
				viewHolder.category = (TextView) item.findViewById(R.id.itemCategory);
			
			viewHolder.name = (TextView) item.findViewById(R.id.itemName);
			viewHolder.background = (RelativeLayout) item.findViewById(R.id.itemBackground);
			item.setTag(viewHolder);
			
		}

		final ViewHolder holder = (ViewHolder) item.getTag();

		if (_items.get(position)!= null)
			holder.name.setText(_items.get(position).toUpperCase());

		//item description  
		if (_items.get(position) != null && !_grid)
			holder.category.setText(_items.get(position));
		
		holder.img.setImageResource(R.drawable.img_l1_default);
		if (_items.get(position)!= null){
			//holder.img.setImageBitmap();
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