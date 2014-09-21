package com.testes.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.ActionMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.android.R;
import com.testes.database.DBHelper;
import com.testes.interfaces.NavigationDrawerCallbacks;
import com.testes.receiver.WifiScanReceiver;

public class FirstActivity extends FragmentActivity implements ActionMode.Callback, NavigationDrawerCallbacks {
	TextView text1;
	EditText linkEditText, e2;
	Button dialogButton, sub, circleTestButton, imageButton,intentsButton, connectbutton,animationActivityButton, mainActivityButton, secondButton, scrollViewButton, tableLayoutButton,tabHostButton;
	Button listViewButton, createTemFiles,webViewButton,viewPagerButton, spinnerButton,alarmsButton;
	Button drawerButton,drawerLayoutButton, fragmentsButton,sensorButton,toggleButtonActivity, drawableButton,ttSpeechButton,canvasButton,pickerButton,seekBarButton,editTextButton;
	Context c=this;
	ScrollView scrollViewLayout;
	RelativeLayout drawerRelativeLayout;
	String videoUrl;
	ProgressDialog progressDialog;
	public ActionMode mActionMode;
	ImageView imageView;
	MediaRecorder recorder;
	boolean isVoiceButtonHeld = false;

	String bString, aString;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main_activity);
		linkEditText = (EditText) findViewById(R.id.myEditText);
		dialogButton = (Button) findViewById(R.id.add);
		drawableButton = (Button) findViewById(R.id.drawableButton);
		secondButton = (Button) findViewById(R.id.secondButton);
		webViewButton = (Button) findViewById(R.id.webViewButton);
		toggleButtonActivity = (Button) findViewById(R.id.toggleButton);
		connectbutton = (Button) findViewById(R.id.connect);
		imageButton = (Button) findViewById(R.id.imageButton);
		editTextButton = (Button) findViewById(R.id.editTextButton);
		sensorButton = (Button) findViewById(R.id.sensorButton);
		canvasButton = (Button) findViewById(R.id.canvasButton);
		seekBarButton = (Button) findViewById(R.id.seekBarButton);
		animationActivityButton = (Button) findViewById(R.id.animationButton);
		pickerButton = (Button) findViewById(R.id.pickersButton);
		alarmsButton = (Button) findViewById(R.id.alarmManagerButton);
		ttSpeechButton= (Button) findViewById(R.id.ttSpeechButton);
		drawerLayoutButton = (Button) findViewById(R.id.drawerLayoutButton);
		mainActivityButton = (Button) findViewById(R.id.mainButtton);
		imageView = (ImageView)findViewById(R.id.imageViewFirst);
		scrollViewLayout = (ScrollView) findViewById(R.id.scrollViewLayout);
		fragmentsButton = (Button) findViewById(R.id.fragmentsInfoButton);
		drawerButton = (Button) findViewById(R.id.fullImage);
		intentsButton = (Button) findViewById(R.id.intentsButton);
		circleTestButton =  (Button) findViewById(R.id.testViewButton);
		drawerRelativeLayout = (RelativeLayout) findViewById(R.id.drawer_layout);
		createTemFiles = (Button) findViewById(R.id.createTempFilesButton);
		viewPagerButton = (Button) findViewById(R.id.viewPagerActivityButton);
		spinnerButton = (Button) findViewById(R.id.spinnerButton);
		text1 = (TextView) findViewById(R.id.changingtext);
		tableLayoutButton = (Button) findViewById(R.id.tableButton);
		scrollViewButton  = (Button) findViewById(R.id.scrollViewButton);
		tabHostButton = (Button) findViewById(R.id.tabsHostButton);
		listViewButton  = (Button) findViewById(R.id.listViewButton);

		Log.i("FirstActivity", "onCreate() first");

		UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
		Log.i("FirstActivity", deviceList.toString());
		UsbDevice device = deviceList.get("deviceName");
		Toast.makeText(getApplicationContext(),"Attached device is : "+ device, Toast.LENGTH_LONG).show();

		//		
		//		try {
		//			URL url = new URL ("http://youcrypt.com/assets/images/osx.jpg");
		//			URLConnection urlconnect = url.openConnection();
		//			InputStream is = urlconnect.getInputStream();
		//            BufferedInputStream bis = new BufferedInputStream (is);
		//            ByteArrayBuffer baf = new ByteArrayBuffer(50);
		//		} catch (MalformedURLException e1) {
		//			
		//			e1.printStackTrace();
		//		} catch (IOException e) {
		//			
		//			e.printStackTrace();
		//		}

		
		
		View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

		int color = Color.TRANSPARENT;
		Drawable background = root.getBackground();
		if (background instanceof ColorDrawable)
			color = ((ColorDrawable) background).getColor();

		String uriBegin = "geo:" + "47.531605" + "," + "21.627312";
		String query = "47.531605" + "," + "21.627312" + "(DeviceId"+")";

		String encodedQuery = Uri.encode(query);
		String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
		Uri uri = Uri.parse(uriString);
		Intent viewIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);

		viewIntent.putExtra("DeviceId", "DeviceId");

		//	    startActivity(intent);

		Bitmap backBitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.image_load_success1);
		Options options = new Options();
		options.inSampleSize = 2;
//		Bitmap failBitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.image_load_failed,options);

		
		
		Button button = new Button(c);
		button.setId(2000);
		final AlertDialog myDialog = new AlertDialog.Builder(FirstActivity.this)
		.setMessage("errorConnectingToServer")
		/*.setView(new Button(c))*/.setCancelable(true).create();
		//		TextView textView = (TextView) myDialog.findViewById(android.R.id.message);
		//		textView.setTextSize(40);
		//		myDialog.show();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("FIRST", "Clicked ok dialog");
				myDialog.dismiss();

			}
		});


		linkEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("text", linkEditText.getText().toString() + " "+ linkEditText.getText().toString().contentEquals("center"));

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(s.length() == 15){
					//					linkEditText.setText(s.toString()+"\n");
					//					linkEditText.setSelection(s.length()+1);
				}

			}
		});

		//	 change the titlebar color
//				getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_action_bar));
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
		//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//		getSupportActionBar().setHomeButtonEnabled(true);
		//		getSupportActionBar().setDisplayShowCustomEnabled(true);
		//		getSupportActionBar().setCustomView(R.layout.actionbar_image_view);
		//		getSupportActionBar().setTitle("My Action Bar");

		//		showEditAlert(listener)

		WifiManager wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);

		if (wifi.isWifiEnabled()==false){
			wifi.setWifiEnabled(true);
		}

		WifiScanReceiver wifireciever = new WifiScanReceiver();
		//		for (int i=0;i<100;i++){
		//			registerReceiver(wifireciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		//
		//			boolean wifiScanSuccedded = wifi.startScan();
		//			Log.i("succedded", ""+wifiScanSuccedded);
		//			if (i==99){
		//				Toast.makeText(getBaseContext(), "Scan Finish", Toast.LENGTH_LONG).show();
		//			}
		//		}

		DBHelper dbHelper = new DBHelper(this, "simple_db", null, 1);

		dbHelper.createMessageReaduser("user", 2);

		new ParseHttpResponse().execute();

		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Start without a delay - first value of the pattern
		// Each element then alternates between vibrate, sleep, vibrate, sleep...
		long[] pattern = {0, 100, 1000, 200, 1000, 300, 1000, 400, 1000,500,1000,600,1000,700,1000};

		// The '-1' here means to vibrate once
		// '0' would make the pattern vibrate indefinitely
		//		vibrator.vibrate(pattern, -1);


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = sdf.format(date);
		Log.i("current Date", dateString);

		animationActivityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, AnimationActivity.class));

			}
		});

		alarmsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, AlarmsActivity.class));
				
			}
		});
		
		spinnerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, SpinnerActivity.class));
				overridePendingTransition(R.anim.animation, R.anim.animation2);
			}
		});

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, ImageActivity.class));

			}
		});

		toggleButtonActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, ToggleButtonActivity.class));

			}
		});

		drawerLayoutButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, DrawerActivity.class));
				
			}
		});
		
		canvasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, CanvasActivity.class));

			}
		});

		editTextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, EditTextActivity.class));

			}
		});

		seekBarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, SeekBarActivity.class));
				//				
				FirstActivity.this.overridePendingTransition(R.anim.animation1, R.anim.animation3);
			}
		});

		pickerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, PickerActivity.class));
				overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
			}
		});

		sensorButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, SensorActivity.class));

			}
		});

		Random i = new Random();
		i.nextInt();
		
		ttSpeechButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, AgendaDetail.class));
			}
		});
		

	        
		HashMap<Integer, Random> randomsHashMap = new HashMap<Integer, Random>();

		listViewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, ListViewActivity.class));
				//				startActivity(new Intent(FirstActivity.this, HorizontalListViewActivity.class));
			}
		});


		fragmentsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, FragmentsActivity.class));

			}
		});

		tableLayoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle =  new Bundle();
				Intent scrollIntent = new Intent(FirstActivity.this, TableLayoutActivity.class);
				bundle.putString("color", "#CCAABB");
				bundle.putString("style", "bold");
				scrollIntent.putExtras(bundle);
				startActivity(scrollIntent);

			}
		});

		circleTestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, TestActivity.class));

			}
		});

		intentsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, IntentsActivity.class));

			}
		});

		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
		Log.i("TASKs", ""+taskInfo.get(0).topActivity.getClassName() + " numActivities: "
				+  taskInfo.get(0).numActivities);

		fragmentsButton.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View view) {
				if (mActionMode != null) {
					return false;
				}

				// Start the CAB using the ActionMode.Callback defined above
				//		        mActionMode = startSupportActionMode(mActionModeCallback);
				view.setSelected(true);
				return true;
			}

		});

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			int layoutDirection = fragmentsButton.getLayoutDirection();
			int absoluteGravity = Gravity.getAbsoluteGravity(Gravity.CENTER, layoutDirection);
			Log.i("GRAVITY", layoutDirection + " "+ absoluteGravity);
		}


		/**
		 * 
		 */
		tabHostButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle =  new Bundle();
				Intent tabsIntent = new Intent(FirstActivity.this, TabsActivity.class);
				bundle.putString("color", "#CCAABB");
				bundle.putString("style", "bold");
				tabsIntent.putExtras(bundle);
				startActivity(tabsIntent);
			}
		});

		//		drawableButton.setOnClickListener(new OnClickListener() {
		//
		//			@Override
		//			public void onClick(View v) {
		//				Bundle bundle =  new Bundle();
		//				Intent scrollIntent = new Intent(FirstActivity.this, DrawableActivity.class);
		//		
		//				startActivity(scrollIntent);
		//
		//			}
		//		});

		drawableButton.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				isVoiceButtonHeld = true;
				startRecording();
				return false;
			}
		});


		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("Please Wait... ");

		drawableButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					if (dialog != null && !dialog.isShowing()) {
						dialog.show();
					}
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP:
					if(dialog!= null && dialog.isShowing()){
						dialog.dismiss();
					}
					break;
				}
				return true;
			}
		});


		scrollViewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, ScrollViewActivity.class));

			}
		});

		createTemFiles.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//				String []TempFiles ={"c1234c10","c1234c11","c1234c12","c1234c13"};
				//				for(int i=0;i<=3;i++)
				//				{
				//					try{
				//						String tDir = System.getProperty("java.io.tmpdir");
				//						System.out.println(tDir);
				//						File parentFOlder = new File(tDir);
				//						String tempFile= TempFiles[i]; 
				//						File temp = File.createTempFile(parentFOlder.getAbsolutePath()+"//"+tempFile, ".xls"); 
				//
				//						System.out.println("Temp file : " + temp.getAbsolutePath());
				//
				//					}catch(IOException e){
				//
				//						e.printStackTrace();
				//					}
				//				}

				File myFolder = new File(getFilesDir(), "/Images/abc/");
				myFolder.mkdirs();
				Log.i("FirstActivity", "folder name: "+myFolder.getName());
				try {
					for (int i = 0; i < 3; i++) {
						String ab = i + ".png";
						File file = new File(myFolder.getAbsolutePath()//folder path
								+File.separator
								+ab); //file name
						Log.i("FirstActivity", "file "+ i + " created : " + file.createNewFile());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		text1.setText("TEXT" + getString(R.string.code));

		text1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(FirstActivity.this, ButtonsActivity.class), 100);
			}
		});

		viewPagerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, ViewPagerActivity.class));

			}
		});


		drawerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(FirstActivity.this, DrawerLayoutActivity.class));

			}
		});

		dialogButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				//				Handler handler = new Handler();
				//				handler.postDelayed(new Runnable() {
				//					public void run() {
				//						if(this!=null && !isFinishing()){
				//							final ProgressDialog dialog = ProgressDialog.show(FirstActivity.this,
				//									"Please Wait... ", "Loading... ", false, true);
				//							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				//						}
				//					}
				//				}, 5000);

				//								.setPositiveButton(getString(R.string.ok),
				//										new DialogInterface.OnClickListener() {
				//									public void onClick(DialogInterface dialog,	int whichButton) {
				//				
				//									}
				//								}).create();
				//				myDialog.setCancelable(false);
				//
				//				myDialog.show();
				//
				//				int height = myDialog.getWindow().getDecorView().getHeight();
				//
				//				System.out.println("height: "+ height);
				//
				//				myDialog.setOnDismissListener(new OnDismissListener() {
				//
				//					@Override
				//					public void onDismiss(DialogInterface dialog) {
				//						System.out.println("fez dismiss");
				//
				//					}
				//				});
				//
				//				final AlertDialog tempDialog = myDialog;
				//
				//				Handler handler = new Handler();
				//				handler.postDelayed(new Runnable() {
				//					public void run() {
				//
				//						if (tempDialog != null) {
				//							int height = tempDialog.getWindow().getDecorView().getHeight();
				//							int width = tempDialog.getWindow().getDecorView().getWidth();
				//							tempDialog.dismiss();
				//							Log.i("", width  + " "+ height);
				//
				//						}
				//					}
				//
				//				}, 2000);


				AlertDialog myAlertDialog = new AlertDialog.Builder(FirstActivity.this)
				.setMessage("My Dialog Message")
				.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int whichButton) {

					}
				}).setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int whichButton) {

					}
				}).create();
				myAlertDialog.show();

			}
		});


		Log.i("FirstActivity", ""+scrollViewLayout.getScrollY());

		mainActivityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(FirstActivity.this, MainActivity.class));

			}
		});

		connectbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ProgressDialog ringProgressDialog = ProgressDialog.show(FirstActivity.this,
						"Please wait ...", "Downloading Image ...", true);

				ringProgressDialog.setCancelable(false);
				ringProgressDialog.hide();

				AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

					@Override
					protected void onPreExecute() {

						ringProgressDialog.show();
					}

					@Override
					protected Void doInBackground(Void... arg0) {
						try {
							//				            diaBox1.show();
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						if (ringProgressDialog != null) {
							ringProgressDialog.dismiss();
							// b.setEnabled(true);
						}
					}

				};
				task.execute((Void[]) null);

			}
		});

		webViewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent webviewIntent = new Intent(FirstActivity.this, WebViewActivity.class);

				webviewIntent.putExtra("webview", "www.google.com");
				startActivity(webviewIntent);

			}
		});

		secondButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//				startActivity(new Intent(First.this, FullImageActivity.class));
				Intent secondIntent = new Intent(FirstActivity.this, SecondActivity.class);
				HashMap<String, String> hashMap = new HashMap<String, String>();

				hashMap.put("A","1");
				hashMap.put("B","");

				Bundle extras = new Bundle();
				extras.putSerializable("key", hashMap);
				secondIntent.putExtra("coins", 10);
				secondIntent.putExtras(extras);
				secondIntent.putExtra("aKey", "key");
				startActivity(secondIntent);
			}
		});

		float mSec = 9.4f;
		double sec=10.2;
		double bestMSec  = 11.92;
		double bestSec =11.2;
		Editor editor= getSharedPreferences("PREFS_NAME", Context.MODE_WORLD_WRITEABLE).edit();
		if(sec< bestSec){
			getSharedPreferences("PREFS_NAME", Context.MODE_WORLD_WRITEABLE);
			getSharedPreferences("PREFS_NAME", Context.MODE_WORLD_WRITEABLE);
			if(mSec<10){
				editor.putString("best", sec+"."+"0"+mSec);
			}else{
				editor.putString("best", sec+"."+mSec);
			}
			editor.putLong("bestSec", (long) sec);
			editor.putFloat("bestMSec", (float) mSec);
			editor.commit();
		}else if(sec== bestSec){
			if(mSec< bestMSec){
				getSharedPreferences("PREFS_NAME",MODE_WORLD_WRITEABLE);

				if(mSec<10){
					editor.putString("best", sec+"."+"0"+mSec);
				}else{
					editor.putString("best", sec+"."+mSec);
				}
				editor.putFloat("bestSec", (float) sec);
				editor.putFloat("bestMSec", mSec);
			}
		}

		MediaPlayer p = new MediaPlayer();
		p.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
		final MediaRecorder recorder = new MediaRecorder();
		////		android.hardware.Camera mCamera = Camera.open();
		////		mCamera.unlock();
		////		recorder.setCamera(mCamera);
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiotest.3gp";
		//		if (path == null)
		//			return;
		////		recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); 
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		////		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		recorder.setOutputFile(path);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		//		recorder.setMaxDuration(300000);
		//
		//
		try {
			recorder.prepare();

		} catch(IllegalStateException ie){
			ie.printStackTrace();
		}
		catch (IOException e) {
			Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
		}
		//				recorder.start();

		//		MediaPlayer p = new MediaPlayer();
		//	    final MediaRecorder recorder = new MediaRecorder();
		//	    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//	    final String path = getFilesDir().getAbsolutePath()+"/acd";
		//	    if (path == null)
		//	        return;
		//	    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		//	    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		//	    recorder.setOutputFile(path);
		//	    recorder.setMaxDuration(300000);
		//	    try {
		//	        recorder.prepare();
		//	    } catch (IOException e) {
		//	        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
		//	    }
		//	    recorder.start();

		String a = ".o.o.o.o.-";
		a = a.substring(0,a.length()-1);
		System.out.println(a);

	}

	private void stopRecording() {
		Toast.makeText(FirstActivity.this, "Recording Finished", Toast.LENGTH_SHORT).show();

		if(recorder!=null){
			recorder.stop();
			recorder.release();
			recorder = null;
		}
	}

	private void startRecording() {
		Toast.makeText(FirstActivity.this, "Recording Message", Toast.LENGTH_SHORT).show();

		String filename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiotest.3gp";

		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setOutputFile(filename);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try { 
			recorder.prepare(); 
		} 
		catch (IOException e) {
			Log.e("MediaRecorder", "prepare() failed"); 
			e.printStackTrace();
		}
		recorder.start();
	}

	public class ParseHttpResponse extends AsyncTask<Void, Void, Bitmap> {

		Context context;
		boolean showDialog;
		boolean showError=false;
		private Dialog loadingDialog;
		String result;
		String stringUrl;

		public ParseHttpResponse(Context context, boolean showDialog, String stringURL) {
			this.context = context;
			this.loadingDialog = new Dialog(context);
			this.showDialog = showDialog;
			this.stringUrl = stringURL;
		}

		public ParseHttpResponse() {

		}

		@Override
		protected void onPreExecute() {
			//			if (showDialog){
			//				loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			//				loadingDialog.setContentView(R.layout.layout_load);
			//				loadingDialog.setOnCancelListener(new OnCancelListener() {
			//
			//					@Override
			//					public void onCancel(DialogInterface dialog) {
			//						cancel(true);
			//					}
			//				});
			//				loadingDialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//				loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			//				//_loadingDialog.getWindow().setWindowAnimations(R.style.dialog_animation_fade);
			//				loadingDialog.show();
			//			}
			//			if(showError){
			//				Toast.makeText(context, "errorConnectingToServer", Toast.LENGTH_LONG).show();
			//			}
		}

		@Override
		protected Bitmap doInBackground(Void... params) {
			//			URL u = null;
			Bitmap bitmap=null;
			//			try {
			List<NameValuePair> paramas = new ArrayList<NameValuePair>();
			paramas.add(new BasicNameValuePair("product_id", "sdd2"));
			//				bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://www.google.com").getContent());
			//
			//			} catch (MalformedURLException e) {
			//				e.printStackTrace();
			//			} catch (IOException e) {
			//				e.printStackTrace();
			//			}
			//			try {
			//				u = new URL(stringUrl);
			//			} catch (MalformedURLException e1) {
			//				e1.printStackTrace();
			//			}
			//




			//			
			//			Ion.with(FirstActivity.this).load("http://---------").setMultipartParameter("IUser_ID=", "126").setMultipartFile("", new File(strFilePath)).setMultipartParameter("User_ID=", "amody@gmail.com").setMultipartParameter("FileTitle", strFileTitle).setMultipartParameter("DT", strDocumentType).asString().setCallback(new FutureCallback<String>() {
			//				@Override
			//				public void onCompleted(Exception e, String result) {
			//				Log.e("Response", "" + result);
			//
			//				}
			//				});

			//			try {
			//				InetAddress address = InetAddress.getByName(new URL(stringUrl).getHost());
			//
			//				String ip = address.getHostAddress();
			//
			//				Log.i("ip", ip);
			//			} catch (UnknownHostException | MalformedURLException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//
			//			HttpURLConnection connection = null;
			//			try {
			//				connection = (HttpURLConnection) u.openConnection();
			//			} catch (IOException e2) {
			//				e2.printStackTrace();
			//			}
			//			try {
			//				connection.setRequestMethod("GET");
			//			} catch (ProtocolException e1) {
			//				e1.printStackTrace();
			//			}
			//
			//			try {
			//				connection.connect();
			//				connection.getContentLength();
			//			} catch (IOException e1) {

			//							e1.printStackTrace();
			//						}
			//
			//			InputStream in;
			//			try {
			//				in = connection.getInputStream();
			//
			//				InputStreamReader isReader = new InputStreamReader(in); 
			//
			//				//put output stream into a string
			//				BufferedReader br = new BufferedReader(isReader);
			//
			//				StringBuilder total = new StringBuilder();
			//				String line;
			//				while ((line = br.readLine()) != null) {
			//					total.append(line);
			//				}
			//
			//				String bufferString = new String(total.toString());
			//				//				System.out.println(bufferString);
			//				if(bufferString.contains("iframe"))
			//					System.out.println("TEM IFRAME");
			//				if(bufferString.contains("lock-"))
			//					System.out.println("tem lock");
			//				if(bufferString.contains("youtube.") || bufferString.contains("youtube.com/embed/")){
			//					System.out.println("YOUTUBE");
			//					String pattern = "(watch\\?v=|/videos/|embed\\/)[^#\\&\\?]{11}";
			//
			//
			//					Pattern compiledPattern = Pattern.compile(pattern);
			//					Matcher matcher = compiledPattern.matcher(bufferString);
			//
			//					if(matcher.find()){
			//						System.out.println(matcher.group());
			//						videoUrl = "http://"+"www.youtube.com/"+matcher.group();
			//					}
			//				}
			//				else if(bufferString.contains("dailymotion")){
			//					System.out.println("DAILYmotion");
			//
			//				}
			//				else
			//					videoUrl = "http://"+"www.youtube.com/embed/3NCkuRapn4I";
			//			} 
			//			catch (FileNotFoundException fe) {
			//				fe.printStackTrace();
			//			}
			//			catch (IOException e) {
			//				e.printStackTrace();
			//			}
			//			catch (IllegalStateException ilegE) {
			//				ilegE.printStackTrace();
			//				//json response was empty - no connection?
			//			}
			//			catch (Exception e) {
			//				e.printStackTrace();
			//			}
			//			//
			//			//			finally{
			//			//				// To avoid dialog flickering
			//			//				try {
			//			//					if (showDialog){
			//			//						Thread.sleep(50);
			//			//					}
			//			//				} catch (InterruptedException e) {
			//			//					e.printStackTrace();
			//			//				}
			//			//			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			//			// User canceled
			//			if (showDialog){
			//				if (!loadingDialog.isShowing()){
			//					System.out.println("cancelou o parsing");
			//					return;
			//				}
			//			}

			//			imageView.setImageBitmap(result);
			// Dismiss loading dialog
			//			if (showDialog){
			//				if (loadingDialog.isShowing()) {
			//					loadingDialog.dismiss();
			//				}
			//			}


		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==100){
			if(data!=null){
				String text = data.getExtras().getString("buttonText");
				text1.setText(text);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 
		{
			setContentView(R.layout.rating_layout);
		} 
		else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			setContentView(R.layout.layout_main_activity);
		}
	}

	private EditText showEditAlert(DialogInterface.OnClickListener listener) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("waypoint");
		alert.setMessage("waypoint_alert_text");
		EditText editText = new EditText(this);
		alert.setView(editText);
		//		alert.setPositiveButton(android.R.string.ok, listener);
		//		alert.setNegativeButton(android.R.string.cancel, null);
		alert.show();
		return editText;
	}

	private int toChar(int encryptCode){

		String alphabet = "abcdefghijklmnopqrstuvwxyz"; 
		char[] alpha = alphabet.toCharArray();

		int base = 26;
		double division = ((double)(encryptCode/255));
		long rounded = Math.round(division);
		int characterSteps = (int) ((rounded)*base);

		char character = alpha[characterSteps];
		System.out.println("char:"+ character);
		return character;
	}


	@Override
	protected void onResume() {
		Log.i("firstacti", "onresume first");
		super.onResume();
	}

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// Called when the action mode is created; startActionMode() was called
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate a menu resource providing context menu items
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.block, menu);
			return true;
		}

		// Called each time the action mode is shown. Always called after onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false; // Return false if nothing is done
		}

		// Called when the user selects a contextual menu item
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.action_overflow:
				//	                shareCurrentItem();
				mode.finish(); // Action picked, so close the CAB
				return true;
			default:
				return false;
			}
		}

		// Called when the user exits the action mode
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode = null;
		}
	};


	@Override
	public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
		return false;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub

	}


}