package com.testes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Gravity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//				WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.layout_buttons_activity);

		RelativeLayout rootView= (RelativeLayout) findViewById(R.id.rootRelativeLayout);
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
		msg.setTextColor(getResources().getColor(R.color.white));
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
		msg.setTextColor(getResources().getColor(R.color.white));
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
