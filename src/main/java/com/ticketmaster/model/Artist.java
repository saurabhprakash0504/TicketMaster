package com.ticketmaster.model;

import java.util.List;

public class Artist {
    private String id;
    private String name;
    private String imgSrc;
    private String url;
    private Integer rank;
  //  private List<Event> events; // Events the artist is performing in
    private List<String> eventName;
    
    
    public Artist(){
    	
    }
    
	public Artist(String id, String name, String imgSrc, String url, Integer rank, List<String> eventName) {
	super();
	this.id = id;
	this.name = name;
	this.imgSrc = imgSrc;
	this.url = url;
	this.rank = rank;
	this.eventName = eventName;
}
	public List<String> getEventName() {
		return eventName;
	}
	public void setEventName(List<String> eventName) {
		this.eventName = eventName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
/*	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}*/
    
    
	    
}
