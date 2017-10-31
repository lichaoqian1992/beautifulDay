package com.manji.adds.mapper;

import java.util.List;

import com.manji.adds.model.Record;

public interface RecordMapper {

	void addRecord(Record rec);
	
	List<Record> getFastRecord();
	
	List<Record> getRecord();
	
	List<Record> queryRecords(Record qrymap);
	
	
}
