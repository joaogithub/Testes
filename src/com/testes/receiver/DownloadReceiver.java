package com.testes.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownloadReceiver extends BroadcastReceiver{

	private final static String TAG = "DownloadReceiver";	

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i(TAG, "Download finished " + intent.toString());
	}

}