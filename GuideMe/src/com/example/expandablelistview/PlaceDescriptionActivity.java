package com.example.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;
import com.google.android.gms.maps.model.LatLng;
import com.placesdata.LocationLatLong;
import com.placesdata.MyLocation;
import com.placesdata.PlaceDesc;

import adapters.PlaceArrayAdapter;
import android.R.integer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PlaceDescriptionActivity extends Activity {

	ListView listView;
	// ImageView imgPlace;
	String placeName, placeCategory;
	Intent goToGallery;
	RatingBar ratingBar ;
	JSONObject object1 ,object2, fileObject1;
	//public DownloadTask dt;
	double newRate;
	String myJson ="" ;
	LayerDrawable stars;
	float rate;
	
	public static final String PREFS_FAV = "favPlaces";
	private boolean isFav = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_description);
		createFavPlace();
		// imgPlace = (ImageView) findViewById(R.id.imgPlace);
		
		goToGallery = new Intent(PlaceDescriptionActivity.this,
				FullPhotoActivity.class);
		// favourite

		// Action Bar
		//if (Build.VERSION.RELEASE.startsWith("4.0")) {
			Drawable d = getResources().getDrawable(R.drawable.back);
			getActionBar().setBackgroundDrawable(d);
			getActionBar().setDisplayShowTitleEnabled(false);
			getActionBar().setIcon(R.drawable.logo);
		//}
		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);
		// Defined Array values to show in ListView
		//Header and Footer
		LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView,
                false);
        ViewGroup rateHeader = (ViewGroup) inflater.inflate(R.layout.footer, listView,
                false);
        listView.addHeaderView(header, null, false);
        listView.addHeaderView(rateHeader, null, false);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar1);
        ratingBar.setEnabled(false);
        ratingBar.setRating((float) 2.5);
		stars = (LayerDrawable) ratingBar.getProgressDrawable();
		stars.getDrawable(2).setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_ATOP);
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
             boolean fromUser) {
            	rate=rating;
            	ratingBar.setRating((float) rating);
             Toast.makeText(getApplicationContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
            // dt = new DownloadTask();
             //dt.execute();
            System.out.println("=====http://192.168.1.101:8084/EventsCalendar/Events/events/rate/"+"Bibliotheca Alexandria"+"/"+String.valueOf(rating));
            }
           });
		// Assign adapter to ListView
		Intent placeIntent = getIntent();
		placeName = (String) placeIntent.getExtras().get("place_name");
		placeCategory = (String) placeIntent.getExtras().get("category_name");
		if (placeCategory == null) {
			placeCategory = "roka";
		}

		if (placeName != null) {
			listView.setAdapter(new PlaceArrayAdapter(this, PlaceDesc.getAll()
					.get(placeName)));
		}
		// check for categories

		if (placeCategory.equalsIgnoreCase("GreekRoman")) {
			HashMap map = PlaceDesc.getGreekRomanHashMap();
			listView.setAdapter(new PlaceArrayAdapter(this, (String[]) map
					.get(placeName)));

			goToGallery.putExtra("press", 2);

		} else if (placeCategory.equalsIgnoreCase("Islamic")) {
			HashMap map = PlaceDesc.getIslamicHashMap();
			listView.setAdapter(new PlaceArrayAdapter(this, (String[]) map
					.get(placeName)));
			goToGallery.putExtra("press", 1);
		} else if (placeCategory.equalsIgnoreCase("Musems&Modern")) {
			HashMap map = PlaceDesc.getMuseumsAndModernHashMap();
			listView.setAdapter(new PlaceArrayAdapter(this, (String[]) map
					.get(placeName)));
			goToGallery.putExtra("press", 3);

		} else {
			HashMap map = PlaceDesc.getAll();
			listView.setAdapter(new PlaceArrayAdapter(this, (String[]) map
					.get(placeName)));
		}

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position-2) {
				case 1:
					// to call the place's phone number
					// ListView Clicked item value
					String itemValue = (String) listView
							.getItemAtPosition(position);
					Intent dial = new Intent();
					dial.setAction("android.intent.action.DIAL");
					dial.setData(Uri.parse("tel:" + itemValue));
					startActivity(dial);
					return;
				case 2:
					// to get routing from user position to place which he read
					// it details
					MyLocation myLocation = new MyLocation(
							getApplicationContext());
					LatLng from = myLocation.updateWithNewLocation(myLocation
							.getLocation());
					LatLng to = LocationLatLong.getAllLongLat().get(placeName);
					Uri uri = Uri.parse("http://maps.google.com/maps?&saddr="
							+ from.latitude + "," + from.longitude + "&daddr="
							+ to.latitude + "," + to.longitude);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_place_description, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_favourit:
			if (!isFav) {
				saveOrRemvoeFromPref(true);
				isFav = true;
				item.setIcon(R.drawable.favorites);
			} else {
				saveOrRemvoeFromPref(false);
				isFav = false;
				item.setIcon(R.drawable.favourit);
			}
			return true;
		case R.id.action_panorama:
			// to show panorama of the place
			Intent intent = new Intent(getApplicationContext(),
					ViewPanoramaActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_gallary:
			startActivity(goToGallery);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	
	private void createFavPlace() {
		SharedPreferences sharedPreferences = getSharedPreferences(PREFS_FAV,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// creat array once to put defult value
		ArrayList listOfPlaces = new ArrayList<String>();
		listOfPlaces.add("none");

		// convert array to string to save it in shared pref as string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listOfPlaces.size(); i++) {
			sb.append(listOfPlaces.get(i)).append(",");
		}
		editor.putString("placesIds", sb.toString());
		editor.commit();
	}

	// this method take boolean if true it will save id in pref if false will
	// remove from pref

	private void saveOrRemvoeFromPref(boolean type) {
		String toastMsg;
		SharedPreferences sharedPreferences = getSharedPreferences(PREFS_FAV,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// get array of all saved places to put or remove from fav
		String AllPlacesStr = sharedPreferences.getString("placesIds", "none");
		String[] placelists = AllPlacesStr.split(",");
		ArrayList<String> listOfPlaces = new ArrayList<String>();
		for (int i = 0; i < placelists.length; i++) {
			listOfPlaces.add(placelists[i]);
		}
		if (type) {
			listOfPlaces.add(placeName);
			toastMsg="added to fav";
		} else {
			listOfPlaces.remove(placeName);
			toastMsg="removed from fav";
		}

		// covert array to string again :D to override the last one
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listOfPlaces.size(); i++) {
			sb.append(listOfPlaces.get(i)).append(",");
		}
		editor.putString("placesIds", sb.toString());
		editor.commit();

		Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT)
				.show();
	}

}
