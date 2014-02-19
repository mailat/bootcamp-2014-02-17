package com.intel.yamba;

import com.marakana.android.yamba.clientlib.YambaClient;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener{
	private static final String TAG = "Yamba";

	YambaClient cloudPost = null;
	SharedPreferences prefs;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//prepare the preferences
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		// TODO some cleanup
	}
	
	/**
	 * get the actual YambaClient or a new one in case the credentials are changed
	 * @return - YambaClient
	 */
	public synchronized YambaClient getYambaClient()
	{
		if (cloudPost == null)
		{
			//read the credentials from the preferences
			String username = prefs.getString("username", "") ;
			String password = prefs.getString("password", "") ;
			String apiRoot = prefs.getString("apiRoot", "http://yamba.marakana.com/api") ;
			
			//create the YambaClient
			cloudPost = new YambaClient(username, password, apiRoot);
		}
		
		return (cloudPost);
	}

	@Override
	public synchronized void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		//preferences are changed, cloudpost must be recreated
		cloudPost = null;
	}
}
