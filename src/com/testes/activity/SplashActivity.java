package com.testes.activity;

import com.testes.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blow_layout);
		final Intent myAct = new Intent(this, FirstActivity.class);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		startActivity(myAct);
		finish();

	}
}