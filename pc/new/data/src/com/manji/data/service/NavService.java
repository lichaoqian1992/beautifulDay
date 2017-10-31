package com.manji.data.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class NavService {

    /**
     * 查询一级PC首页导航
     * @return
     */
    public List<Record> pcHomeNav() {

        String sql = "select id,icon,title,update_time,status from dt_advert_navigation where parent_id=0 and type='PC' ";

        List<Record> records = Db.find(sql);
        Map<String,Object> map = new HashMap<String,Object>();
        
        return records;
    }

    public void addPcFirst() {

        String sql = "";


    }
}
