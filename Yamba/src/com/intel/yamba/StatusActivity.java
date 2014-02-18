package com.intel.yamba;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		Log.d("Yamba", "onCreate");
		
		Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonUpdate.setOnLongClickListener(eventOnLongClickListener);
	}
	
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

		//we log the sent message
		Log.d("Yamba", editTextPost);
		
		//we display the sent message
		Toast.makeText(StatusActivity.this, editTextPost, Toast.LENGTH_LONG).show();
		
		//redirect to the second activity
		Log.d("Yamba", "startActivity start");
		startActivity(new Intent(StatusActivity.this, SecondActivity.class));
		Log.d("Yamba", "startActivity end");
		//finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

}
