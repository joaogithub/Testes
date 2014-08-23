package com.testes.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.testes.android.R;

public class AnimationActivity extends ActionBarActivity{

	String buttonText= "";
	Button oneBtn, twoChb, threeChb;
	LinearLayout viewToInject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Animation mBounceAnimation = AnimationUtils.loadAnimation(this,R.anim.scale);
		final Animation mBounceAnimation1 = AnimationUtils.loadAnimation(this,R.anim.scale);
		final Animation mBounceAnimation2 = AnimationUtils.loadAnimation(this,R.anim.scale);
		
		setContentView(R.layout.activity_animation);

		oneBtn = (Button) findViewById(R.id.buttonAct);
		twoChb = (Button) findViewById(R.id.twoChb);
		threeChb = (Button) findViewById(R.id.threeChb);
	
		oneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				v.startAnimation(mBounceAnimation);
				
			}
		});
		
		twoChb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.startAnimation(mBounceAnimation1);
				
			}
		});
		
		threeChb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.startAnimation(mBounceAnimation2);
				
			}
		});
		
	}



	@Override
	protected void onPause() {
		super.onPause();
	}
	
}
