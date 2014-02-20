package com.intel.yamba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	static final String DB_NAME = "timeline.db";
	static final int DB_VERSION = 1;
	
	//table name and columns
	static final String TABLE = "timeline";
	static final String C_ID = BaseColumns._ID;
	static final String C_CREATED_AT = "created_at";
	static final String C_TEXT = "text";
	static final String C_USER = "user";
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("Yamba", "onCreate - execSQL - CREATE");
		
		//create the table timeline
		String sql = "CREATE TABLE " + TABLE + 
				"( " + C_ID + " INT PRIMARY KEY, " +
				C_CREATED_AT + " int, " +
				C_USER + " text, " +
				C_TEXT + " text);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//only for developing purposes, we drop everything and we recreate the tables
		Log.d("Yamba", "onUpgrade - execSQL - DROP");
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE + ";");
		onCreate(db);
	}

}
