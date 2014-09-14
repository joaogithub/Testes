package com.testes.activity;

import com.testes.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

		new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);

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