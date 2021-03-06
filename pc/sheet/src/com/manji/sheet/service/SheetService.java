package com.manji.sheet.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.model.bean.Sheet;
import com.manji.sheet.model.reqParam.SheetParam;

public class SheetService extends BaseSheetService {


	//多条件得到工单分页数据
	public Page<Record> getSheetPage(SheetParam param) {


		int index = param.getIndex();

		StringBuffer sqlBuffer = new StringBuffer("from dt_sheets where 1=1 ");


//		。。。。


		sqlBuffer.append(" order by add_time desc");//根据创建时间倒序

		Page<Record> list = Db.paginate(index, 20, "select *", sqlBuffer.toString());


		return list;

	}


	//根据编号得到工单信息
	public Record getSheetById(long sheet_id) {

		Record rec = Db.findFirst("select * from dt_sheets where id=?", sheet_id);

		return rec;
	}

	//跟据编号获得工单资料信息
	public List<Record> getSheetInfos(long sheet_id) {

		List<Record> infos = Db.find("select * from dt_sheet_info where sheet_id =? order by add_time desc", sheet_id);//根据时间顺序查询

		return infos;

	}

	//根据编号获得工单业务关联信息

	public Record getSheetBusyinfo(long sheet_id) {

		Record rec = Db.findFirst("select * from dt_sheet_business where sheet_id =? ", sheet_id);

		return rec;
	}
}
