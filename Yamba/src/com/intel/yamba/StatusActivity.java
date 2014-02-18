package com.intel.yamba;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity {
	TextView textCounter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		Log.d("Yamba", "onCreate");
		
		//System.setProperty("http.proxyHost", "prox here");
		//System.setProperty("http.proxyPort", "port here");
		
		Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonUpdate.setOnLongClickListener(eventOnLongClickListener);
		
		//get a reference to the editText and the textCounter
		EditText editText = (EditText) findViewById(R.id.editText);
		textCounter = (TextView) findViewById(R.id.textCounter);
		
		//add a listener for text change
		editText.addTextChangedListener(textWatcher);
		textCounter.setTextColor(Color.GREEN);
		
	}
	
    //create the listener for text changes
    TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		
		@Override
		public void afterTextChanged(Editable statusText) {
			int count = 140 - statusText.length();
			textCounter.setText(Integer.toString(count) + " chars left");
			textCounter.setTextColor(Color.GREEN);
			if (count == 0)
				textCounter.setTextColor(Color.RED);
			else if (count < 20)
				textCounter.setTextColor(Color.YELLOW);

		}
	};
	
  private OnLongClickListener eventOnLongClickListener = new OnLongClickListener() {
    	public boolean onLongClick(View v) {
    		Toast.makeText(StatusActivity.this, "onLongClick was pressed", Toast.LENGTH_SHORT).show();
			return true;
    	}    	
    }; 
	    
	public void buttonUpdateClick(View v)
	{
		// we got reference to the EditText
		EditText editText = (EditText) findViewById(R.id.editText);
		String editTextPost = editText.getText().toString();

		//TODO just post on twitter
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

}
