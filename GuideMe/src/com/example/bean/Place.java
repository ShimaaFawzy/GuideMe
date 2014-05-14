package com.example.bean;

public class Place {
	
	private int id;
	private String placeName ,placeDescription , placeRate ,placeLongitude ,placeLatitude ,placeTicketPrice;
	public int getId() {
		return id;
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
	public String getPlaceRate() {
		return placeRate;
	}
	public void setPlaceRate(String placeRate) {
		this.placeRate = placeRate;
	}
	public String getPlaceLongitude() {
		return placeLongitude;
	}
	public void setPlaceLongitude(String placeLongitude) {
		this.placeLongitude = placeLongitude;
	}
	public String getPlaceLatitude() {
		return placeLatitude;
	}
	public void setPlaceLatitude(String placeLatitude) {
		this.placeLatitude = placeLatitude;
	}
	public String getPlaceTicketPrice() {
		return placeTicketPrice;
	}
	public void setPlaceTicketPrice(String placeTicketPrice) {
		this.placeTicketPrice = placeTicketPrice;
	}
	@Override
	public String toString() {
		return "Place [id=" + id + ", placeName=" + placeName
				+ ", placeDescription=" + placeDescription + ", placeRate="
				+ placeRate + ", placeLongitude=" + placeLongitude
				+ ", placeLatitude=" + placeLatitude + ", placeTicketPrice="
				+ placeTicketPrice + "]";
	}
	
	
}
