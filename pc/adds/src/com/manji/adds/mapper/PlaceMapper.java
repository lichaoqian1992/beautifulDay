package com.manji.adds.mapper;


import java.util.List;


import com.manji.adds.model.Place;

public interface PlaceMapper {

	
	List<Place> Multiquery(Place pla);
	
	Place queryPlaceById(Place queryMap);
	
	int updPlace(Place place);
	
	void insertPlace(Place place);
	
}
