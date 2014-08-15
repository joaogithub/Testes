package com.testes.adapter;


import com.testes.android.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DrawerItemAdapter extends ArrayAdapter<String>
{
	Context context;
	String[] mTitles;
	Integer[] mImages;
	int selectedIndex;
	int resourceId;

	public DrawerItemAdapter(Context context, int layoutResourceId, String[] titleData, Integer[] drawableData)
	{
		super(context, layoutResourceId);

		this.context = context;
		this.mImages = drawableData;
		this.mTitles = titleData;
		this.resourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;

		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resourceId, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.drawerItemImageView);
			viewHolder.itemSelected = (View) convertView.findViewById(R.id.menuSelectedView);
			viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.drawerItemTitleView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.itemImage.setImageResource(mImages[position]);
		viewHolder.itemTitle.setText(mTitles[position]);

		if (((ListView) parent).getCheckedItemPosition() == position)
		{
			viewHolder.itemSelected.setVisibility(View.VISIBLE);

			viewHolder.itemImage.setImageResource(mImages[position]);
			viewHolder.itemTitle.setTextColor(Color.WHITE);
		}
		// item is not selected, reset to normal state
		else
		{
			viewHolder.itemSelected.setVisibility(View.INVISIBLE);

			viewHolder.itemImage.setImageResource(mImages[position]);
			viewHolder.itemTitle.setTextColor(Color.WHITE);
		}

		return convertView;
	}

	@Override
	public int getCount()
	{
		return mTitles.length;
	}

	@Override
	public String getItem(int position)
	{
		return mTitles[position];
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	private class ViewHolder
	{
		public View itemSelected;
		public ImageView itemImage;
		public TextView itemTitle;
	}
}
