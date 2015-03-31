package com.testes.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

import com.testes.android.R;

public class DownloadActivity extends ActionBarActivity implements OnClickListener{

	private DownloadManager manager;
	private DownloadManager.Request request;
	private String stringUrl;
	private int downloadId;
	private CheckBox download_state;
	private int dl_progress;
	private String [] stockArr;
	private int count;
	private TextView downloadCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_download);

		findViewById(R.id.downloadManagerButton).setOnClickListener(this);
		stringUrl = "http://www.novobanco.pt/site/cms.aspx?srv=207&stp=1&id=715642&fext=.pdf";

		manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		
		
	}

	@Override
	public void onClick(View v) {
		Log.i("TAG", "clicked");
		switch (v.getId()) {
		case R.id.downloadManagerButton:

			test();

			break;

		default:
			break;
		}

	}

	@SuppressLint("NewApi")
	public void test(){

		final int x=3;
		request = new Request(Uri.parse(stringUrl));
		request.setDestinationInExternalFilesDir(this, "/folder", "example.pdf")
		//VISIBILITY_HIDDEN gives exception with no permission
		.setNotificationVisibility(Request.VISIBILITY_VISIBLE)
		.setVisibleInDownloadsUi(false);
		manager.enqueue(request);
		final Timer myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@SuppressLint("NewApi") @Override
			public void run() {
				DownloadManager.Query query = new DownloadManager.Query();
//				query.setFilterById(downloadId);
				Cursor cursor = manager.query(query);
				if(cursor.moveToFirst()){
					long bytes_downloaded = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
					long bytes_total = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
					try {
						long dl_progress = (bytes_downloaded * 100)/bytes_total;
						//checkStatus(Const.cursor);
					} catch(ArithmeticException a){
						//						checkStatus(cursor);
						boolean mbool = false;
						download_state.setChecked(mbool);
						myTimer.cancel();
					}
					cursor.close();
					Log.i("download_progress-->",""+dl_progress);
					if(bytes_downloaded == bytes_total){
						if(stockArr.length == x){
							Intent i = new Intent(DownloadActivity.this, ScrollViewActivity.class);
							startActivity(i);
						}else{
							int newthumb = 1;
							if(newthumb == 1){

								//				int mineFile = fm.moveFileTointernal(this, "/folder", "example.pdf");
								//				if(mineFile == 1){

								count++;
								runOnUiThread(new Runnable(){
									@Override
									public void run(){
										downloadCount = (TextView) findViewById(R.id.txtDownloadCount);
										downloadCount.setText("Downloaded "+count+" of  3");
										Log.d("",""+"Downloaded "+count+" of 3");
									}
								});
								//					test();
								//				}

							}
						}
					}

				}
				cursor.close();
			}

		}, 0, 95000);
		
	}

}
