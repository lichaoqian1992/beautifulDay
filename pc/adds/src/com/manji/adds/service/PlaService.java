package com.manji.adds.service;

import java.util.List;

import com.manji.adds.model.Place;

public interface PlaService {

	
	List<Place> multiQuery(Place pla);
	
	Place queryPlace(Place queryMap);
	
	boolean updPlace(Place place);
	
	void addPlace(Place place);
	
	
	
	
}
