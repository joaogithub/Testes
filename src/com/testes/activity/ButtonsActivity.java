package com.testes.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testes.android.R;

public class ButtonsActivity extends ActionBarActivity{

	String buttonText= "";
	LinearLayout viewToInject;
	private int X, Y;
	private Bitmap bg;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
		//				WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.layout_buttons_activity);

		RelativeLayout rootView = (RelativeLayout) findViewById(R.id.rootRelativeLayout);

		rootView.setOnTouchListener(new View.OnTouchListener() { 
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				//gesture detector to detect swipe.
				int x = (int) event.getX();
				int y = (int) event.getY();

				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					break;

				case MotionEvent.ACTION_MOVE:
					X = x;  Y = y;
					break;

				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
			}
		});

		final Bitmap bitmap = Bitmap.createBitmap(255, 255, Bitmap.Config.ARGB_8888);

		//after that try this line (A is alpha value interval is 0-255 and 0 is fully transparent).

		bitmap.eraseColor(Color.argb(128,100,200,50));

		bg = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);

		if(Build.VERSION.SDK_INT>16){
			rootView.setBackground(new BitmapDrawable(getResources(), bg)
			{

				@Override
				public void draw(Canvas canvas) {
					canvas.drawBitmap(bitmap,X,Y, null);
				}
			});
		}

		viewToInject = (LinearLayout) findViewById(R.id.viewToInject);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener listener = new TabListener() {

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

			}

			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {

			}

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {

			}
		};

		actionBar.addTab(actionBar.newTab().setText("Primeira").setTabListener(listener));
		actionBar.addTab(actionBar.newTab().setText("Segunda").setTabListener(listener));

		appendSenderText("primeiro");
		appendReceiverText("segundo");

		//		viewToInject.addView(viewToInject);
		rootView.removeView(viewToInject);
		rootView.addView(viewToInject);
	}

	private void appendSenderText(String message) {

		TextView msg = new TextView(ButtonsActivity.this);
		msg.setBackgroundResource(R.drawable.img_rbs_login_btn_unpressed);
		msg.setText(message);
		msg.setId(1);
		msg.setPadding(10, 10, 10, 10);
		msg.setTextColor(Color.WHITE);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.setMargins(5, 15, 0, 0);
		params.gravity = Gravity.LEFT;
		msg.setLayoutParams(params);
		msg.setGravity(Gravity.CENTER);
		RelativeLayout chat = (RelativeLayout) findViewById(R.id.rootRelativeLayout);
		chat.addView(msg);  
	}

	private void appendReceiverText(String message) {

		TextView msg = new TextView(ButtonsActivity.this);
		msg.setBackgroundResource(R.drawable.img_rbs_login_btn_unpressed);
		msg.setText(message);
		msg.setPadding(10, 10, 10, 10);
		msg.setTextColor(Color.WHITE);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 15, 5, 0);
		params.gravity = Gravity.RIGHT;
		msg.setLayoutParams(params);
		msg.setGravity(Gravity.CENTER);
		RelativeLayout chat = (RelativeLayout) findViewById(R.id.rootRelativeLayout);
		chat.addView(msg);  
	}

	@Override
	protected void onPause() {
		getWindow().getDecorView().getRootView().setBackgroundColor(Color.BLUE);
		getWindow().getDecorView().getRootView().invalidate();
		super.onPause();
	}

}
