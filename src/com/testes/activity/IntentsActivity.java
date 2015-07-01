package com.testes.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.RingtoneManager;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts.Phones;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.testes.android.R;

public class IntentsActivity extends ActionBarActivity {

	String buttonText = "";
	ImageView startImage;
	Button send, search, capture, setasButton, viewButton, mapsButton,
			openMediPlayerButton, playFileButton, browserButton, pickButton,
			callButton, getContentButton, ringToneButton, settingsButton;
	String imageUri;
	public static final String TAG = "IntentsActivity";

	private static final int PICK_REQUEST_CODE = 100;
	private static final int CALL_REQUEST_CODE = 200;
	private static final int CAPTURE_REQUEST_CODE = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_intents);

		Intent sendIntent = getIntent();

		if (sendIntent != null) {
			Log.i(TAG, sendIntent.toString());
			Uri uri = (Uri) sendIntent.getExtras().get(Intent.EXTRA_STREAM);

			if (uri != null)
				Log.i(TAG, "stream:" + uri.toString());

			ContentResolver cr = getContentResolver();
			InputStream stream = null;
			try {
			    stream = cr.openInputStream(uri);
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			}

			StringBuffer fileContent = new StringBuffer("");
			int ch;
			try {
			    while( (ch = stream.read()) != -1)
			      fileContent.append((char)ch);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			String data = new String(fileContent);
			Log.i("TAG", "contact vcf data: " + data);
			
			Bundle bundle = sendIntent.getExtras();

			//list all extras in the intent
			if (bundle != null) {
				for (String key : bundle.keySet()) {
					Object value = bundle.get(key);
					Log.i(TAG, String.format("%s %s (%s)", key,
							value.toString(), value.getClass().getName()));
				}
			}
		}

		send = (Button) findViewById(R.id.sendIntentButton);
		capture = (Button) findViewById(R.id.captureIntentButton);
		pickButton = (Button) findViewById(R.id.pickIntentButton);
		setasButton = (Button) findViewById(R.id.setAsButton);
		ringToneButton = (Button) findViewById(R.id.getRingTonesButton);
		callButton = (Button) findViewById(R.id.callButton);
		openMediPlayerButton = (Button) findViewById(R.id.playButton);
		playFileButton = (Button) findViewById(R.id.playFileButton);
		settingsButton = (Button) findViewById(R.id.settingsButton);
		mapsButton = (Button) findViewById(R.id.mapsButton);
		browserButton = (Button) findViewById(R.id.webButton);
		getContentButton = (Button) findViewById(R.id.getContentIntentButton);
		viewButton = (Button) findViewById(R.id.viewButton);
		startImage = (ImageView) findViewById(R.id.previewImage);

		capture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent capturingIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsoluteFile() + "/" + "231" + ".jpg");
				capturingIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(file));

				startActivityForResult(capturingIntent, CAPTURE_REQUEST_CODE);
			}
		});

		settingsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
				// startActivity(new Intent(Settings.ACTION_WIFI_IP_SETTINGS));
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});

		ringToneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ringtone intent
				Intent intent = new Intent(
						RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
						RingtoneManager.TYPE_ALL);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
						"Select Tone");
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
						RingtoneManager.getActualDefaultRingtoneUri(
								getApplicationContext(),
								RingtoneManager.TYPE_ALARM));
				startActivity(intent);
			}
		});

		/**
		 * call home
		 */
		callButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + "214075478"));
				startActivityForResult(callIntent, CALL_REQUEST_CODE);
			}
		});

		openMediPlayerButton.setOnClickListener(new OnClickListener() {

			@SuppressLint("InlinedApi")
			@Override
			public void onClick(View v) {
				Intent musicIntent = new Intent(Intent.ACTION_MAIN);
				// Intent intent = new
				// Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
				musicIntent.addCategory(Intent.CATEGORY_APP_MUSIC);
				// File musicFile = new
				// File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"muse - survival.mp3");
				// musicIntent.setDataAndType(Uri.fromFile(musicFile),
				// "audio/*");
				startActivity(musicIntent);
			}
		});

		playFileButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// play audio file from sdcard
				Intent playFileIntent = new Intent(Intent.ACTION_VIEW);
				File musicFile = new File(
						getExternalFilesDir(Environment.DIRECTORY_MUSIC),
						"muse - survival.mp3");
				playFileIntent.setDataAndType(Uri.fromFile(musicFile),
						"audio/*");
				startActivity(playFileIntent);
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
				Intent pickIntent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(pickIntent, PICK_REQUEST_CODE);
			}
		});

		browserButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://autodatapt.com/autodata_online.php"));
				// https://www.autodata-online.net/online/login/autodatalogon.aspx
				startActivity(browserIntent);

			}
		});

		mapsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent mapIntent = new Intent(Intent.ACTION_VIEW,
				// Uri.parse("geo:37.423156,-122.084917?q=37.423156,-122.084917(Label+Name)"));

				String uriBegin = "geo:" + "47.531605" + "," + "21.627312";
				String query = "47.531605" + "," + "21.627312" + "(DeviceId"
						+ ")";

				String encodedQuery = Uri.encode(query);
				String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
				Uri uri = Uri.parse(uriString);
				Intent viewIntent = new Intent(
						android.content.Intent.ACTION_VIEW, uri);

				viewIntent.putExtra("DeviceId", "DeviceId");

				// startActivity(intent);

				String DESTINATION_LOCATION = "Label+Name";
				String latit = "37.925942";
				String longit = "-122.084917";
				Intent mapIntent = new Intent(
						android.content.Intent.ACTION_VIEW, Uri.parse("geo:<"
								+ latit + ">,<" + longit + ">?q=<" + latit
								+ ">,<" + longit + ">(" + DESTINATION_LOCATION
								+ ")")); // name the label
				startActivity(mapIntent);

			}
		});

		viewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				// intent.addCategory(Intent.CATEGORY_DEFAULT);
				// intent.setDataAndType(Uri.parse("android.resource://"+getPackageName()+"/"+
				// R.drawable.ic_launcher), "image/*");
				intent.setDataAndType(Uri.parse(imageUri), "image/*");

				intent.putExtra("mimeType", "image/*");
				startActivity(Intent.createChooser(intent, "View/Play:"));

				// GPS coordinates

				// String CURRENT_LOCATION = "37.967775, 23.720689";
				// String DESTINATION_LOCATION = "37.925942, 23.938683";
				//
				// Intent intent = new
				// Intent(android.content.Intent.ACTION_VIEW,
				// Uri.parse("http://maps.google.com/maps?saddr="+
				// CURRENT_LOCATION +"&daddr="+CURRENT_LOCATION)); //Added
				// ampersand
				// startActivity(intent);

				// String DESTINATION_LOCATION = "37.925942,  23.938683";

				// GPS location
				// Uri.parse("http://maps.google.com/maps?q="+DESTINATION_LOCATION));
				// startActivity(intent);

			}
		});

		setasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.setDataAndType(
						Uri.parse("android.resource://" + getPackageName()
								+ "/" + R.drawable.ic_launcher), "image/*");
				Log.i(TAG,
						"URI"
								+ Uri.parse(
										"android.resource://com.testes.android/drawable/ic_launcher")
										.toString() + getPackageName());
				// intent.setDataAndType(Uri.parse(imageUri), "image/jpeg");
				intent.putExtra("mimeType", "image/*");
				startActivity(Intent.createChooser(intent, "Set as:"));
			}
		});

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				Uri screenshotUri = null;
				if (imageUri != null) {
					screenshotUri = Uri.parse(imageUri);
					sharingIntent.setType("image/jpeg");
					sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
				} else {
					screenshotUri = Uri
							.parse("market://details?id=com.android.chrome");
					sharingIntent.setType("text/plain");
					// sharingIntent.putExtra(Intent.EXTRA_STREAM,
					// screenshotUri);
					sharingIntent.putExtra(Intent.EXTRA_TEXT,
							"market://details?id=com.android.chrome");
					if (Build.VERSION.SDK_INT > 15)
						sharingIntent.putExtra(Intent.EXTRA_HTML_TEXT,
								"market://details?id=com.android.chrome");
				}

				startActivity(Intent.createChooser(sharingIntent,
						"Share image using"));

			}
		});

		String incomingNumber = "+351916379917";
		Uri uri = Uri.withAppendedPath(Phones.CONTENT_FILTER_URL,
				Uri.encode(incomingNumber));
		Toast.makeText(this, incomingNumber, Toast.LENGTH_LONG).show();
		String name = null;
		Cursor cursor = getContentResolver()
				.query(uri,
						new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
						null, null, null);
		Toast.makeText(this, "unknown", Toast.LENGTH_LONG).show();
		// Invoke endCall()
		if (cursor != null && cursor.moveToFirst()) {

			// editor1.putBoolean("fromcontacts", true);
			// editor1.putBoolean("notfromcontacts", false);
			// editor1.putString("incomingnumbername", cursor.getString(0));
			// editor1.commit();
			// Toast.makeText(this, p.getString("incomingnumbername",
			// "unknown"), Toast.LENGTH_LONG).show();
		}

		else {
			// editor1.putBoolean("notfromcontacts", true);
			// editor1.putBoolean("fromcontacts", false);
			// editor1.putString("incomingnumbername", "Unknown");
			// editor1.commit();
			Toast.makeText(this, "incomingnumbername", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAPTURE_REQUEST_CODE
				&& resultCode == Activity.RESULT_OK) {
			ExifInterface exif;
			int rotate = 0;
			if (data != null) {
				Bitmap bitmap = (Bitmap) data.getExtras().get("data");
				saveImageToSDCard(bitmap);
				try {
					imageUri = data.getData().toString();

					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					Log.i(TAG,
							"COMPRESSED Bitmap:"
									+ bitmap.compress(
											Bitmap.CompressFormat.PNG, 40,
											bytes));

					try {
						File directory = new File(
								Environment.getExternalStorageDirectory()
										+ "/myDirectory/");
						Log.i(TAG, "Created external folder successfully: "
								+ directory.mkdirs());
						File f = new File(
								Environment.getExternalStorageDirectory()
										+ "/myDirectory/" + "test.png");
						Log.i(TAG,
								"Created external file successfully: "
										+ f.createNewFile() + " file exists "
										+ f.exists());
						if (f.exists()) {
							FileOutputStream fo = new FileOutputStream(f);
							fo.write(bytes.toByteArray());
							fo.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						Log.e(TAG, "Was unable to copy image: " + e.toString());
					}

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap = MediaStore.Images.Media.getBitmap(
							getContentResolver(), data.getData());
					Log.i(TAG,
							"COMPRESSED "
									+ bitmap.compress(
											Bitmap.CompressFormat.PNG, 100,
											stream));
					byte[] byteArray = stream.toByteArray();
					Log.i(TAG, "byte array length:" + byteArray.length);

					File file = new File(getFilesDir() + "/file.png");
					FileOutputStream fos = new FileOutputStream(file);

					fos.write(byteArray);
					fos.flush();
					fos.close();

					Log.i(TAG, "imageuri = " + imageUri);
					exif = new ExifInterface(imageUri);
					int exifOrientation = exif.getAttributeInt(
							ExifInterface.TAG_ORIENTATION,
							ExifInterface.ORIENTATION_NORMAL);

					switch (exifOrientation) {
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
					try {
						Log.i(TAG, "mutable bitmap? " + bitmap.isMutable()
								+ " w: " + width + " h: " + height);
						bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100,
								100);
						// Bitmap.createBitmap(bitmap, 40, 40, width-40,
						// height-40, null, false);
						startImage.setImageBitmap(bitmap);

						Log.i(TAG,
								"mutable bitmap after? " + bitmap.isMutable()
										+ " w: " + width + " h: " + height);
					} catch (OutOfMemoryError e) {
						e.printStackTrace();

					}

					if (rotate != 0) {
						width = bitmap.getWidth();
						height = bitmap.getHeight();

						Log.i(TAG, "Intents rotating " + rotate + " degrees ");

						// Setting pre rotate
						mtx = new Matrix();
						// mtx.preRotate(rotate);

						// Rotating Bitmap & convert to ARGB_8888, required by
						// tess
						try {
							bitmap = Bitmap.createBitmap(bitmap, 40, 40,
									width - 40, height - 40, mtx, false);
							startImage.setImageBitmap(bitmap);
						} catch (OutOfMemoryError e) {
							e.printStackTrace();

						}

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		else if (requestCode == PICK_REQUEST_CODE
				&& resultCode == Activity.RESULT_OK) {

			Bitmap bitmap = null;
			if (data.getExtras() != null)
				bitmap = (Bitmap) data.getExtras().get("data");
			try {
				imageUri = data.getData().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (requestCode == CALL_REQUEST_CODE
				&& resultCode == Activity.RESULT_OK) {
			Log.i(TAG, data.toString());
		} else {
			Log.i(TAG, "ACTION FAILED");
		}
	}

	public class MyPreview implements Camera.PreviewCallback {

		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			Log.d(TAG, "Got a camera frame");

			Canvas canvas = null;

			try {
				canvas = new SurfaceView(IntentsActivity.this).getHolder()
						.lockCanvas(null);

				Log.d(TAG, "Got Bitmap");
			} finally {
				if (canvas != null) {
					new SurfaceView(IntentsActivity.this).getHolder()
							.unlockCanvasAndPost(canvas);
				}
			}
		}

	}

	public void saveImageToSDCard(Bitmap bitmap) {
		String dirname = "/Amazing Wallpapers/";

		File myDir = new File(Environment.getExternalStorageDirectory()
				.getPath() + dirname);

		myDir.mkdirs();

		String str = String.valueOf(System.currentTimeMillis());

		String fname = "Wallpaper-" + str + ".jpg";
		File file = new File(myDir, fname);
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			Toast.makeText(this,
					"Toast_saved".replace("#", "\"" + "Gallery name" + "\""),
					Toast.LENGTH_SHORT).show();
			Log.d(TAG, "Wallpaper saved to:" + file.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "toast_saved_failed", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
