package com.testes.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.testes.android.R;

public class AnimationActivity extends ActionBarActivity{

	String buttonText= "";
	Button oneBtn, twoChb, threeChb;
	LinearLayout ballLayout;
	private ImageView tennisBall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Animation mBounceAnimation = AnimationUtils.loadAnimation(this,R.anim.scale);
		final Animation mBounceAnimation1 = AnimationUtils.loadAnimation(this,R.anim.scale);
		final Animation mBounceAnimation2 = AnimationUtils.loadAnimation(this,R.anim.scale);
		final Animation ballAnimation = AnimationUtils.loadAnimation(this,R.anim.ball_ghost);
		
		setContentView(R.layout.activity_animation);

		oneBtn = (Button) findViewById(R.id.buttonAct);
		twoChb = (Button) findViewById(R.id.twoChb);
		threeChb = (Button) findViewById(R.id.threeChb);
		tennisBall = (ImageView) findViewById(R.id.ball);
		ballLayout = (LinearLayout) findViewById(R.id.tennisBallLayout);
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
		
		tennisBall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.startAnimation(ballAnimation);
				
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						drawBallGhost(2);
						
					}
					
				}, 200);
				
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						drawBallGhost(1);
						
					}
					
				}, 200);
				
			}
		});
		
	}


	@SuppressLint("NewApi")
	void drawBallGhost(int shadowValue){
		ImageView shadowTennisBall = new ImageView(this);
		shadowTennisBall.setImageResource(R.drawable.ball);
		shadowTennisBall.setImageAlpha(shadowValue);
		ballLayout.addView(shadowTennisBall);
	}
	

	@Override
	protected void onPause() {
		super.onPause();
	}
	
}
