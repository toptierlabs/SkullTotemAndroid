package com.toptier;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//End and Repeat buttons activity
public class EndButtonsActivity extends Activity implements OnClickListener{
	
	Button repeat,exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endbuttons);
		
		repeat = (Button) findViewById(R.id.bRepeat);
		exit = (Button) findViewById(R.id.bExit);
		
		//Set event to the buttons
		repeat.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bExit:
			//If button exit then return OK and finish the activity 
			setResult(RESULT_OK);
			this.finish();
			break;
		case R.id.bRepeat:
			//Restart the App
			Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
		
		}
	}

}
