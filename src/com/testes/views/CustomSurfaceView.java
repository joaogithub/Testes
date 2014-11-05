package com.testes.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView implements Runnable {

	@SuppressLint("NewApi")
	public CustomSurfaceView(Context context) {
		super(context);
		Matrix matrix = getMatrix();
		
		matrix.postRotate(40);
		
//		RectF drawableRect = new RectF(0, 0, Background.getWidth(), Background.getHeight());
//		RectF viewRect = new RectF(0, 0, screenWidth, screenHeight);
//		Log.d("tag", drawableRect.toString());
//		Log.d("tag", viewRect.toString());
//		matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
	}

	@Override
	public void run() {
		
	}

}
