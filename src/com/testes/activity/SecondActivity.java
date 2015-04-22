package com.testes.activity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.ListPopupWindowCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ListPopupWindow;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.android.R;
import com.testes.views.BrushViewClass;

public class SecondActivity extends ActionBarActivity {

	ArrayAdapter<String> adapter;
	public static final String TAG = "SecondActivity";
	ArrayList<String> artistName = new ArrayList<String>();
	LinearLayout root;
	TextView Biography;
	ListView list;
	String xxx;
	ProgressBar progress;
	String items;
	Button home;
	String listViewIsEmpty;
	ImageView imageView;
	private ListView lv;
	Button search,mybooks,contact,about, contactsButton;
	View mybooks_layout,contact_layout,about_layout;
	EditText inputSearch;
	private BluetoothAdapter bluetoothadapt;
	ViewGroup content;
	ArrayList<HashMap<String, String>> productList;

	String value;
	public static final String URL = "http://theopentutorials.com/totwp331/wp-          content/uploads/totlogo.png";
	protected static final int CONTACT_PICKER_RESULT = 1000;
	private int coinsValue = 0;

	@SuppressWarnings("unchecked")
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sec_activity);
		Bundle bundle = null;
		if(getIntent().getExtras()!=null){
			bundle = getIntent().getExtras();
			Serializable res = bundle.getSerializable("results");

			coinsValue  = getIntent().getIntExtra("coins", 0);
			Log.i(TAG, "Coins: "+ coinsValue);

			Serializable s = getIntent().getExtras().getSerializable("key");
			Log.i(TAG,s!=null?s.toString():"null key serializable");

			//			HashMap<String, String> hashMap = (HashMap<String, String>) s;
			//			Log.i(TAG, hashMap.toString());
		}
		//		        if (i.hasExtra("aKey")) {
		//            value = i.getStringExtra("aKey");
		//             new DownloadFilesTask().execute(value);
		//		        }
		if(getSupportActionBar()!=null){
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}

		imageView = (ImageView) findViewById(R.id.drawer_icon);

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imageView.setX(imageView.getX()+20);

			}
		});

		contactsButton = (Button) findViewById(R.id.contacts);
		Button popupButton = (Button) findViewById(R.id.popup);

		bluetoothadapt = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothadapt == null) {
			Toast.makeText(getApplicationContext(), "Your device does not support Bluetooth", Toast.LENGTH_LONG).show();
			popupButton.setEnabled(false);
		} else {
			popupButton.setEnabled(true);
		}

		popupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				find(view);

			}
		});

		if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
			Intent detailIntent = new Intent(SecondActivity.this, ImageActivity.class);
			String transitionName = getString(R.string.my_transition);
			ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
					popupButton, transitionName);
			startActivity(detailIntent, options.toBundle());
		}

		ArrayList<String> array = new ArrayList<String>();
		array.add("spendisse vm. Cras eleifend ante at laoreet facilisis. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridic lorem ipsum");
		array.add("at rutrum tellus. Nsl none elit dictum, et aliquet sem condimentum. Praesent mauris mi, malesuada volutpat dictum a, porta non mauris. Cras non sce");
		array.add("quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci veli");
		array.add("ltricies interdum sit ame lorem. Donec eget quam porta, hendrerit sapien a, interdum arcu. Integer rutrum, ante sed posuere adipiscing, nisl mi malesuada lorem, quis egestas sem augue fauci");

		final ListPopupWindow showRoomListPopupWindow = new ListPopupWindow(SecondActivity.this);
		showRoomListPopupWindow.setAdapter(new ArrayAdapter(SecondActivity.this,
				R.layout.movie_detail_spinner_item, array));
		showRoomListPopupWindow.setModal(true);

		showRoomListPopupWindow.setAnchorView(popupButton);

		showRoomListPopupWindow.setWidth(android.support.v7.internal.widget.ListPopupWindow.WRAP_CONTENT);

		showRoomListPopupWindow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(SecondActivity.this, "Clicked item " + position, Toast.LENGTH_SHORT).show();
			}
		});

		popupButton.post(new Runnable() {
			public void run() {
				showRoomListPopupWindow.show();
				// no divider
				showRoomListPopupWindow.getListView().setDivider(null);
			}
		});

		//contacts button
		contactsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(contactPickerIntent,CONTACT_PICKER_RESULT);

				ContentResolver contentResolver = getContentResolver();
				Cursor contentCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,null, null);

				if (contentCursor.getCount() > 0) {
					while (contentCursor.moveToNext()) {
						String id = contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts._ID));
						String name = contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
						String timesContacted = contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED));

						if (Integer.parseInt(contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
							Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
									null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?",new String[] { id }, null);

							while (phoneCursor.moveToNext()) {
								String phoneNo = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
								Log.i(TAG, name+" phone number: "+ phoneCursor.getPosition()+ ": "+ phoneNo);
							}
							phoneCursor.close();
						}
					}
				}
				contentCursor.close();
			}
		});

		try {
			FileOutputStream fOut = openFileOutput("chaves.dat" , MODE_APPEND);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write("coisas");
			osw.flush();
			osw.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		home=(Button)findViewById(R.id.home_btn);   
		mybooks=(Button)findViewById(R.id.mybooks_btn);
		//		contact=(Button)findViewById(R.id.contact_btn);
		about=(Button)findViewById(R.id.about_btn);
		inputSearch=(EditText)findViewById(R.id.timeEdit);
		//	lv=(ListView)findViewById(R.id.bookList);

		String a="Mão";

		AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
		//        ListAdapter adapter = new CustomDialogAdapter(context, itemsList);
		//        builder.setAdapter(adapter, listener);
		final EditText input = new EditText(this);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER_HORIZONTAL);
		//      input.setSingleLine(true);
		layout.setPadding(10, 0, 10, 0);
		input.setHint("Hint");
		layout.addView(input);
		//        builder.setTitle("title").setMessage("coisas").setView(layout);
		//        AlertDialog dialog = builder.create();
		//        dialog.setCanceledOnTouchOutside(true);
		//        dialog.show();

		String output;

	}

	String getTimeGioAm(String a){
		String time="";
		if (a.equals("Mão")) {
			time = "6-8";
		}
		return time ;
	}

	private StringBuilder inputStreamToString(InputStream is) {
		String rLine = "";
		StringBuilder answer = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		try {
			while ((rLine = rd.readLine()) != null) {
				answer.append(rLine);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			progress.setProgress(0);
		}

		protected String doInBackground(String... artist) {
			int size;
			String x = "";
			String y = "";
			int count = 0;
			JSONArray zArray;
			xxx = "";

			String jsonResult = "";
			try {

				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httppost = new HttpGet(
						"http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist="
								+ artist[0]
										+ "&api_key=4d58b86a02c379e5b57b95091226c1ef&format=json");

				HttpResponse response = httpclient.execute(httppost);

				jsonResult = inputStreamToString(
						response.getEntity().getContent()).toString();

				Log.d(TAG, "try1 testss");
				try {
					JSONObject object = new JSONObject(jsonResult);

					x = object.getString("topalbums");
					JSONObject obj2 = new JSONObject(x);
					zArray = obj2.getJSONArray("album");
					for (int i = 0; i < zArray.length(); i++) {
						JSONObject c = zArray.getJSONObject(i);

						xxx = c.getString("name");
					}

					for (int i = 0; i < zArray.length(); i++) {
						String name;

						JSONObject obj4 = zArray.getJSONObject(i);
						// xxx = obj4.getString("name") + "\n";
						//
						// artistName.add(xxx);
					}

					// JSONObject obj5 = new JSONObject(x);
					// x = obj5.getString("image");

					/*
					 * for(int i=0;i<size;i++) { JSONObject obj4 =
					 * array.getJSONObject(i); x+=               obj4.getString("name"); }
					 */
					// JSONArray array = new JSONArray(jsonResult);

				} catch (JSONException e2) {
					e2.printStackTrace();
					Log.e(TAG, "jsonexception secondactivity testss");
				}

			} catch (ClientProtocolException e) {
				Log.d("4 catch1", "testss");
				e.printStackTrace();
			} catch (IOException e) {
				Log.d("4 catch 2", "testss");
				e.printStackTrace();
			}
			// Toast.makeText(getApplicationContext(), x+" ",
			// Toast.LENGTH_SHORT).show();
			Log.d("bottom", "testss");

			return xxx;

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progress.setProgress(100);
		}

		protected void onPostExecute(String result) {
			Biography.setText(xxx);

			// bio.setAdapter(adapter);
		}

	}

	public void find(View view) {
		if (bluetoothadapt.isDiscovering()) {
			// the button is pressed when it discovers, so cancel the discovery
			bluetoothadapt.cancelDiscovery();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == CONTACT_PICKER_RESULT){
			if(resultCode==Activity.RESULT_OK){
				coinsValue = coinsValue-3;
				Uri uri = data.getData();
				ContentResolver contentResolver = getContentResolver();
				Cursor contentCursor = contentResolver.query(uri, null, null,null, null);

				if(contentCursor.moveToFirst()){
					String id = contentCursor.getString(contentCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

					String hasPhone =
							contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

					if (hasPhone.equalsIgnoreCase("1")) 
					{
						Cursor phonesCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
						phonesCursor.moveToFirst();
						String contactNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						Log.i("SecondActivity", "PhoneNumber - The phone number is "+ contactNumber);
						phonesCursor.close();
					}
				}
				super.onActivityResult(requestCode, resultCode, data);
			}
		}
	}

	public static String getContactPhoneNumber(Context context, String contactId) {
		int type = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
		String phoneNumber = null;

		String[] whereArgs = new String[] { String.valueOf(contactId), String.valueOf(type) };

		Log.d("SecondActivity", "Got contact id: "+contactId);

		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
				null,
				ContactsContract.CommonDataKinds.Phone._ID + " = ? and " + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?", 
				whereArgs, 
				null);

		int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

		if (cursor != null) {
			Log.d("SecondActivity", "Returned contact count: "+cursor.getCount());
			try {
				if (cursor.moveToFirst()) {
					phoneNumber = cursor.getString(phoneNumberIndex);
				}
			} finally {
				cursor.close();
			}
		}

		Log.d("SecondActivity", "Returning phone number: "+phoneNumber);
		return phoneNumber;
	}

}
