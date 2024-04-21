package com.ticketmaster.model;

import java.util.List;

public class Event {
	private String id;
	private String title;
	private String dateStatus;
	private String timeZone;
	private String startDate;
	private List<Artist> artists;
	//private List<String> artistIds;
	private Venue venue;
	private boolean hiddenFromSearch;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDateStatus() {
		return dateStatus;
	}
	public void setDateStatus(String dateStatus) {
		this.dateStatus = dateStatus;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getStartDate() {
		return startDate;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public boolean isHiddenFromSearch() {
		return hiddenFromSearch;
	}
	public void setHiddenFromSearch(boolean hiddenFromSearch) {
		this.hiddenFromSearch = hiddenFromSearch;
	}
	
	
}
