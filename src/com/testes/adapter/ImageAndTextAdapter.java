package com.testes.adapter;

import com.testes.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAndTextAdapter extends ArrayAdapter<String> {

	private LayoutInflater mInflater;

	private String[] mStrings;
	private TypedArray mIcons;

	private int mViewResourceId;

	public ImageAndTextAdapter(Context ctx, int viewResourceId,
			String[] strings, TypedArray icons) {
		super(ctx, viewResourceId, strings);

		mInflater = (LayoutInflater)ctx.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

		mStrings = strings;
		mIcons = icons;

		mViewResourceId = viewResourceId;
	}

	@Override
	public int getCount() {
		return mStrings.length;
	}

	@Override
	public String getItem(int position) {
		return mStrings[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(mViewResourceId, null);

		ImageView iv = (ImageView)convertView.findViewById(R.id.icon);
		iv.setImageDrawable(mIcons.getDrawable(position));

		TextView tv = new TextView(getContext());
		tv.setText(mStrings[position]);

		return convertView;
	}
}