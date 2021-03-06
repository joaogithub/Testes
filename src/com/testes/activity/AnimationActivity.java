package com.testes.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testes.android.R;

public class AnimationActivity extends ActionBarActivity{

	protected static final String TAG = "AnimationActivity";
	String buttonText= "";
	Button oneBtn, twoChb, threeChb;
	LinearLayout ballLayout;
	private ImageView tennisBall;

	@SuppressLint("NewApi")
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

		ImageView img = (ImageView)findViewById(R.id.simple_anim);
		final AnimationDrawable animDrawable = (AnimationDrawable)img.getDrawable();


		new Runnable() {
			@Override
			public void run() {
				animDrawable.start();
			}
		}.run();

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

		Animation anim = AnimationUtils.loadAnimation(this, R.anim.full_rotation);
		anim.setRepeatCount(Animation.INFINITE);
		tennisBall.startAnimation(anim);
		tennisBall.animate();

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

		final ScaleAnimation scaleAnimation =
				new ScaleAnimation(1.0f, 5f, 1.0f, 5f,
						ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
						ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

		//		starScaleAnimation.set

		scaleAnimation.setDuration(9000);

		ImageView lolImageView = (ImageView) findViewById(R.id.meetImage);
		ImageView starImageView = (ImageView) findViewById(R.id.scale_anim);

		starImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ScaleAnimation starScaleAnimation =
						new ScaleAnimation(0.3f, 1f, 0.3f, 1f,
								ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
								ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

				starScaleAnimation.setDuration(500);
				((ImageView) v).setImageResource(R.drawable.star);
				ScaleAnimation scaleAnim = starScaleAnimation;
				v.startAnimation(scaleAnim);
			}
		});

		lolImageView.setImageResource(R.drawable.medal_portugues2_aula);

		lolImageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(final View v, MotionEvent event) {
				ScaleAnimation scaleAnim = scaleAnimation;
				Log.i(TAG, "x:"+event.getX() + ", y:"+ event.getY());
				startScaleAnimation(v, scaleAnim, event.getX()/v.getWidth(), event.getY()/v.getHeight());
				v.performClick();
				return true;
			}
		});

		//		lol.setAnimation(scaleAnimation);

		final ImageView imageView =(ImageView)findViewById(R.id.meetImage);
		Animation anim1 = new TranslateAnimation(0,0,300,0);
		anim1.setDuration(3000);
		anim1.setFillAfter(true);

		anim1.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				Log.i("AnimationActivity", "Animation started");
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Animation anim2 = new TranslateAnimation(0, 0, 824, 1024);
				anim2.setDuration(3000);
				anim2.setFillAfter(true);
				imageView.clearAnimation();
				//				imageView.startAnimation(anim2);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});

		//		imageView.startAnimation(anim1);

		TextView animText = (TextView) findViewById(R.id.animTextView);

		if(Build.VERSION.SDK_INT>10){
			//SCREEN ROTATE ANIMATION
			ObjectAnimator animator = ObjectAnimator.ofFloat(getWindow().getDecorView(), "rotation", 0,
					90, 180, 270, 360);
			animator.setDuration(3000);
			animator.setRepeatCount(1);
			//			animator.start();

			Animation movingJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
			//			animText.startAnimation(movingJumpAnimation);
		}

	}

	@SuppressLint("NewApi")
	public static void colorFade(View view, Resources res) {
		if(Build.VERSION.SDK_INT>10){
			ObjectAnimator colorFadeAnimator = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), res.getColor(R.color.blue), 0xffccc);
			colorFadeAnimator.setDuration(30000);
			colorFadeAnimator.start();
		}
	}

	@SuppressLint("NewApi")
	void drawBallGhost(int shadowValue){
		ImageView shadowTennisBall = new ImageView(this);
		shadowTennisBall.setImageResource(R.drawable.ball);
		shadowTennisBall.setImageAlpha(shadowValue);
		ballLayout.addView(shadowTennisBall);
	}


	static void startAnimationText(Context context){

	}

	/**
	 * stars a scale animation of 4 seconds duration over a view where that view was touched
	 * @param v
	 * @param scaleAnim
	 * @param pivotX
	 * @param pivotY
	 */
	static void startScaleAnimation(View v, ScaleAnimation scaleAnim, float pivotX, float pivotY){
		scaleAnim =
				new ScaleAnimation(1.0f, 5f, 1.0f, 5f,
						ScaleAnimation.RELATIVE_TO_SELF, pivotX,
						ScaleAnimation.RELATIVE_TO_SELF, pivotY);
		scaleAnim.setDuration(4000);

		v.startAnimation(scaleAnim);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
