package com.example.bean;

import com.google.android.gms.maps.model.Marker;

import android.view.ViewGroup.MarginLayoutParams;

public class Place {
	
	private int id;
	private String placeName ,placeDescription , placeFromTo ,placeCategory ,placeTelephone;
	private Marker marker;
	private double placeRate ,placeSumRate ,userRate, placeLongitude ,placeLatitude;
	private int placeVoteNo ;
	public double getPlaceSumRate() {
		return placeSumRate;
	}

	public void setPlaceSumRate(double placeSumRate) {
		this.placeSumRate = placeSumRate;
	}

	public int getPlaceVoteNo() {
		return placeVoteNo;
	}

	public void setPlaceVoteNo(int placeVoteNo) {
		this.placeVoteNo = placeVoteNo;
	}

	public int getId() {
		return id;
	}
	
	public String getPlaceFromTo() {
		return placeFromTo;
	}

	public void setPlaceFromTo(String placeFromTo) {
		this.placeFromTo = placeFromTo;
	}

	public String getPlaceCategory() {
		return placeCategory;
	}

	public void setPlaceCategory(String placeCategory) {
		this.placeCategory = placeCategory;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceDescription() {
		return placeDescription;
	}
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
	
	public String getPlaceTelephone() {
		return placeTelephone;
	}

	public void setPlaceTelephone(String placeTelephone) {
		this.placeTelephone = placeTelephone;
	}

	public double getPlaceRate() {
		return placeRate;
	}

	public void setPlaceRate(double placeRate) {
		this.placeRate = placeRate;
	}

	
	public double getUserRate() {
		return userRate;
	}

	public void setUserRate(double userRate) {
		this.userRate = userRate;
	}


	
	
	public double getPlaceLongitude() {
		return placeLongitude;
	}

	public void setPlaceLongitude(double placeLongitude) {
		this.placeLongitude = placeLongitude;
	}

	public double getPlaceLatitude() {
		return placeLatitude;
	}

	public void setPlaceLatitude(double placeLatitude) {
		this.placeLatitude = placeLatitude;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", placeName=" + placeName
				+ ", placeDescription=" + placeDescription + ", placeFromTo="
				+ placeFromTo + ", placeCategory=" + placeCategory
				+ ", placeTelephone=" + placeTelephone + ", marker=" + marker
				+ ", placeRate=" + placeRate + ", placeSumRate=" + placeSumRate
				+ ", placeVoteNo=" + placeVoteNo + ", userRate=" + userRate
				+ ", placeLongitude=" + placeLongitude + ", placeLatitude="
				+ placeLatitude + "]";
	}
	
	
}
