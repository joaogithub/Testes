package com.testes.services;

import com.testes.activity.MainActivity;
import com.testes.android.R;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmService extends IntentService {

	private NotificationManager alarmnotificationManager;

	public AlarmService(){
		super("AlarmService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		sendNotification("Get up! Get up!");

	}
	private void sendNotification(String msg){

		Log.i("AlarmService","Sending notification...:"+msg);
		alarmnotificationManager=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
				this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);


		alamNotificationBuilder.setContentIntent(contentIntent);
		alarmnotificationManager.notify(1, alamNotificationBuilder.build());
		Log.d("AlarmService", "Notification sent.");

	}

}
