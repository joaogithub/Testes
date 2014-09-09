package com.testes.activity;

import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.MapFragment;
import com.testes.android.R;
import com.testes.data.Person;

public class SecondActivity extends ActionBarActivity{

	private static TextView myView, myOtherView;
	CheckBox checkbox;

	Person person = new Person();

	public Person getPerson() {
		return person;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_place_activity);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceHolderFragment())
			.commit();
		}
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//	            actionBar.hide();

		person.name= "John";

		checkbox = (CheckBox) findViewById(R.id.checkbox_meat);
		String path = "android.resource://" + getPackageName() + "/" + R.raw.music1; //set up MediaPlayer
		MediaPlayer mediaPlayer = MediaPlayer.create(SecondActivity.this, R.raw.music1);

		try {
//			mediaPlayer.setDataSource(path); 
//			mediaPlayer.prepare(); 
//			mediaPlayer.setLooping(true); 
//			mediaPlayer.setVolume(100, 100);
			mediaPlayer.start(); 
		}
		catch (Exception e) {
			e.printStackTrace(); 
			Log.d("NOT WORKING","NOT WORKING"); 
		}

		//		Button buttonFade = (Button) findViewById(R.id.operateButton);
		ImageView image = (ImageView) findViewById(R.id.helloWorldImage);
		((GradientDrawable) image.getDrawable()).setColor(Color.RED);
		//        final Animation animationFadeIn = AnimationUtils.loadAnimation(this,
		//                android.R.anim.fade_in);
		//        final Animation animationFadeOut = AnimationUtils.loadAnimation(this,
		//                android.R.anim.fade_out);
		//        
		//        buttonFade.setOnClickListener(new Button.OnClickListener() {
		//            int i = 0;
		//            @Override
		//            public void onClick(View arg0) {
		//
		//                if (i == 0) {
		//                    image.startAnimation(animationFadeOut);
		//                    i++;
		//                } else {
		//                    image.startAnimation(animationFadeIn);
		//                    i--;
		//                }
		//            }
		//        });
	}
	//
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu){
	        MenuInflater inflater = getMenuInflater();
//	        inflater.inflate(R.menu.block, menu);
	        inflater.inflate(R.menu.main_activity_actions, menu);
	        return super.onCreateOptionsMenu(menu);
	    }
	    
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	    	return super.onPrepareOptionsMenu(menu);
	    }
	//
	//    @Override
	//    public boolean onOptionsItemSelected(MenuItem item) {
	//    	return super.onOptionsItemSelected(item);
	//    }


	/**
	 * A replacement fragment containing a simple view.
	 */
	public static class ReplaceFragment extends Fragment {

		public ReplaceFragment() {
		}

		Person fragPerson;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_replace, container, false);
			myOtherView = (TextView) rootView.findViewById(R.id.textViewReplace);

			SecondActivity activity = (SecondActivity) getActivity();
			fragPerson = activity.getPerson();
			System.out.println(fragPerson.name + " act: "+ activity.person.name);
			myView.setText(activity.person.name);

			String storyText = "My previous text";
			storyText = "<b>" + storyText + "</b> "+ " " + "<b>" + "added now text" + "</b>" + " ";
			storyText.replaceAll("\n", "<br />");
			Spanned spanned = Html.fromHtml(storyText);
			String text = Html.toHtml(spanned);

			text = storyText.replace(storyText.substring(storyText.indexOf("<b>"), storyText.indexOf("</b>")+3), "xx");

			myView.setText(spanned);
			Log.i("gettypeface", ""+myView.getTypeface());

			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
		}
		
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceHolderFragment extends Fragment {

		public PlaceHolderFragment() {
		}

		Person fragPerson;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			myView = (TextView) rootView.findViewById(R.id.textView1);

			SecondActivity activity = (SecondActivity) getActivity();
			fragPerson = activity.getPerson();
			System.out.println(fragPerson.name + " act: "+ activity.person.name);
			myView.setText(activity.person.name);



			String storyText = "My previous text";
			storyText = "<b>" + storyText + "</b> "+ " " + "<b>" + "addednowtext" + "</b>" + " ";
			storyText.replaceAll("\n", "<br />");
			Spanned spanned = Html.fromHtml(storyText);
			myView.setText(spanned);

			myView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Fragment signupFragment = new ReplaceFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(((ViewGroup)getView().getParent()).getId(), signupFragment).commit();

					MapFragment mapf = (MapFragment) MapFragment.newInstance();
					GoogleMap mapView = mapf.getMap();
					mapView.setOnMapLoadedCallback(new OnMapLoadedCallback() {

						@Override
						public void onMapLoaded() {
							Log.i("MAP LOADDEE", "MAP IS READY");

						}
					});

					//					transaction.add(((ViewGroup)getView().getParent()).getId(), mapf, "TAG").commit();

				}
			});



			return rootView;
		}


		public void onViewCreated(View v, Bundle savedInstanceState) {
			super.onViewCreated(v, savedInstanceState);

			SharedPreferences activitiesFile = getActivity().getSharedPreferences("Activities", 0);
			activitiesFile.edit().putString("name", "me").commit();
			Set<String> keylist = activitiesFile.getAll().keySet();
			for (String s : keylist) {
				String active = activitiesFile.getString(s, "");
				Button activeName=new Button(getActivity());
				activeName.setText(active);
				activeName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
						LayoutParams.WRAP_CONTENT));
				LinearLayout layout=(LinearLayout) v.findViewById(R.id.activityList);
				activeName.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//	                    FillInInfo(v);
					}
				});
				layout.addView(activeName);

			}

		}
	}

	public void press(View view){
		Log.i("SecondActivity", "Pressed");
		myView.setText("Changed");

	}

	public void onCheckBoxClicked(View view){
		boolean checked = ((CheckBox) checkbox).isChecked();

		if(checked){
			Intent myIntent = new Intent(SecondActivity.this, ThirdActivity.class);
			startActivity(myIntent);
		}
	} 
}