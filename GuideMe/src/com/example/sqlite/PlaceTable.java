package com.sqlite.place;

import java.util.LinkedList;
import java.util.List;

import com.example.bean.Event;
import com.example.bean.Place;
import com.google.android.gms.internal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.drm.DrmStore.Playback;
import android.util.Log;

public class PlaceTable extends SQLiteOpenHelper{

 // Database Name
    private static final String DATABASE_NAME = "placesManager";
    
 // Database Version
    private static int DATABASE_VERSION = 1;
    
 // Table Name
    private static final String TABLE_PLACE = "place";
    
 // Place Table - column names
    private static final String KEY_ID = "place_id";
    private static final String KEY_NAME = "place_name";
    private static final String KEY_DESC = "place_desc";
    private static final String KEY_LONG = "place_long";
    private static final String KEY_LAT = "place_lat";
    private static final String KEY_FROMTO = "place_fromTo";
    private static final String KEY_CATEGORY = "place_categ";
    private static final String KEY_PHONE = "place_phone";
    private static final String KEY_PLACE_RATE = "place_rate";
    private static final String KEY_SUM_RATE = "place_sum_rate";
    private static final String KEY_VOTE_NO = "place_vote_num";
    private static final String KEY_USER_RATE = "place_user_rate";

 //Place table create statement
    private static final String CREATE_TABLE_PLACE = "CREATE TABLE "
            + TABLE_PLACE + "(" 
    		+ KEY_ID + " INTEGER PRIMARY KEY," 
            + KEY_NAME + " TEXT," 
    		+ KEY_DESC + " TEXT," 
            + KEY_LONG + " REAL "
            + KEY_LAT  + " REAL " 
            + KEY_FROMTO + " TEXT "
            + KEY_CATEGORY + " TEXT "
            + KEY_PHONE + " TEXT "
            + KEY_PLACE_RATE + " REAL "
            + KEY_SUM_RATE + " REAL "
            + KEY_VOTE_NO + " INTEGER "
            + KEY_USER_RATE + " REAL "
            + ")";
    
    public PlaceTable(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PLACE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Insert a place 
	 */
	public void addPlace(Place place){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
	    values.put(KEY_ID, place.getId()); 
	    values.put(KEY_NAME, place.getPlaceName());
	    values.put(KEY_DESC, place.getPlaceDescription());
	    values.put(KEY_LONG, place.getPlaceLongitude());
	    values.put(KEY_LAT, place.getPlaceLatitude());
	    values.put(KEY_FROMTO, place.getPlaceFromTo());
	    values.put(KEY_CATEGORY, place.getPlaceCategory());
	    values.put(KEY_PLACE_RATE, place.getPlaceRate());
	    values.put(KEY_SUM_RATE, place.getPlaceSumRate());
	    values.put(KEY_VOTE_NO, place.getPlaceVoteNo());
	    values.put(KEY_USER_RATE, place.getUserRate());
	    
	    //insert data
	    db.insert(TABLE_PLACE, null, values);
	    db.close();     
	}
	
	/*
	 * Retrieve all places data
	 */
	public List<Place> getAllPlaceData(int id) {
		List<Place> placesList = new LinkedList<Place>();
		
		// build the query
        String query = "SELECT  * FROM " + TABLE_PLACE;
        //get reference to data base
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        
        Place place = null;
        if (cursor.moveToFirst()) {
        	do{
        		place = new Place();
        		place.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        		place.setPlaceName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        		place.setPlaceDescription(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
        		place.setPlaceLongitude(cursor.getDouble(cursor.getColumnIndex(KEY_LONG)));
        		place.setPlaceLatitude(cursor.getDouble(cursor.getColumnIndex(KEY_LAT)));
        		place.setPlaceFromTo(cursor.getString(cursor.getColumnIndex(KEY_FROMTO)));
        		place.setPlaceCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
        		place.setPlaceTelephone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
        		place.setPlaceRate(cursor.getDouble(cursor.getColumnIndex(KEY_PLACE_RATE)));
        		place.setPlaceSumRate(cursor.getDouble(cursor.getColumnIndex(KEY_SUM_RATE)));
        		place.setPlaceVoteNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTE_NO)));
        		place.setUserRate(cursor.getDouble(cursor.getColumnIndex(KEY_USER_RATE)));
        		
        		//add place to list
        		placesList.add(place);
        		
        	} while (cursor.moveToNext());
        }
		return placesList;
	}
	
	/*
	 * get single raw of given id
	 */
	public Place getPlace(int place_id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    String selectQuery = "SELECT  * FROM " + TABLE_PLACE + " WHERE "
	            + KEY_ID + " = " + place_id;
	    
	    Log.e("Places Table", selectQuery);
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
	    Place place = null;
	    if(cursor != null){
	    	 cursor.moveToFirst();
	    	  
	    	 place = new Place();
	    	 place.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
	    	 place.setPlaceName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
             place.setPlaceDescription(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
             place.setPlaceLongitude(cursor.getDouble(cursor.getColumnIndex(KEY_LONG)));
             place.setPlaceLatitude(cursor.getDouble(cursor.getColumnIndex(KEY_LAT)));
	         place.setPlaceFromTo(cursor.getString(cursor.getColumnIndex(KEY_FROMTO)));
	         place.setPlaceCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
	         place.setPlaceTelephone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
	         place.setPlaceRate(cursor.getDouble(cursor.getColumnIndex(KEY_PLACE_RATE)));
	         place.setPlaceSumRate(cursor.getDouble(cursor.getColumnIndex(KEY_SUM_RATE)));
	         place.setPlaceVoteNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTE_NO)));
	         place.setUserRate(cursor.getDouble(cursor.getColumnIndex(KEY_USER_RATE)));
	    }
	    return place;
	}
	
	/*
	 * Updating a place
	 */
	public int updatePlace(Place place) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, place.getPlaceName());
	    values.put(KEY_DESC, place.getPlaceDescription());
	    values.put(KEY_LONG, place.getPlaceLongitude());
	    values.put(KEY_LAT, place.getPlaceLatitude());
	    values.put(KEY_FROMTO, place.getPlaceFromTo());
	    values.put(KEY_CATEGORY, place.getPlaceCategory());
	    values.put(KEY_PLACE_RATE, place.getPlaceRate());
	    values.put(KEY_SUM_RATE, place.getPlaceSumRate());
	    values.put(KEY_VOTE_NO, place.getPlaceVoteNo());
	    values.put(KEY_USER_RATE, place.getUserRate());
	    
	    // updating row
	    int i = db.update(TABLE_PLACE, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(place.getId()) });
	    db.close();
	    
	    return i;
	}
	
	/*
	 * Deleting a place
	 */
	public void deletePlace(int place_id) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_PLACE, KEY_ID + " = ?",
	            new String[] { String.valueOf(place_id) });
	    db.close();
	}
}
