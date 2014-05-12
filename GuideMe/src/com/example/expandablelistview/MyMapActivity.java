package com.example.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.placesdata.MyLocation;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyMapActivity extends FragmentActivity {

	GoogleMap map;
	static HashMap<Marker, String> markers;
	private static final LatLng MAKTABA = new LatLng(31.2089, 29.9092);
	private static final LatLng MATHAF = new LatLng(31.2407, 29.9630);
	static final LatLng ELKLAA = new LatLng(31.2130, 29.8852);
	static Marker maktaba, mathaf, elklaa, myLocation;
	boolean isShow;
	// location
	LocationManager locMng;
	Address address;
	ArrayList<Address> addresses;
	String addressText;
	Geocoder geocoder;
	Intent intent ;

	// final Boolean gpsEnabled = locMng
	// .isProviderEnabled(LocationManager.GPS_PROVIDER);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		getActionBar().hide();
		markers = new HashMap<Marker, String>();
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		
		
		if (map != null) {
		implMarkerMap();
			// set listener to info window
			map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker marker) {
					String check = (String) markers.get(marker);
					if (check.equalsIgnoreCase("currentLocation")) {
						implMarkerMap();
					} else {
//						if (check.equalsIgnoreCase("BibAlex")) {
//							System.out.println(">>>>>>>>>>>>>>><,<<<<<<<<<<<<<<<<<<<<<<<<<"+check);
							goToDesc(check);
//						}
					}
				}
			});
			map.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker marker) {
					if (isShow == false) {
						isShow = true;
						marker.showInfoWindow();

					} else {
						isShow = false;
						marker.hideInfoWindow();

					}
					return false;
				}
			});

			// map.addMarker(makeMarkerOptions(HAM, "ham", "hhf", 1));
			// getMyLocation();
		} else {
			Toast.makeText(this, "Google Maps not available", Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
		MyLocation curLocation= new MyLocation(this);
		Location location = curLocation.getLocation();
		if (location != null) {
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 13));
			myLocation = map.addMarker(new MarkerOptions()
					.position(
							new LatLng(location.getLatitude(), location
									.getLongitude())).title("your Location").snippet("show near ?:D "));
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(location.getLatitude(), location
							.getLongitude())) // Sets the center of the map to
												// location user
					.zoom(10) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(40) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			map.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			markers.put(myLocation, "currentLocation");
		}

		/*
		 * if (!gpsEnabled) { enabledLocationSetting(); }
		 */

	}

/*	private void enabledLocationSetting() {
		Intent settingIntent = new Intent(
				Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(settingIntent);
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_sethybrid:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.menu_setnormal:
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.menu_shownear:
			implMarkerMap();

			break;
		}
		return true;
	}

	public void implMarkerMap() {
		maktaba = map.addMarker(new MarkerOptions()
				.position(MAKTABA)
				.title("Biblioteca  Alexandria ")
				.snippet("more details.. ")
				// marker.showinfowindow or hide info window
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));
		mathaf = map.addMarker(new MarkerOptions()
				.position(MATHAF)
				.title("Roman amphitheatre")
				.snippet("more details..")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		// marker.showinfowindow or hide info
		/*
		 * .icon(BitmapDescriptorFactory
		 * .fromResource(R.drawable.ic_launcher)));
		 */
		elklaa = map.addMarker(new MarkerOptions()
				.position(ELKLAA)
				.title("Qaitbay Citadel")
				.snippet("more details..")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		maktaba.hideInfoWindow();
		mathaf.hideInfoWindow();
		elklaa.hideInfoWindow();
		isShow = false;
		markers.put(maktaba, "Bibliotheca Alexandria");
		markers.put(mathaf, "Roman Amphitheatre");
		markers.put(elklaa, "Citadel of Quitbay");
		

	}
	public void goToDesc(String placeKey){
		intent = new Intent(getApplicationContext(),PlaceDescriptionActivity.class);
		intent.putExtra("place_name", placeKey);
		startActivity(intent);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_map, menu);
		return true;
	}

}
