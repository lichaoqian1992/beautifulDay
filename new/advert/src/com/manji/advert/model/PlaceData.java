package com.manji.advert.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class PlaceData {

	private Record place;
	private List<Record> advertises;
	
	
	
	public Record getPlace() {
		return place;
	}
	public void setPlace(Record place) {
		this.place = place;
	}
	public List<Record> getAdvertises() {
		return advertises;
	}
	public void setAdvertises(List<Record> advertises) {
		this.advertises = advertises;
	}
	
	
	
}
