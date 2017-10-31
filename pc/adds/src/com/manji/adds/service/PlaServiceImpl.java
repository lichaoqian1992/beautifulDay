package com.manji.adds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.PlaceMapper;
import com.manji.adds.model.Place;
import com.manji.adds.utils.JsonUtils;
@Service
public class PlaServiceImpl implements PlaService {

	@Autowired
	private PlaceMapper mapper;
	


	@Override
	public List<Place> multiQuery(Place pla) {

		List<Place> list =mapper.Multiquery(pla);
		
		return list;
	}

	@Override
	public Place queryPlace(Place queryMap) {


		return mapper.queryPlaceById(queryMap);
	}

	@Override
	public boolean updPlace(Place place) {
		
		System.out.println(JsonUtils.getJson(place));
		int r =mapper.updPlace(place);
		System.out.println(r);
		if(r>0)
		{
			return true;
		}else
		{
			return false;
		}
		
		
		
	}

	@Override
	public void addPlace(Place place) {

		
		mapper.insertPlace(place);
		
		
	}

}
