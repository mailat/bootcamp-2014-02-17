package com.intel.yamba;

import java.util.List;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class IntentUpdaterService extends IntentService {

	public IntentUpdaterService() {
		super("IntentUpdaterService");
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		//database reference
		DbHelper dbHelper = new DbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		//get the list of tweets
		List<Status> timeline;
		try {
			timeline = ((YambaApplication) getApplication() ).getYambaClient().getTimeline(20);
			
			ContentValues contentValues = new ContentValues();
			 //parse the values
			 for (YambaClient.Status status : timeline)
			 {
				 Log.d("Yamba", status.getUser() + "-" + status.getMessage() + "-" + status.getCreatedAt().getTime());
				 
				 //use ContentValues for inserting data
				 contentValues.clear();
				 contentValues.put(DbHelper.C_ID, status.getId());
				 contentValues.put(DbHelper.C_CREATED_AT, status.getCreatedAt().getTime());
				 contentValues.put(DbHelper.C_TEXT, status.getMessage());
				 contentValues.put(DbHelper.C_USER, status.getUser());
				 
				 //insert one record in the table timeline
				 db.insertOrThrow(DbHelper.TABLE, null, contentValues);
			 }
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		db.close();
	}
}
