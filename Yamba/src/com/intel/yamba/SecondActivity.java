package com.intel.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//setup a new layout in the code
		//setContentView(R.layout.second_layout);
		
		//declarative code for layouting
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	
		TextView textView = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		textView.setText("test");
		linearLayout.addView(textView);
		setContentView(linearLayout);
		
		Log.d("Yamba", "I am in the second activity");


	}

}
