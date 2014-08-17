package com.testes.activity;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.testes.android.R;

public class SeekBarActivity extends ActionBarActivity {

	String buttonText= "";
	ImageView startImage;
	Button send, search, capture;
	LinearLayout viewToInject;
	SeekBar sbPower;
	RelativeLayout powerContainer;
	String imageUri;
	Rect delegateArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_seekbar);

		powerContainer = (RelativeLayout) findViewById(R.id.powerContainer);
		TextView animText = (TextView) findViewById(R.id.animTextView);

		delegateArea = new Rect();
		sbPower = (SeekBar) findViewById(R.id.seekBar);
		//		sbPower.getHitRect(delegateArea);

		ObjectAnimator animator = ObjectAnimator.ofFloat(getWindow().getDecorView(), "rotation", 0,
				90, 180, 270, 360);
		animator.setDuration(3000);
		animator.setRepeatCount(1);
//		animator.start();

		Animation movingJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
		//		animText.startAnimation(movingJumpAnimation);

		Log.i("Rect TOP",String.valueOf(delegateArea.top));
		Log.i("Rect RIGHT",String.valueOf(delegateArea.right));
		Log.i("Rect BOTTOM",String.valueOf(delegateArea.bottom));
		Log.i("Rect LEFT",String.valueOf(delegateArea.left));

		int mWidth = powerContainer.getWidth();
		Log.i("powerContainer",String.valueOf((mWidth * .7f)));
		float wWidth = ((mWidth * .7f) > 756 ) ? 756 : mWidth * .7f;
		ViewGroup.LayoutParams params = sbPower.getLayoutParams();
		params.width = (int) wWidth;
		//		sbPower.setLayoutParams(params);
		//		sbPower.setThumbOffset(0);

		sbPower.getHitRect(delegateArea);

		Log.i("Rect TOP",String.valueOf(delegateArea.top));
		Log.i("Rect RIGHT",String.valueOf(delegateArea.right));
		Log.i("Rect BOTTOM",String.valueOf(delegateArea.bottom));
		Log.i("Rect LEFT",String.valueOf(delegateArea.left));


	}

	@Override
	protected void onResume() {
		super.onResume();
		int mWidth = powerContainer.getWidth();
		Log.i("powerContainer onresume",String.valueOf((mWidth * .7f)));
		float wWidth = ((mWidth * .7f) > 756 ) ? 756 : mWidth * .7f;
		ViewGroup.LayoutParams params = sbPower.getLayoutParams();
		params.width = (int) wWidth;
		sbPower.setLayoutParams(params);
		//		sbPower.setThumbOffset(0);

		sbPower.getHitRect(delegateArea);

		Log.i("Rect TOP resume",String.valueOf(delegateArea.top));
		Log.i("Rect RIGHT resume",String.valueOf(delegateArea.right));
		Log.i("Rect BOTTOM resume",String.valueOf(delegateArea.bottom));
		Log.i("Rect LEFT resume",String.valueOf(delegateArea.left));
	}

}
