package com.testes.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.testes.android.R;

public class IntentsActivity extends ActionBarActivity {

	String buttonText= "";
	ImageView startImage;
	Button send, search, capture, setasButton, viewButton, pickButton, callButton, getContentButton;
	LinearLayout viewToInject;
	String imageUri;

	private static final int PICK_REQUEST_CODE = 100;
	private static final int CALL_REQUEST_CODE = 200;
	private static final int CAPTURE_REQUEST_CODE = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_buttons_activity);

		send = (Button) findViewById(R.id.sendIntentButton);
		capture = (Button) findViewById(R.id.captureIntentButton);
		pickButton = (Button) findViewById(R.id.pickIntentButton);
		setasButton = (Button) findViewById(R.id.setAsButton);
		callButton = (Button) findViewById(R.id.callButton);
		getContentButton = (Button) findViewById(R.id.getContnetIntentButton);
		viewButton = (Button) findViewById(R.id.viewButton);
		startImage = (ImageView) findViewById(R.id.previewImage);

		capture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent capturingIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(capturingIntent, CAPTURE_REQUEST_CODE);
			}
		});

		callButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+"214075478"));  
				startActivityForResult(callIntent, CALL_REQUEST_CODE);
			}
		});
		
		getContentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_GET_CONTENT);
				startActivity(i);
			}
		});
		
		pickButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, PICK_REQUEST_CODE);
			}
		});

		setasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				//		        intent.setDataAndType(Uri.parse("android.resource://"+getPackageName()+"/"+ R.drawable.ic_launcher), "image/*");
				intent.setDataAndType(Uri.parse(imageUri), "image/*");
				intent.putExtra("mimeType", "image/*");
				startActivity(Intent.createChooser(intent, "View:"));

			}
		});

		setasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.setDataAndType(Uri.parse("android.resource://"+getPackageName()+"/"+ R.drawable.ic_launcher), "image/*");
				Log.i("URI", ""+Uri.parse("android.resource://com.testes.android/drawable/ic_launcher").toString() + getPackageName());
				//		        intent.setDataAndType(Uri.parse(imageUri), "image/jpeg");
				intent.putExtra("mimeType", "image/*");
				startActivity(Intent.createChooser(intent, "Set as:"));

			}
		});


		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				Uri screenshotUri = null;
				if(imageUri!=null){
					screenshotUri = Uri.parse(imageUri);
					sharingIntent.setType("image/jpeg");
					sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
				}
				else
				{
					screenshotUri = Uri.parse("market://details?id=com.android.chrome");
					sharingIntent.setType("text/plain");
//					sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
					sharingIntent.putExtra(Intent.EXTRA_TEXT, "market://details?id=com.android.chrome");
					if(Build.VERSION.SDK_INT>15)
						sharingIntent.putExtra(Intent.EXTRA_HTML_TEXT, "market://details?id=com.android.chrome");
				}

				startActivity(Intent.createChooser(sharingIntent, "Share image using"));

			}
		});

		//		Matrix matrix = getMatrix();
		//
		//	    RectF drawableRect = new RectF(0, 0, Background.getWidth(), Background.getHeight());
		//	    RectF viewRect = new RectF(0, 0, screenWidth, screenHeight);
		//	    Log.d("tag", drawableRect.toString());
		//	    Log.d("tag", viewRect.toString());
		//
		//	    matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);

	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode ==CAPTURE_REQUEST_CODE && resultCode ==Activity.RESULT_OK){
			ExifInterface exif;
			int rotate = 0;
			Bitmap bitmap =(Bitmap) data.getExtras().get("data"); 
			try 
			{
				imageUri = data.getData().toString();

				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				Log.i("COMPRESSED Bitmap", " "+bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes));

				try {
					File directory = new File(Environment.getExternalStorageDirectory() + "/myDirectory/");
					Log.i("Created external folder successfully", " "+directory.mkdirs());
					File f = new File(Environment.getExternalStorageDirectory() + "/myDirectory/" + "test.png");
					Log.i("Created external file successfully", " "+f.createNewFile() + " file exists "+ f.exists());
					if(f.exists()){
						FileOutputStream fo = new FileOutputStream(f);
						fo.write(bytes.toByteArray());
						fo.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("MyActivity", "Was unable to copy image" + e.toString());
				}

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
				Log.i("COMPRESSED", " " +bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream));
				byte[] byteArray = stream.toByteArray();
				Log.i("byte array length", ""+byteArray.length);

				File file = new File(getFilesDir()+"/file.png");
				FileOutputStream fos = new FileOutputStream(file);

				fos.write(byteArray);
				fos.flush();
				fos.close();

				Log.i("Intents", imageUri);
				exif = new ExifInterface(imageUri);
				int exifOrientation = exif.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);

				switch (exifOrientation) 
				{
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;

				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;

				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
				}

				int width = bitmap.getWidth();
				int height = bitmap.getHeight();
				Matrix mtx = new Matrix();
				try
				{
					Log.i("mutable bitmap?" ,""+ bitmap.isMutable() + " w: "+width + " h: "+ height);
					bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);
					//							Bitmap.createBitmap(bitmap, 40, 40, width-40, height-40, null, false);
					startImage.setImageBitmap(bitmap);

					Log.i("mutable bitmap after?" ,""+ bitmap.isMutable() + " w: "+width + " h: "+ height);
				}
				catch(OutOfMemoryError e)
				{
					e.printStackTrace();

				}

				if (rotate != 0) 
				{
					width = bitmap.getWidth();
					height = bitmap.getHeight();

					Log.i("Intents", "rotating "+ rotate + " degrees ");

					// Setting pre rotate
					mtx = new Matrix();
					//					mtx.preRotate(rotate);

					// Rotating Bitmap & convert to ARGB_8888, required by tess
					try
					{
						bitmap = Bitmap.createBitmap(bitmap, 40, 40, width-40, height-40, mtx, false);
						startImage.setImageBitmap(bitmap);
					}
					catch(OutOfMemoryError e)
					{
						e.printStackTrace();

					}

				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}

		else if(requestCode == PICK_REQUEST_CODE && resultCode==Activity.RESULT_OK){

			Bitmap bitmap =null;
			if(data.getExtras()!=null)
				bitmap = (Bitmap) data.getExtras().get("data"); 
			try 
			{
				imageUri = data.getData().toString();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(requestCode == CALL_REQUEST_CODE && resultCode==Activity.RESULT_OK){
			Log.i("IntentsActivity", data.toString());
		}
		else{
			Log.i("IntentsActivity", "ACTION FAILED");
		}
	}
}
