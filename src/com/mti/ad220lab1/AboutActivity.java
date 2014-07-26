package com.mti.ad220lab1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends ActionBarActivity {
	
	private SharedPreferences settings;
	private int mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences("SavedValues", MODE_PRIVATE);
		mode = settings.getInt("Mode", 0);
		switch (mode) {
		case 0:
			setContentView(R.layout.activity_about);
			break;
		case 1:
			setContentView(R.layout.activity_about_text);
			TextView tvHelp = (TextView) findViewById(R.id.tvAboutTextHelp);
			String help = "Tips:\n"
					+ "   Close the soft keyboard to access the controls.\n"
					+ "   Pay attention to what file name you have selected for saving, loading, and delete operations. There are no confirmation windows for any of them.";
			tvHelp.setText(help);
			break;
		default:
			setContentView(R.layout.activity_about);
		}//endswitch - (mode)
		
	}//onCreate(Bundle savedInstanceState)

}//class
