package com.testes.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView implements Runnable {

	@SuppressLint("NewApi")
	public CustomSurfaceView(Context context) {
		super(context);
		Matrix matrix = getMatrix();
		
		matrix.postRotate(40);
	}

	@Override
	public void run() {
		
	}

}
