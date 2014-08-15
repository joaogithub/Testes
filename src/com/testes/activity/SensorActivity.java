package com.testes.activity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testes.android.R;

public class SensorActivity extends ActionBarActivity{

	String buttonText= "";
	ImageView startImage;
	LinearLayout viewToInject;
	TextView angleText;
	View line;

	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor magnetometer;
	private Sensor gyroscope;
	float degreesSoItIsParallelToTheGround=0f;
	String phoneRotationDegrees="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.sensor_activity);


		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

		line = findViewById(R.id.lineView);
		angleText = (TextView) findViewById(R.id.angleText);

	}



	@Override
	protected void onPause() {

		super.onPause();
		sensorManager.unregisterListener(sensorEventListenerer);
	}

	@Override
	protected void onResume() {

		super.onResume();
		Log.i("SENSOR", "Accelerometer enabled: "+sensorManager.registerListener(sensorEventListenerer, accelerometer, SensorManager.SENSOR_DELAY_NORMAL));
		Log.i("SENSOR", "Magnetometer enabled: "+sensorManager.registerListener(sensorEventListenerer, magnetometer, SensorManager.SENSOR_DELAY_NORMAL));
		Log.i("SENSOR", "Gyroscope enabled: "+sensorManager.registerListener(sensorEventListenerer, gyroscope, SensorManager.SENSOR_DELAY_NORMAL));
	}


	private SensorEventListener sensorEventListenerer = new SensorEventListener() {
		@SuppressLint("NewApi")
		@Override
		public void onSensorChanged(SensorEvent event) {

			/*
			 * Here i want to do my calculations 
			 * so i can find what rotation the line should have
			 *
			
			 */
			
			switch(event.sensor.getType()){
			case 1:
				Log.i("Type", "ACCELEROMETER");
				break;
			case 4:
				Log.i("Type", "Gyroscope");
				break;
				
				default:
					Log.i("Type", ""+event.sensor.getType());
			}
			
			Log.i("EVENT", event.sensor.getName() + " vendor: "+event.sensor.getVendor() );

			final float alpha = 0.8f;

			float [] gravity = new float[3];
			float linear_acceleration[] = new float[3];
			
			gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

			linear_acceleration[0] = event.values[0] - gravity[0];
			linear_acceleration[1] = event.values[1] - gravity[1];
			linear_acceleration[2] = event.values[2] - gravity[2];


			angleText.setText(phoneRotationDegrees);
			line.setRotation(degreesSoItIsParallelToTheGround);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}
