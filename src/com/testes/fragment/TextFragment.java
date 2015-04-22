package com.testes.fragment;

import com.testes.activity.ViewPagerActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextFragment extends Fragment{

	TextView _textView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		LinearLayout _rootView = new LinearLayout(getActivity());
		_textView = new TextView(getActivity());
		_textView.setBackgroundColor(Color.MAGENTA);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		_textView.setLayoutParams(lp);
		_textView.setText("My TextView in pager");
		_rootView.addView(_textView);

		return _rootView;
	}

}
