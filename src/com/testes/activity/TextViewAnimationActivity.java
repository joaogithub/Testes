package com.testes.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.testes.android.R;

public class TextViewAnimationActivity extends ActionBarActivity{

	private TextView story_txt;
	private Animation animationSlideDownIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_cast);

		story_txt = (TextView) findViewById(R.id.story_txt);

		Typeface face = Typeface
				.createFromAsset(getAssets(), "fonts/secret.ttf");

		story_txt.setTypeface(face);

		animationSlideDownIn = AnimationUtils.loadAnimation(this,
				R.anim.slide_up);

		animationSlideDownIn.setAnimationListener(animationSlideInListener);

		story_txt.startAnimation(animationSlideDownIn);

	}

	AnimationListener animationSlideInListener = new AnimationListener() {

		@Override
		public void onAnimationEnd(Animation arg0) {
			story_txt.setVisibility(View.INVISIBLE);
			finish();
		}

		@Override
		public void onAnimationStart(Animation animation) {
			
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			
			
		}

	};

}
