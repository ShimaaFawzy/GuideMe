package com.example.bean;



public class Event {

	private int id ,eventPlaceId;
	private String eventName ,eventPlace , eventDescription , eventStartDate , eventEndtDate ,eventTicketPrice ;

	
	public Event(){}
	
	public Event(int id ,int placeId,String name, String desc ,String start ,String end ,String ticket) {
		super();
		this.id =id;
		eventPlaceId =placeId;
		eventName = name;
		eventDescription = desc;
		eventStartDate = start;
		eventEndtDate = end;
		eventTicketPrice = ticket;
	}
	
	
	public String getEventTicketPrice() {
		return eventTicketPrice;
	}

	public void setEventTicketPrice(String eventTicketPrice) {
		this.eventTicketPrice = eventTicketPrice;
	}

	public int getEventPlaceId() {
		return eventPlaceId;
	}

	public void setEventPlaceId(int eventPlaceId) {
		this.eventPlaceId = eventPlaceId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getEventEndtDate() {
		return eventEndtDate;
	}

	public void setEventEndtDate(String eventEndtDate) {
		this.eventEndtDate = eventEndtDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", eventPlaceId=" + eventPlaceId
				+ ", eventName=" + eventName + ", eventPlace=" + eventPlace
				+ ", eventDescription=" + eventDescription
				+ ", eventStartDate=" + eventStartDate + ", eventEndtDate="
				+ eventEndtDate + "]";
	}
	
	
	
}
