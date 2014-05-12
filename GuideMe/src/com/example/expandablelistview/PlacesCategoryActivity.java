package com.example.expandablelistview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlacesCategoryActivity extends Activity {

	ListView listView;
//	public static String[] places_values = new String[20];
	public static ArrayList<String> places_values = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places_category);
		Drawable d = getResources().getDrawable(R.drawable.back);
		getActionBar().setBackgroundDrawable(d);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setIcon(R.drawable.logo);
		listView = (ListView) findViewById(R.id.listView_place_category);
		
		// Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		              android.R.layout.simple_list_item_1, android.R.id.text1, this.getPlaces_values());
		  
		 // Assign adapter to ListView
         listView.setAdapter(adapter); 
         
         final Intent myIntent = new Intent(getApplicationContext(),PlaceDescriptionActivity.class);
         
         Intent catIntent = getIntent();
         final String categoryName = (String)catIntent.getExtras().get("category_name");
         
      // ListView Item Click Listener
         listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int itemPosition, long id) {
                //send intent holding the place name to load its description page
				   String placeName = (String) places_values.toArray()[itemPosition];
                myIntent.putExtra("place_name", placeName);
                
                if(categoryName.equalsIgnoreCase("GreekRoman")){
             	   myIntent.putExtra("category_name", "GreekRoman");
                }else if (categoryName.equalsIgnoreCase("Islamic")) {
             	   myIntent.putExtra("category_name", "Islamic");
         	  } else if(categoryName.equalsIgnoreCase("Musems&Modern")){
         		  myIntent.putExtra("category_name", "Musems&Modern");
         	  }
                startActivity(myIntent);
			
			}
        	 
         });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.places_category, menu);
		return true;
	}

	public static ArrayList<String> getPlaces_values() {
		return places_values;
	}

	public static void setPlaces_values(ArrayList<String> places_values) {
		PlacesCategoryActivity.places_values = places_values;
	}

}
