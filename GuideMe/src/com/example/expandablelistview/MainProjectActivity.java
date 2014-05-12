package com.example.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.example.calender.CalenderActivity;
import com.javapapers.android.RegisterActivity;




import adapters.ExpandableListAdapter;
import android.os.Bundle;
import android.provider.Settings.System;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import com.placesdata.PlaceDesc;

public class MainProjectActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Intent intent ;


@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_mainproject);
	getActionBar().hide();
	
	//get the list view 
	expListView = (ExpandableListView) findViewById(R.id.expandableListView_id);
	
	// preparing list data
      prepareListData();
	
	 listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	 expListView.setAdapter(listAdapter);

	 RegisterActivity l=new RegisterActivity();
	 l.startGCMService(getApplicationContext());
	 //child items click
	 expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
	
		 
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			Intent intent = new Intent(getApplicationContext(),PlaceDescriptionActivity.class);
			Intent intent2 = new Intent(getApplicationContext(), PlacesCategoryActivity.class);
			
			if(groupPosition == 0){
				switch (childPosition) {
					case 0:
						intent.putExtra("place_name", "Bibliotheca Alexandria");
						startActivity(intent);
						break;
					case 1:
						intent.putExtra("place_name", "Roman Amphitheatre");
						startActivity(intent);
						break;
					case 2:
						intent.putExtra("place_name", "Citadel of Quitbay");
						startActivity(intent);
						break;
                   
					default:
						break;
				}
			}
			
			else if(groupPosition == 2){//touristic places
				switch (childPosition) {
				case 0://Greek		
					intent2.putExtra("category_name", "GreekRoman");
					HashMap greekMap = PlaceDesc.getGreekRomanHashMap();
					ArrayList<String> greek_places = getKeysFromMap(greekMap);
					PlacesCategoryActivity.setPlaces_values(greek_places);
					startActivity(intent2);
					break;
					
				case 1://Islamic
					intent2.putExtra("category_name", "Islamic");
						HashMap islamicMap = PlaceDesc.getIslamicHashMap();
						ArrayList<String> islamic_places = getKeysFromMap(islamicMap);
					PlacesCategoryActivity.setPlaces_values(islamic_places);
					startActivity(intent2);
					break;
					
				case 2 ://Museums
					intent2.putExtra("category_name", "Musems&Modern");
					HashMap egyptianMap = PlaceDesc.getMuseumsAndModernHashMap();
						ArrayList<String> egyptian_places = getKeysFromMap(egyptianMap);
					PlacesCategoryActivity.setPlaces_values(egyptian_places);
					startActivity(intent2);
					break;
					
				default:
					break;
				}
				
			}
			return false;
		}
	});
	 
	 //headers listener
	 expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
		
		@Override
		public boolean onGroupClick(ExpandableListView arg0, View v, int position,
				long id) {
			
			switch (position) {
			case 4:
				//map
				intent = new Intent(MainProjectActivity.this, MyMapActivity.class);
				startActivity(intent);
				break;
				
			case 5://photos
					intent = new Intent(MainProjectActivity.this, ListPhotosActivity.class);
					startActivity(intent);
					break;
			case 6://photos
				intent = new Intent(MainProjectActivity.this,CalenderActivity.class);
				startActivity(intent);
				break;


			default:
				break;
			}
		
			return false;
		}
	});

	 
}

/*
 * Preparing the list data
 */
private void prepareListData() {
	 listDataHeader = new ArrayList<String>();
     listDataChild = new HashMap<String, List<String>>();
     
     // Adding headers data
     listDataHeader.add("Don't Miss");
     listDataHeader.add("Favourites");
     listDataHeader.add("Touristic Places");
     listDataHeader.add("Areas");
     listDataHeader.add("Maps");
     listDataHeader.add("Photos");
     listDataHeader.add("Events");
     listDataHeader.add("Program");

     // Adding child data
     List<String> dontMiss = new ArrayList<String>();
     dontMiss.add("Bibliotheca Alexandrina");
     dontMiss.add("Roman Amphitheatre");
     dontMiss.add("Qaitbay Citadel");
   

     List<String> favourites = new ArrayList<String>();
     
     List<String> places = new ArrayList<String>();
     places.add("Greek-Roman");
     places.add("Islamic");
     places.add("Museums and Modern");

     List<String> areas = new ArrayList<String>();

     List<String> maps = new ArrayList<String>();
     List<String> photos = new ArrayList<String>();
     
     List<String> events = new ArrayList<String>();
     List<String> program = new ArrayList<String>();
     
      // Header, Child data
     listDataChild.put(listDataHeader.get(0), dontMiss);
     listDataChild.put(listDataHeader.get(1), favourites);
     listDataChild.put(listDataHeader.get(2), places);
     listDataChild.put(listDataHeader.get(3), areas);
     listDataChild.put(listDataHeader.get(4), maps);
     listDataChild.put(listDataHeader.get(5), photos);
     listDataChild.put(listDataHeader.get(6), events);
     listDataChild.put(listDataHeader.get(7), program);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
}

public ArrayList<String> getKeysFromMap(HashMap  hashMap){
    ArrayList<String> places = new ArrayList<String>();
	Set keys = hashMap.keySet();
    Iterator iterator = keys.iterator();
    String key;
    while(iterator.hasNext())
    {
        key = (String)iterator.next();
        places.add(key);
    }	
    return places;
}



}