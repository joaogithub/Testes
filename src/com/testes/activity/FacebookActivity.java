package com.testes.activity;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.plus.Plus;
import com.testes.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FacebookActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener{

	public static final String TAG = "FacebookActivity";
	
	private UiLifecycleHelper uiHelper;
	private static final int REQUEST_CODE_RESOLVE_ERR = 1;
	GoogleApiClient mGoogleClient;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.facebook_layout);
		uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    //start profile page
//		startActivity(getFBIntent(this, "joaoamaro.silva.1"));
		
	}
	
	
	public static Intent getFBIntent(Context context, String facebookId) {
        try {
            // Check if FB app is even installed
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            String facebookScheme = "fb://profile/" + facebookId; //also tried with /page
            Log.i("FB_LINK", facebookScheme);
            return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
        }
        catch(Exception e) {
            // Cache and Open a url in browser
            String facebookProfileUri = "https://www.facebook.com/" + facebookId;
            return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUri));
        }
    }
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	
	public void share(View v){
		mGoogleClient = new GoogleApiClient.Builder(this)
	    .addApi(Drive.API)
	    .addScope(Drive.SCOPE_FILE)
	    .addScope(Plus.SCOPE_PLUS_LOGIN)
	    .addConnectionCallbacks(this)
	    .addOnConnectionFailedListener(this)
	    .build();
		
		mGoogleClient.connect();
	}


	@Override
	public void onConnected(Bundle connectionHint) {
		Log.i(TAG, connectionHint.toString());
	}


	@Override
	public void onConnectionSuspended(int cause) {
		Log.i(TAG, "suspended: "+ cause);
		
	}
	
	@Override
	public void onConnectionFailed(ConnectionResult result) {
	    Log.e(TAG,"GoogleClient connection failed!");
	    Log.e(TAG, "result: "+ result.getErrorCode());
	    if (result.hasResolution()) {
	        try {
	            result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
	        } catch (SendIntentException e) {
	            mGoogleClient.connect();
	        }
	    }
	    // Speichern Sie das Ergebnis und beheben Sie den Verbindungsfehler bei einem Klick des Nutzers.
	   ConnectionResult mGoogleConnectionResult = result;

	}
	
}
