package com.intel.yamba;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity{
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
		// we got reference to the EditText and we read the status
		EditText editText = (EditText) findViewById(R.id.editText);
		String editTextPost = editText.getText().toString();

		//call the AsyncTask poster to send the text
		new PostToTwitter().execute(editTextPost);
	}

	/**
	 * postToTwitter
	 * post in an AsyncTask the information to Yamba.Marakana.com
	 *
	 */
	class PostToTwitter extends AsyncTask<String, Integer, String>{
		private ProgressDialog progress;

		@Override
		protected String doInBackground(String ... status) {
			//start posting on twitter
			try {
				((YambaApplication) getApplication()).getYambaClient().postStatus(status[0]);
				return "Posting was ok";
			} catch (Throwable e) {
				e.printStackTrace();
				Log.d("Yamba", e.getMessage());
				return "Failed to post";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			progress.dismiss();
			Toast.makeText(StatusActivity.this, "Posting was done!", Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(StatusActivity.this, "Posting to yamba server", "Please wait ....");
		}

	};
	
	/**
	 * display the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

	/**
	 * if we click on the items in the menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//distribute the calls from the menus
		switch (item.getItemId())
		{
			case R.id.action_settings:
				startActivity(new Intent(this, PrefsActivity.class));
				break;
			case R.id.action_servicestart:
				startService(new Intent(this, UpdaterService.class));
				break;
			case R.id.action_servicestop:
				stopService(new Intent(this, UpdaterService.class));
				break;			
		}
		
		return true;
	}

}
