package com.testes.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class WifiScanReceiver extends BroadcastReceiver{

	@Override
    public void onReceive(Context context, Intent intent) {     
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) 
            Log.d("WifiReceiver", "Wifi Connection is enabled");
        else
            Log.d("WifiReceiver", "No Wifi Connection found");    
    }   

}
