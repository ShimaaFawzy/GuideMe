package com.example.sqlite;


import java.util.LinkedList;
import java.util.List;

import com.example.bean.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Database Version
    private static int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "EventDB";
	public static final String CREATE_Event_TABLE = "CREATE TABLE events ( " +
            "id INTEGER PRIMARY KEY, " + 
			"name TEXT, "+
			"description TEXT, "+
			"place_id INTEGER, "+
			"ticketPrice TEXT, "+
			"start_date TEXT, "+
			"end_date TEXT )";
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create event table

		System.out.println("Table created=======");
		// create books table
		db.execSQL(CREATE_Event_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS events");
        
        // create fresh books table
        this.onCreate(db);
	}
	//---------------------------------------------------------------------
   
	/**
     * CRUD operations (create "add", read "get", update, delete) event + get all events + delete all events
     */
	
	// events table name
    private static final String TABLE_EVENTS = "events";
    
    // events Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EVENT_NAME = "name";
    private static final String KEY_EVENT_DESCRIPTION = "description";
    private static final String KEY_EVENT_PLACE_ID = "place_id";
    private static final String KEY_EVENT_TICKET_PRICE = "ticketPrice";
    private static final String KEY_EVENT_START_DATE = "start_date";
    private static final String KEY_EVENT_END_DATE = "end_date";
  
    
    private static final String[] COLUMNS = {KEY_ID,KEY_EVENT_NAME,KEY_EVENT_DESCRIPTION,KEY_EVENT_PLACE_ID,KEY_EVENT_TICKET_PRICE,KEY_EVENT_START_DATE,KEY_EVENT_END_DATE};
    
	public void addEvent(Event event){
		System.out.println("=======addEvent"+ event.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, event.getId());  
        values.put(KEY_EVENT_NAME, event.getEventName());  
        values.put(KEY_EVENT_DESCRIPTION, event.getEventDescription());
        values.put(KEY_EVENT_PLACE_ID, event.getEventPlaceId());
        values.put(KEY_EVENT_TICKET_PRICE, event.getEventTicketPrice());
        values.put(KEY_EVENT_START_DATE, event.getEventStartDate());  
        values.put(KEY_EVENT_END_DATE, event.getEventEndtDate()); 
          
       
        // 3. insert
        db.insert(TABLE_EVENTS, // table
        		null, //nullColumnHack
        		values); // key/value -> keys = column names/ values = column values
        
        // 4. close
        db.close(); 
	}
	
	public Event getEvent(int id){

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. build query
        Cursor cursor = 
        		db.query(TABLE_EVENTS, // a. table
        		COLUMNS, // b. column names
        		" id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build Event object
        Event event = new Event();
        event.setId(Integer.parseInt(cursor.getString(0)));
        event.setEventName(cursor.getString(1));
        event.setEventDescription(cursor.getString(2));
        event.setEventPlaceId(Integer.parseInt(cursor.getString(3)));
        event.setEventTicketPrice(cursor.getString(4));
        event.setEventStartDate(cursor.getString(5));
        event.setEventEndtDate(cursor.getString(4));
       
        System.out.println("=============getEvent("+id+")"+ event.toString());

        // 5. return event
        return event;
	}
	
	// Get All Events
    public List<Event> getAllEvents() {
        List<Event> events = new LinkedList<Event>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENTS;
 
    	// 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build event and add it to list
        Event event = null;
        if (cursor.moveToFirst()) {
            do {
                event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setEventName(cursor.getString(1));
                event.setEventDescription(cursor.getString(2));
                event.setEventPlaceId(Integer.parseInt(cursor.getString(3)));
                event.setEventTicketPrice(cursor.getString(4));
                event.setEventStartDate(cursor.getString(5));
                event.setEventEndtDate(cursor.getString(4));

                // Add event to events
                events.add(event);
            } while (cursor.moveToNext());
        }
        
		System.out.println("=======getAllEvents()"+events.toString());

        // return events
        return events;
    }
	
	 // Updating single event
    public int updateEvent(Event event) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", event.getId()); // 
        values.put("name", event.getEventName()); // 
        values.put("description", event.getEventDescription()); // 
        values.put("placeId", event.getEventPlaceId()); // 
        values.put("ticketPrice", event.getEventTicketPrice()); // 
        values.put("startDate", event.getEventStartDate()); // 
        values.put("endDate", event.getEventEndtDate()); // 
        
        // 3. updating row
        int i = db.update(TABLE_EVENTS, //table
        		values, // column/value
        		KEY_ID+" = ?", // selections
                new String[] { String.valueOf(event.getId()) }); //selection args
        
        // 4. close
        db.close();
        
        return i;
        
    }

    // Deleting single event
    public void deleteEvent(Event event) {

    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        
        // 2. delete
        db.delete(TABLE_EVENTS,
        		KEY_ID+" = ?",
                new String[] { String.valueOf(event.getId()) });
        
        // 3. close
        db.close();
        
        System.out.println("=====deleteEvent"+ event.toString());

    }
}
