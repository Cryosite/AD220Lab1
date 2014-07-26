package com.mti.ad220lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends ActionBarActivity implements OnClickListener, OnItemSelectedListener {
//Layout Controls
	//App navigation
	private Button btnReturn;
	private Spinner spnrMode;
	//Circle Count
	private TextView tvCircleCount;
	private int circleCount;
	private Button btnPlus;
	private Button btnMinus;
	//Circle Size
	private TextView tvSize;
	private Button btnSizePlus;
	private Button btnSizeMinus;
	private int size;
	
//Shared Preferences implementation.
	private SharedPreferences settings;
	private Editor editor;
//planned settings
	//Speed float
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	//Layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		//app Navigation
		btnReturn = (Button) findViewById(R.id.btnSettingsReturn);
		btnReturn.setOnClickListener(this);
		spnrMode = (Spinner) findViewById(R.id.spnrSettingsMode);
		spnrMode.setOnItemSelectedListener(this);
		//Circle Count
		tvCircleCount = (TextView) findViewById(R.id.tvSettingsCircleCount);
		btnPlus = (Button) findViewById(R.id.btnSettingsPlus);
		btnPlus.setOnClickListener(this);
		btnMinus = (Button) findViewById(R.id.btnSettingsMinus);
		btnMinus.setOnClickListener(this);
		//Circle Size
		tvSize = (TextView) findViewById(R.id.tvSettingsSize);
		btnSizePlus = (Button) findViewById(R.id.btnSettingsSizePlus);
		btnSizePlus.setOnClickListener(this);
		btnSizeMinus = (Button) findViewById(R.id.btnSettingsSizeMinus);
		btnSizeMinus.setOnClickListener(this);
		
	//Shared Preferences implementation
		settings = getSharedPreferences("SavedValues", MODE_PRIVATE);
		editor = settings.edit();
	}//onCreate(Bundle savedInstanceState)
	
	@Override
	public void onResume() {
		super.onResume();
		circleCount = settings.getInt("CircleCount", 1);
		size = settings.getInt("Size", 1);
		tvCircleCount.setText("Circles per touch: " + circleCount);
		tvSize.setText("Size of circles: " + size);
	}//onResume()

	@Override
	public void onClick(View v) {
		//return 
		if (v.getId() == R.id.btnSettingsReturn) {
			Intent openMain = new Intent(SettingsActivity.this, MainActivity.class);
			
			Bundle myBundle = new Bundle();
			openMain.putExtras(myBundle);
			
			SettingsActivity.this.startActivity(openMain);
		}//endif - (v.getId() == R.id.btnSettingsReturn)

		//Circle Count
		boolean noSpam = true;
		if (v.getId() == R.id.btnSettingsPlus) {
			circleCount++;
			editor.putInt("CircleCount", circleCount);
			editor.commit();
			tvCircleCount.setText("Circles per touch: " + circleCount);
			noSpam = true;
		}//endif - (v.getId() == R.id.btnSettingsPlus)
		if (v.getId() == R.id.btnSettingsMinus) {
			if (circleCount == 1 && noSpam) {
				Toast.makeText(this, "Minimum value is 1", Toast.LENGTH_SHORT).show();
				noSpam = false;
			}//endif - (circleCount == 1 && noSpam)
			else {
				circleCount--;
				editor.putInt("CircleCount", circleCount);
				editor.commit();
				tvCircleCount.setText("Circles per touch: " + circleCount);
				noSpam = true;
			}//endelse - (circleCount == 1)
		}//endif - (v.getId() == R.id.btnSettingsMinus)
		
		//Size
		if (v.getId() == R.id.btnSettingsSizePlus) {
			if (size == 10) {
				Toast.makeText(this, "Maximum value is 10", Toast.LENGTH_SHORT).show();
				noSpam = false;
			}//endif - (size == 10)
			else {
				size++;
				editor.putInt("Size", size);
				editor.commit();
				tvSize.setText("Size of circles: " + size);
				noSpam = true;
			}//endelse - (size == 10)
		}//endif - (v.getId() == R.id.btnSettingsSizePlus)
		if (v.getId() == R.id.btnSettingsSizeMinus) {
			if (size == 1 && noSpam) {
				Toast.makeText(this, "Minimum value is 1", Toast.LENGTH_SHORT).show();
				noSpam = false;
			}//endif - (size == 1 && noSpam)
			else {
				size--;
				editor.putInt("Size", size);
				editor.commit();
				tvSize.setText("Size of circles: " + size);
				noSpam = true;
			}//endelse - (size == 1)
		}//endif - (v.getId() == R.id.btnSettingsSizePlus)
		
	}//onClick(View v)

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		
		editor.putInt("Mode", spnrMode.getSelectedItemPosition());
		editor.commit();
		Toast.makeText(this, "Mode selected: " + settings.getInt("Mode", 5), Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}//class
