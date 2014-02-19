package com.intel.yamba;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//load the layout of the preferences
		addPreferencesFromResource(R.xml.prefs);
	}

}
