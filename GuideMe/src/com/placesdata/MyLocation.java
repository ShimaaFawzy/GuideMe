package com.placesdata;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;

public class MyLocation {
	Context mContext;
	Location curLocation;
	LocationManager locMgr;

	public MyLocation(Context mContext) {
		this.mContext = mContext;
		locMgr = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locMgr
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
			// go to setting
		} else {
			Criteria criteria = new Criteria();
			curLocation = locMgr.getLastKnownLocation(locMgr.getBestProvider(
					criteria, false));
			LocationProvider prov = locMgr
					.getProvider(LocationManager.GPS_PROVIDER);
			locMgr.requestLocationUpdates(prov.getName(), 30, 30,
					new getLocationListener());
		}

	}

	public Location getLocation() {
		return curLocation;
	}

	public LatLng updateWithNewLocation(Location location) {

		LatLng newLocation = null;

		if (location != null) {

			newLocation = new LatLng(location.getLatitude(),
					location.getLongitude());

		} else {
			newLocation = new LatLng(31.214011, 29.885638);
		}
		return newLocation;
	}

	class getLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			curLocation = location;

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

}