package com.intel.yamba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//start the IntentUpdaterService
		Log.d("Yamba", "I am in receiver - onReceive().");
		context.startService(new Intent(context, IntentUpdaterService.class));
	}

}
