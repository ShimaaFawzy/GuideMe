package com.sqlite.place;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FavouriteTable extends SQLiteOpenHelper{

 // Database Name
    private static final String DATABASE_NAME = "favManager";
    
 // Database Version
    private static int DATABASE_VERSION = 1;
    
   // Table Name
    private static final String TABLE_FAVOURITE = "favourite";
    
    // Favourite Table - column names
    private static final String KEY_PLACE_ID = "place_id";
    private static final String KEY_FLAG = "flag";

  //Favourite table create statement
    private static final String CREATE_TABLE_FAVOURITE = "CREATE TABLE "
            + TABLE_FAVOURITE + "(" 
    		+ KEY_PLACE_ID + " INTEGER PRIMARY KEY," 
            + KEY_FLAG + " INTEGER "
            + ")";
    
	
	public FavouriteTable(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_FAVOURITE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
	}
	
	

}
