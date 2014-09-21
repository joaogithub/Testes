package com.testes.activity;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.testes.android.R;
import com.testes.android.R.string;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	static HttpClient client;
	static HttpGet request = new HttpGet();
	static HttpPost post = new HttpPost();
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder db;
	static long timestamp;
	static InputSource inStream;
	static ListView lv;
	static HttpResponse response;
	public ImageView img1, img2, img3, img4;
	public TextView emptyText, text2, text3;
	ArrayList <String> layoutData;
	static ProgressDialog progressDialog;
	private Context _context;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.layout_spinner_activity);
		_context = this;

		//Print display size and characteristics
		WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth(); // deprecated
		int height = display.getHeight(); // deprecated
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		String strScreenDIP = "The model of the device is: "+ android.os.Build.MANUFACTURER + " "+ android.os.Build.MODEL+ " with brand " + android.os.Build.BRAND + " as product "+ android.os.Build.PRODUCT+ "\n";
		strScreenDIP += "The Android ID is " + Secure.ANDROID_ID + "\n";
		strScreenDIP += "The current API version is: " + android.os.Build.VERSION.SDK_INT + "\n";
		strScreenDIP += "Screen size category is: "+getSizeName(this) + "\n";
		strScreenDIP += "The logical density of the display is: " + dMetrics.density + "\n";
		strScreenDIP += "The screen density expressed as dots-per-inch is: " + dMetrics.densityDpi + " - "+ getScreenDensityName(dMetrics.densityDpi)+"\n";
		strScreenDIP += "The screen size in dp: " + height/((float)dMetrics.densityDpi/160) + "x"+ width/((float)dMetrics.densityDpi/160)+ "\n";
		strScreenDIP += "The absolute height of the display in pixels: " + dMetrics.heightPixels +"\n";
		strScreenDIP += "The absolute width of the display in pixels: " + dMetrics.widthPixels+ "\n";
		strScreenDIP += "A scaling factor for fonts displayed on the display: " + dMetrics.scaledDensity + "\n";
		strScreenDIP += "The exact physical pixels per inch of the screen in the X dimension: " + dMetrics.xdpi + "\n";
		strScreenDIP += "The exact physical pixels per inch of the screen in the Y dimension: " + dMetrics.ydpi + "\n";
		System.out.println(strScreenDIP);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

			}
		});
	}

	//private void highlightMenuItemImage(double menuItemId) {
	//	for(WebImageView image : _menuItemImages) {
	//		image.getImageView().setColorFilter(Color.argb(0, 0, 0, 0));
	//	}
	//
	//	if(menuItemId != -1) _menuItemImages.get((int) menuItemId).getImageView().setColorFilter(getResources().getColor(R.color.menu_item_color_selection));
	//}

	public void bulkDownloadFirstTime()	{

		//Get layoutData from xml

		try {

			final CharSequence[] items = {getString(R.string.bulkCompleteDownload), getString(R.string.bulkDownloadContinue)};

			final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static String getSizeName(Context context) {
		int screenLayout = context.getResources().getConfiguration().screenLayout;
		screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (screenLayout) {
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			return "SMALL";
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			return "NORMAL";
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			return "LARGE";
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			return "XLARGE";
		default:
			return "UNDEFINED";
		}
	}

	public String getScreenDensityName(int screenDensitydpi){
		switch(screenDensitydpi){
		case DisplayMetrics.DENSITY_LOW:
			return "LDPI";
		case DisplayMetrics.DENSITY_MEDIUM:
			return "MDPI";

		case DisplayMetrics.DENSITY_HIGH:
			return "HDPI";

		case DisplayMetrics.DENSITY_XHIGH:
			return "XHDPI";

		case DisplayMetrics.DENSITY_XXHIGH:
			return "XXHDPI";

		case DisplayMetrics.DENSITY_XXXHIGH:
			return "XXXHDPI";
		default:
			return "DEFAULT";
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

}
