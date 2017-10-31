package com.manji.adds.mapper;

import java.util.List;

import com.manji.adds.model.Adds;
import com.manji.adds.model.Sequence;

public interface AddsMapper {

	Sequence getSequence();
	
	void updSequence(Sequence sec);
	
	void insertAdds(Adds adds);
	
	Adds queryAddsById(String ad_id);
	
	int updAdds(Adds adds);
	
	List<Adds> queryAdds(Adds queryMap);
	
	int updState(Adds updMap);
	
	int getAllCount();
	
	int getRevCount();
	
	int getAllCounts();
	
	int getMCounts(String date);
	
	int getDCounts(String date);
	
	
	
	
}
