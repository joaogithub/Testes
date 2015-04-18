package com.testes.fragment;

import com.testes.activity.ViewPagerActivity;
import com.testes.android.R;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImageFragment extends Fragment{

	RelativeLayout _view;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		_view = new RelativeLayout(getActivity());
		_view.setBackgroundColor(Color.GRAY);
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(R.drawable.ic_launcher);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		imageView.setLayoutParams(params);
		_view.addView(imageView);
		return _view;
	}

}
