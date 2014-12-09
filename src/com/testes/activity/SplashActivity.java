package com.testes.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.testes.android.R;

public class SplashActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("SplashActivity","onCreate()");
		setContentView(R.layout.blow_layout);
		final Intent myAct = new Intent(this, FirstActivity.class);
		//BLOCKS UI THREAD
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Log.i("SplashActivity","sleeped");
//		startActivity(myAct);
//		finish();

		ImageView imageView = new ImageView(this);
		imageView.setId(132131311);
		imageView.setBackgroundResource(R.drawable.ball);
		imageView.setImageResource(R.drawable.zoom_in);
		imageView.setScaleType(ScaleType.CENTER);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		addContentView(imageView, params);
		
		int i = 2;
		int c = i+3;
		i=4;
		Log.i("Splash activity referne:", ""+c);
		
		new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		Log.i("SplashActivity","sleeped");
                startActivity(myAct);
                finish();
            }

           }.start();
		
	}
}