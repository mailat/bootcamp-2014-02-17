package com.intel.yamba;

import java.util.List;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {
	boolean runFlag = false;
	private Updater updater;
	private YambaApplication yamba;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		//create the updater
		this.yamba = (YambaApplication) getApplication();
		this.updater = new Updater();
		
		Log.d("Yamba", "service onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("Yamba", "service onDestroy");
		this.runFlag = false;
		
		//stop the worker thread and help in making a better world
		this.updater.interrupt();
		this.updater = null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		Log.d("Yamba", "service onStartCommand");
		
		//we start the updater
		
		if ( this.runFlag == false )
		{
			this.runFlag = true;
			this.updater.start();
		}

		return START_STICKY;
	}
	
	private class Updater extends Thread{
		
		public Updater()
		{
			super("UpdaterService-Updater");
		}

		@Override
		public void run() {
			//we do here the heavy lifting
			UpdaterService updaterService = UpdaterService.this;
			
			while (updaterService.runFlag)
			{
				//get last 20 posts from yamba.marakana.com
				try {
					Log.d("Yamba", "we do heavy lifting in run()");
					
					 List<Status> timeline = yamba.getYambaClient().getTimeline(20);
					 
					 //parse the values
					 for (YambaClient.Status status : timeline)
					 {
						 Log.d("Yamba", status.getUser() + ": " + status.getMessage());
					 }
					
					//we sleep 1 min
					Thread.sleep(60000);
				} catch (Throwable e) {
					e.printStackTrace();
					//Log.d("Yamba", e.getMessage());
					updaterService.runFlag = false;
				}
			}
		}
	}

}
