package com.testes.fragment;

import com.testes.activity.ViewPagerActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextFragment extends Fragment{

	EditText _editView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


		_editView =  new EditText(getActivity());
		_editView.setHint("MyEdittext");
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
