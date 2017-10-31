package com.manji.adds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.RecordMapper;
import com.manji.adds.model.Adds;
import com.manji.adds.model.Record;
import com.manji.adds.utils.DatesUtils;
import com.manji.adds.utils.JsonUtils;

@Service
public class RecordServiceImpl implements RecordService {

	
	@Autowired
	private RecordMapper mapper;
	


	@Override
	public void addRecord(Adds adds, String operate, String username) {

		Record rec =new Record();
		String date =DatesUtils.today();
		String time =DatesUtils.time();
		
		
		rec.setDate(date);
		rec.setTime(time);
		rec.setOperate(operate);
		rec.setTitle(adds.getTitle());
		rec.setUsername(username);
		rec.setType("0");
		rec.setVal(adds.getAd_id());
		
		mapper.addRecord(rec);
		
	}

	@Override
	public List<Record> firstPageRecords() {
		
		
		List<Record> list =mapper.getFastRecord();
		
		
		return list;
	}

	@Override
	public List<Record> allRecords() {
		
		List<Record>  list=mapper.getRecord();
		
		return list;
	}

	@Override
	public List<Record> queryRecords(Record qrymap) {

		List<Record>  list=mapper.queryRecords(qrymap);
		
		return list;
	}

}
