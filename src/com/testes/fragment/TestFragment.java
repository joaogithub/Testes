package com.testes.fragment;

import com.testes.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment{

	private View mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		mView = inflater.inflate(R.layout.fragment_test, container);
		
		return mView;
	}

	public static Fragment newInstance(boolean b) {
		return new TestFragment();
	}
	
}
