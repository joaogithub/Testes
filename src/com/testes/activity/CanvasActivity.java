package com.testes.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.testes.android.R;

public class CanvasActivity extends ActionBarActivity{

	String buttonText= "";
	private ImageView imageView;

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

	new MyAsyncTask().execute();

	}

public class MyAsyncTask extends AsyncTask<Void, Void, Void>{

	@Override
	protected Void doInBackground(Void... params) {
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		   AlertDialog.Builder builder = new AlertDialog.Builder(CanvasActivity.this);
           LayoutInflater inflater = getLayoutInflater();
           View imageLayoutView = inflater.inflate(R.layout.image_layout, null);
           ImageView image = (ImageView) imageLayoutView.findViewById(R.id.starterPic);
           Uri uri = Uri.parse("content://media/external/images/media/1602");

           image.setImageURI(uri);
           
           builder.setView(imageLayoutView)
              .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                         dialog.cancel();
                  }
           });

           builder.create();
           builder.show();
		
	}
	
}

}
