package com.testes.activity;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.testes.android.R;

public class ScrollViewActivity extends Activity {

	TextView timervalue;
	ImageView mImageArrowBack;
	TextView mTitle;
	ImageButton close;
	boolean visible = false;
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	String secondHalf;
	ExecutorService threadPoolExecutor;
	private long startTime = 0L;
	private Handler customHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_textview);

		mImageArrowBack = (ImageView) findViewById(R.id.fragment_back);
		mTitle = (TextView) findViewById(R.id.fragment_title);
		close = (ImageButton) findViewById(R.id.fragment_close);
		timervalue = (TextView) findViewById(R.id.timerValue);
		secondHalf = null;
		Button startButton;
		startButton = (Button) findViewById(R.id.startButton);

		Button pause;
		threadPoolExecutor = Executors.newSingleThreadExecutor();

		pause = (Button) findViewById(R.id.button1);
		pause.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(firstHalfRunnable);
			}

		});

			
		
		startButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(firstHalfRunnable, 0);
			}

		});

		if(getIntent().getExtras()!=null){
			mTitle.setTypeface(Typeface.DEFAULT_BOLD);
			mTitle.setTextColor(Color.parseColor(getIntent().getExtras().getString("color")));
		}

		mImageArrowBack.setVisibility(View.GONE);
		//		setBackButton(false);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!visible){
					mImageArrowBack.setVisibility(View.VISIBLE);
					visible = true;
				}
				else{
					mImageArrowBack.setVisibility(View.GONE);
					visible =false;
				}

			}
		});

	}

	private void setBackButton(boolean visible) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTitle.getLayoutParams();
		if (visible) {
			mImageArrowBack.setVisibility(View.VISIBLE);
			params.addRule(RelativeLayout.RIGHT_OF, R.id.fragment_back);
		} else {
			mImageArrowBack.setVisibility(View.GONE);
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		}
		params.addRule(RelativeLayout.LEFT_OF, R.id.fragment_close);
		mTitle.setLayoutParams(params);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(customHandler!=null)
			customHandler.removeCallbacks(firstHalfRunnable);
	}
	
	private Runnable firstHalfRunnable = new Runnable() {
		public void run() {
			Future longRunningTaskFurure = threadPoolExecutor.submit(firstHalfRunnable);
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			Log.i("ScrollViewActivity", updatedTime + " cast secs: " + (int) (updatedTime / 1000));
			int secs = (int) (updatedTime / 1000);
			//    int mins = secs / 60;

			secs = secs % 91;
			Log.i("ScrollViewActivity", "Secs: "+ secs);
			int milliseconds = (int) (updatedTime % 1000);

			timervalue.setText("minuto" + ":"
					+ String.format("%02d", secs)); //+ ":"

			//   + String.format("%03d", milliseconds));

			customHandler.postDelayed(this, 0);
			if ( secs == 10 ) {
				secondHalf = "X";
				timeSwapBuff += timeInMilliseconds;
				longRunningTaskFurure.cancel(true);
			}
		}


	};

}
