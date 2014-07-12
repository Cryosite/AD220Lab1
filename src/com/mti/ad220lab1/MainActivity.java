package com.mti.ad220lab1;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnTouchListener, SensorEventListener {
	private DrawingBoard db;
	private SensorManager sm;
	private SharedPreferences settings;
	//set by user settings.
	private int circleCount;
	private int radius;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DrawingBoard(this);
		setContentView(db);
		settings = getSharedPreferences("SavedValues", MODE_PRIVATE);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		db.setOnTouchListener(this);
		Toast.makeText(this, "New Game. Touch screen to add circles.", Toast.LENGTH_SHORT).show();
	}//onCreate(Bundle savedInstanceState)
	
	@Override
	protected void onPause() {
		sm.unregisterListener(this);
		super.onPause();
	}//onPause()
	
	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		 circleCount = settings.getInt("CircleCount", 1);
		 radius = settings.getInt("Size", 1);
	}//onResume()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}//onCreateOptionsMenu(Menu menu)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//launch settings or about fragment.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent openSettings = new Intent(MainActivity.this, SettingsActivity.class);
			
			Bundle myBundle = new Bundle();
			openSettings.putExtras(myBundle);
			
			MainActivity.this.startActivity(openSettings);
			return true;
		}//endif - (id == R.id.action_settings)
		if (id == R.id.action_about) {
			Intent openAbout = new Intent(MainActivity.this, AboutActivity.class);
			
			Bundle myBundle = new Bundle();
			openAbout.putExtras(myBundle);
			
			MainActivity.this.startActivity(openAbout);
			return true;
		}//endif - (id == R.id.action_about)
		return super.onOptionsItemSelected(item);
	}//onOptionsItemSelected(MenuItem item)

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (int i = 0; i<circleCount; i++) {
				Circle aCircle = new Circle(event.getX(), event.getY(), db, radius);
				db.addCircle(aCircle);
			}//endfor - (int i = 1; i>circleCount; i++)	
		}//endif - (event.getAction() == MotionEvent.ACTION_DOWN)
		return true;
	}//onTouch(MotionEvent event)

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//stub
	}//onAccuracyChanged(Sensor sensor, int accuracy)

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && 
				getAccelerometer(event) >= 3 && 
				db.circleCount() > 0) {
			ArrayList<Circle> circles = db.getCircles();
			for (Circle circle : circles) {
				circle.accelerate(getAccelerometer(event));
			}//endfor - (Circle circle : circles)
		}//endif - (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && getAccelerometer(event))

	
	}//onSensorChanged(SensorEvent event)
	
	private float getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (float) ((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2))
				/ (Math.pow(SensorManager.GRAVITY_EARTH, 2)));

		return accelationSquareRoot; 	
	}//getAccelerometer(SensorEvent event)
}//class
