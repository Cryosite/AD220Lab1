package com.mti.ad220lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends ActionBarActivity implements OnClickListener {
	private Button btnContinue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		btnContinue = (Button) findViewById(R.id.btnStartContinue);
		btnContinue.setOnClickListener(this);
		
	}//onCreate(Bundle savedInstanceState)

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnStartContinue) {
			Intent beginMain = new Intent(StartActivity.this, MainActivity.class);
			
			Bundle myBundle = new Bundle();
			beginMain.putExtras(myBundle);
			
			StartActivity.this.startActivity(beginMain);
			
		}//endif - (v.getId() == R.id.btnStartContinue)
	}//onClick(View v)

}//class
