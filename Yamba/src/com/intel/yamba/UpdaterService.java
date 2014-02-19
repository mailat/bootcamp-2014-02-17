package com.intel.yamba;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("Yamba", "service onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("Yamba", "service onDestroy");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d("Yamba", "service onStartCommand");
		return START_STICKY;
	}

}
