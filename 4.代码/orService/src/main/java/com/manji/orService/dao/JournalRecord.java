package com.manji.orService.dao;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class JournalRecord {

    List<HashMap<String,Object>> allRecords;

    List<HashMap<String,Object>> statusRecords;

    List<HashMap<String,Object>> memoRecords;

    List<HashMap<String,Object>> enclosure;

}
