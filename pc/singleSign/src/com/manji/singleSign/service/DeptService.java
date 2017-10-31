package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.bean.Dept;

public class DeptService extends BaseService{


	public List<Record> deptList(){

		List<Record> list =Db.find("select * from sso_dept order by id");
		return list;

	}

	/**
	 * 分页通过条件查询部门
	 * @param page
	 * @param dept_name
     * @return
     */
	public Page<Record> findAllDept(int page,String dept_name){
		StringBuffer sql=new StringBuffer("from sso_dept where 1=1");
		if (dept_name!=""&&dept_name!=null){
			sql.append(" and dept_name like '%"+dept_name+"%'");
		}
		Page<Record> list =Db.paginate(page,20,"select *",sql.toString());
		return list;
	}

	/**
	 * 通过部门id查询部门
	 * @param deptId
	 * @return
     */
	public Record findDeptById(String deptId){
		String sql="select * from sso_dept where id=?";
		return Db.findFirst(sql,deptId);
	}

	public Record findDept(String deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addDept(Dept d) {
		Record rec =new Record();
		
		
		rec.set("dept_name", d.getDept_name())
			.set("type", d.getType())
			.set("status", d.getStatus());
		
		Db.save("sso_dept", rec);
		
		
	}

	public void updateDept(Dept d) {
		
		
		Record rec =new Record();
		
		
		rec.set("dept_name", d.getDept_name())
			.set("type", d.getType())
			.set("status", d.getStatus())
			.set("id", d.getId());
		
		Db.update("sso_dept", rec);

	}

	/*public void deleteDept(String deptId) {
		// TODO Auto-generated method stub

	}
*/









}
