package com.manji.adds.service;

import java.util.List;

import com.manji.adds.model.Adds;
import com.manji.adds.model.Record;

public interface RecordService {

	void addRecord(Adds adds, String operate, String username);
	
	List<Record> firstPageRecords();
	
	List<Record> allRecords();
	
	List<Record> queryRecords(Record qrymap);
	
}
