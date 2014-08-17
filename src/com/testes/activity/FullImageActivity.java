package com.testes.activity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.testes.adapter.DrawerItemAdapter;
import com.testes.android.R;
import com.testes.interfaces.NavigationDrawerCallbacks;

public class FullImageActivity extends ActionBarActivity implements NavigationDrawerCallbacks{

	private ImageView _imageView;
	private ListView mDrawerListView;
	private View mFragmentContainerView;
	private int mCurrentSelectedPosition = 0;

	/**
	 * Remember the position of the selected item.
	 */
	private static final String SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * Per the design guidelines, you should show the drawer on launch until the user manually expands it. This shared
	 * preference tracks this.
	 */
	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	/**
	 * A pointer to the current callbacks instance (the Activity).
	 */
	private NavigationDrawerCallbacks mCallbacks;

	/**
	 * Helper component that ties the action bar to the navigation drawer.
	 */
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private boolean mUserLearnedDrawer;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_drawerimage_activity);

		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		//		// get intent data
		//		Intent i = getIntent();
		//
		//		// Selected image id
		//		//int position = i.getExtras().getInt("id");
		//		//ArrayAdapter imageAdapter = new ArrayAdapter(this);
		//
		_imageView = (ImageView) findViewById(R.id.profileImage);

		Bitmap bitmap = ((BitmapDrawable)_imageView.getDrawable()).getBitmap();

		mDrawerListView = (ListView) findViewById(R.id.left_drawer);

		mDrawerListView.setAdapter(new DrawerItemAdapter(getSupportActionBar().getThemedContext(),
				R.layout.drawer_item, new String[] { "wall_title", "my_account",
			"feedback_title", "about_title",
		"faq_title" }, new Integer[] { R.drawable.abc_ab_share_pack_holo_light,
			R.drawable.abc_ab_stacked_solid_dark_holo, R.drawable.icn_exercises_2dig, R.drawable.icn_seta_preto,
			R.drawable.nd_icon_mural }));

		mDrawerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
				
			}
		});

		//		//imageView.setImageResource(imageAdapter.mThumbIds[position]);
		//		setaswall(new View(this));

		//		Calendar testCalendar = Calendar.getInstance();
		//
		//		testCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
		//
		//		Log.e("WEEEK TEST:", ""+ testCalendar.get(Calendar.WEEK_OF_YEAR) + " "+ testCalendar.getFirstDayOfWeek());
		//
		//		testCalendar.add(Calendar.WEEK_OF_YEAR, 1);
		//
		//		Log.e("WEEEK TEST:", ""+ testCalendar.get(Calendar.WEEK_OF_YEAR));
		Point p = new Point(5,5);
		//		floodFill(bitmap, p, bitmap.getPixel(5, 5), R.color.green);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}


	/**
	 * bitmap not mutable
	 * @param image
	 * @param node
	 * @param targetColor
	 * @param replacementColor
	 */
	public void floodFill(Bitmap  image, Point node, int targetColor,
			int replacementColor) {
		int width = image.getWidth();
		int height = image.getHeight();
		int target = targetColor;
		int replacement = replacementColor;
		if (target != replacement) {
			Queue<Point> queue = new LinkedList<Point>();
			do {
				int x = node.x;
				int y = node.y;
				while (x > 0 && image.getPixel(x - 1, y) == target) {
					x--;
				}
				boolean spanUp = false;
				boolean spanDown = false;
				while (x < width && image.getPixel(x, y) == target) {
					image.setPixel(x, y, replacement);
					if (!spanUp && y > 0 && image.getPixel(x, y - 1) == target) {
						queue.add(new Point(x, y - 1));
						spanUp = true;
					} else if (spanUp && y > 0
							&& image.getPixel(x, y - 1) != target) {
						spanUp = false;
					}
					if (!spanDown && y < height - 1
							&& image.getPixel(x, y + 1) == target) {
						queue.add(new Point(x, y + 1));
						spanDown = true;
					} else if (spanDown && y < height - 1
							&& image.getPixel(x, y + 1) != target) {
						spanDown = false;
					}
					x++;
				}
			} while ((node = queue.poll()) != null);
		}
	}

	public void setaswall(View view) { // SET AS WALLPAPER BUTTON
		WallpaperManager myWallpaperManager
		= WallpaperManager.getInstance(getApplicationContext());
		try {
			Bitmap bitmap=((BitmapDrawable)_imageView.getDrawable()).getBitmap();
			if(bitmap!=null)
				myWallpaperManager.setBitmap(bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setaaswall(View view) { // SET AS WALLPAPER BUTTON
		WallpaperManager myWallpaperManager 
		= WallpaperManager.getInstance(getApplicationContext());
	};

	/**
	 * loads the activity for this position and updates the current position on the drawer
	 * 
	 * @param position
	 *            the position to select
	 */
	protected void selectItem(int position)
	{
		mCurrentSelectedPosition = position;
		if (mDrawerListView != null)
		{
			mDrawerListView.setItemChecked(position, true);
		}
		if (mDrawerLayout != null)
		{
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		}
		if (mCallbacks != null)
		{
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	protected void onStart()
	{
		try
		{
			mCallbacks = (NavigationDrawerCallbacks) FullImageActivity.this;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
		}

		setUp(R.id.left_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		super.onStart();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}



	/**
	 * Users of this fragment must call this method to set up the navigation drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout)
	{
		mFragmentContainerView = findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer opens
		//		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.custom_nav_drawer, /* nav drawer image to replace 'Up' caret */
				R.string.ok, /* "open drawer" description for accessibility */
				R.string.abc_action_bar_up_description /* "close drawer" description for accessibility */
				)
		{

			@Override
			public void onDrawerClosed(View drawerView)
			{
				super.onDrawerClosed(drawerView);

				supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView)
			{
				super.onDrawerOpened(drawerView);

				if (!mUserLearnedDrawer)
				{
					// The user manually opened the drawer; store this flag to prevent auto-showing
					// the navigation drawer automatically in the future.
					mUserLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(FullImageActivity.this);
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).commit();
				}

				supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}
		};


		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable()
		{
			@Override
			public void run()
			{
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}


	public void onNavigationDrawerItemSelected(int position)
	{
		// update the main content by replacing fragments

		switch (position)
		{
		case 0:
			Intent wallIntent = new Intent(FullImageActivity.this, MainActivity.class);
			wallIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			wallIntent.putExtra(SELECTED_POSITION, position);
			startActivity(wallIntent);
			overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			break;
		case 1:
			Intent accountIntent = new Intent(FullImageActivity.this, FirstActivity.class);
			accountIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			accountIntent.putExtra(SELECTED_POSITION, position);
			startActivity(accountIntent);
			overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			break;
			// feedback
		case 2:
			Intent feedbackIntent = new Intent(FullImageActivity.this, CheckListActivity.class);
			feedbackIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			feedbackIntent.putExtra(SELECTED_POSITION, position);
			startActivity(feedbackIntent);
			overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			break;

		case 3:
			// about
			Intent aboutIntent = new Intent(FullImageActivity.this, GridViewActivity.class);
			aboutIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			aboutIntent.putExtra(SELECTED_POSITION, position);
			startActivity(aboutIntent);
			overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			break;
			// FAQ
		case 4:
			Intent faqIntent = new Intent(FullImageActivity.this, ThirdActivity.class);
			faqIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			faqIntent.putExtra(SELECTED_POSITION, position);
			startActivity(faqIntent);
			overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
			break;

		}

	}

}
