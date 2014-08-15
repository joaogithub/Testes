package com.testes.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testes.android.R;

public class CanvasActivity extends ActionBarActivity{

	String buttonText= "";
	private ImageView imageView;
	LinearLayout viewToInject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.image_layout);

		imageView = (ImageView) findViewById(R.id.starterPic);
		
		Bitmap bmp = Bitmap.createBitmap(800,600,Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		Paint textPaint = new Paint();
		textPaint.setColor(Color.RED);
		textPaint.setStrokeWidth(5);
		canvas.drawLine(0, 0, 40, 40, textPaint);
		canvas.save();
		canvas.drawLine(40, 40, 80, 20, textPaint);
		canvas.restore();
		canvas.drawLine(80, 20, 180, 30, textPaint);
		//		    canvas.drawPath(new Path(), textPaint);
		imageView.setImageBitmap(bmp);


	}



}
