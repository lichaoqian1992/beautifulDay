package com.manji.advert.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class ChannelData {

	private Record channel;
	
	private List<PlaceData> places;

	public Record getChannel() {
		return channel;
	}

	public void setChannel(Record channel) {
		this.channel = channel;
	}

	public List<PlaceData> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceData> places) {
		this.places = places;
	}
	
	
	
	
}
