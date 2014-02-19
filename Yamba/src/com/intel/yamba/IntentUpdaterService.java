package com.intel.yamba;

import java.util.List;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class IntentUpdaterService extends IntentService {

	public IntentUpdaterService() {
		super("IntentUpdaterService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		//get the list of tweets
		List<Status> timeline;
		try {
			timeline = ((YambaApplication) getApplication() ).getYambaClient().getTimeline(20);
			
			 //parse the values
			 for (YambaClient.Status status : timeline)
			 {
				 Log.d("Yamba", status.getUser() + ": " + status.getMessage());
			 }
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

}
