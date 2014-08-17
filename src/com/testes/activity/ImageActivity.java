package com.testes.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Attendees;
import android.provider.CalendarContract.Instances;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Track;
import com.testes.android.R;

public class ImageActivity extends ActionBarActivity{

	String buttonText= "";
	LinearLayout viewToInject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_weight);

		//		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)  mViewHolder.startDate.getLayoutParams();
		//	    RelativeLayout.LayoutParams paramsImage = (RelativeLayout.LayoutParams) imgView.
		//	   double conversion = minute*2.7;
		//	   int topmargin = (int)conversion;
		//	  // params.setMargins(0,topmargin,0,0);
		//	   params.topMargin = topmargin;
		//	   paramsImage.topMargin = topmargin+15;
		SimpleDateFormat df = new SimpleDateFormat("h:mm a");
		String date = df.format(Calendar.getInstance().getTime());
		//	       if(hour == myList.get(position).getPosition())
		//	       {
		//	           mViewHolder.startDate.setLayoutParams(params);
		//	           mViewHolder.startDate.setTextColor(Color.BLUE);
		//	           mViewHolder.startDate =detail(convertView, R.id.datetitle, date);
		//	           if(imgView != null)
		//	               imgView.setLayoutParams(paramsImage);
		//	               imgView.setVisibility(1);
		//	                imgView.bringToFront();
		//	       }
		//	       else{
		//	           params.topMargin=0;
		//	           mViewHolder.startDate.setLayoutParams(params);
		//	           mViewHolder.startDate.setTextColor(Color.BLACK);
		//	           imgView.setVisibility(View.INVISIBLE);
		//	       }

		ArrayList<String> instanceIdList = new ArrayList<String>();
		ArrayList<Long> startDateList = new ArrayList<Long>();
		ContentResolver resolver = getContentResolver();

		String[] EVENT_PROJECTION = new String[] 
				{
				Instances.TITLE,
				Instances.BEGIN,
				Instances.END,
				Instances.EVENT_ID,
				Instances.RRULE,
				Instances.RDATE
				};


		Cursor cur = Instances.query(resolver, EVENT_PROJECTION, System.currentTimeMillis(), System.currentTimeMillis()+3*24*60*60*1000);

		// Use the cursor to step through the returned records
		while (cur.moveToNext()) 
		{
			String eventTitle = cur.getString(cur.getColumnIndex(Instances.TITLE));
			long eventStartDt = cur.getLong(cur.getColumnIndex(Instances.BEGIN));
			long eventEndDt = cur.getLong(cur.getColumnIndex(Instances.END));
			long instanceID = cur.getLong(cur.getColumnIndex(Instances.EVENT_ID));
			String rrule = cur.getString(cur.getColumnIndex(Instances.RRULE));
			String rdate = cur.getString(cur.getColumnIndex(Instances.RDATE));
			Log.i("calendarActivitu", rrule + " date:"+ rdate);
				instanceIdList.add(String.valueOf(instanceID));
				startDateList.add(eventStartDt);
				//				    		            instanceEnd.add(eventEndDt);
			
		}

		ArrayList<String> selectedNames  = new ArrayList<String>();
		ArrayList<String> emailList  = new ArrayList<String>();
		emailList.add("joao_amarosilva@hotmail.com");
		emailList.add("patriccia@hotmial.com");
		emailList.add("marco@hotmial.com");
		selectedNames.add("Joao");
		selectedNames.add("Patricia");
		selectedNames.add("Marco");

		int countOuter = 0;
		int countInner = 0;
		int numberOfAttendees = selectedNames.size()-1;      //selectedNames: the chosen attendees

		// If only one attendee is chosen, add it to the event id
		if(selectedNames.size() == 1)
		{
			ContentValues attendees = new ContentValues();
			attendees.put(Attendees.ATTENDEE_EMAIL, emailList.get(0));
			attendees.put(Attendees.EVENT_ID, 52);
			Uri uriAttendees = resolver.insert(Attendees.CONTENT_URI, attendees);
			uriAttendees.buildUpon();
		}
		//multiple attendees to assign
		else
		{
			if(!instanceIdList.isEmpty())
			{
				while(instanceIdList.size() > countOuter)
				{       
					ContentValues attendees = new ContentValues();

					// Add one attendee from list to one instance of an event
					while(countInner < 1)
					{
						attendees.put(Attendees.EVENT_ID, instanceIdList.get(countOuter));
						attendees.put(Attendees.ATTENDEE_EMAIL,emailList.get(numberOfAttendees));
						attendees.put(Attendees.ATTENDEE_NAME, selectedNames.get(countOuter));
						attendees.put(Attendees.ATTENDEE_TYPE, Attendees.TYPE_OPTIONAL);
						countInner++;
						numberOfAttendees--;

						// If all attendees in list has been assigned and 
						// there are still more instances, start assigning to 
						// the attendees in the list again
						if(numberOfAttendees == -1)
						{
							numberOfAttendees = selectedNames.size()-1;
						}
					}
					countInner = 0;
					countOuter++;
					Uri uriAttendees = resolver.insert(Attendees.CONTENT_URI, attendees);
					uriAttendees.buildUpon();
				}
			}
		}
		
		new EchoTask().execute();
		
		
	}

	public class EchoTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			
			String[] args={"/storage/sdcard0/Music/halo.mp3"};
	        File file = new File(args[0]);

	        if(!file.canRead())
	        {
	            System.out.println("Insert a valid path!");
	        }

	        EchoNestAPI echoNest = new EchoNestAPI("XLNN9CZXKLXYFC66X");
	        System.out.println("hello!");
	        
	        Track track = null;
			try {
				track = echoNest.uploadTrack(file);
			} catch (EchoNestException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

	        System.out.println("uploaded!");
	        try {
				track.waitForAnalysis((60*1000)/2);
			} catch (EchoNestException e) {
				
				e.printStackTrace();
			}


	        System.out.println("ID: "+track.getID());
	        try {
				System.out.println("Artist: "+track.getArtistName());
			} catch (EchoNestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				System.out.println("Title: "+track.getTitle());
			} catch (EchoNestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
}



