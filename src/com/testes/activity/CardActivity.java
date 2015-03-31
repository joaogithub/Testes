package com.testes.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;

import com.testes.android.R;

public class CardActivity extends FragmentActivity{

	private CardView cardView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.layout_card);
		
		cardView = (CardView) findViewById(R.id.card_view);
		
		
		cardView.setPreventCornerOverlap(false);
	}
	
}
