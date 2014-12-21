package com.testes.activity;

import com.testes.android.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_activity_layout);

		VideoView mVideoView = (VideoView) findViewById(R.id.videoView);


		//		String path = "https://www.somethingvideo.com/yplay"; // The link i know
		//
		//		mVideoView.setVideoPath(path);
		mVideoView.setMediaController(new MediaController(this));

		Uri video = Uri.parse("android.resource://" + getPackageName() + "/" 
				+ R.raw.testmpeg); //do not add any extension
		//if your file is named sherif.mp4 and placed in /raw
		//use R.raw.sherif
		mVideoView.setVideoURI(video);

		mVideoView.requestFocus();
		mVideoView.start();
	}



}
