package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.bean.Role;
import com.manji.singleSign.model.query.RoleVo;

public class RoleService extends BaseService {

	public List<Record> roleList() {

		List<Record> list = Db.find("select r.*,s.system_name from sso_roles r,sso_system s where s.id =r.sys_id");

		return list;
	}

	public List<Record> role(){
		return Db.find("  select distinct role_name,name from sso_roles");
	}

	//通过项目id查询项目下全部角色
	public List<Record> findRoleBySystemid(String system_id){
		List<Record> list=Db.find("select * from sso_roles where sys_id=?",system_id);
		return list;
	}


	public Page<Record> queryRoles(RoleVo rv) {

		StringBuffer sqlBuffer = new StringBuffer("from sso_roles r,sso_system s where  1=1 and s.id =r.sys_id ");

		if (rv.getSys_id() != null && !"0".equals(rv.getSys_id())) {

			sqlBuffer.append(" and r.sys_id ='" + rv.getSys_id() + "'");

		}
		if(rv.getRole_name() != null && !rv.getRole_name().equals("")){
			sqlBuffer.append(" and r.role_name ='" + rv.getRole_name() + "'");
		}
		if(rv.getName() != null && !rv.getName().equals("")){
			sqlBuffer.append(" and r.name ='" + rv.getName() + "'");
		}
		if(rv.getName_role() != null && !rv.getName_role().equals("")){
			sqlBuffer.append(" and (r.name like '%" + rv.getName_role() + "%' or r.role_name like '%"+rv.getName_role()+"%')");
		}
		sqlBuffer.append(" order by  r.id");

		Page<Record> page = Db.paginate(Integer.valueOf(rv.getIndex()), 20, "select r.*,s.system_name",
				sqlBuffer.toString());

		return page;
	}

	public void addRole(Role role) {

		Record rec = new Record();

		rec.set("role_name", role.getRole_name()).set("name", role.getName()).set("sys_id", role.getSys_id())
				.set("status", role.getStatus());

		Db.save("sso_roles", rec);

	}

	public Record getRole(String id) {

		Record rec = Db.findFirst(
				"select r.*,s.system_name from sso_roles r,sso_system s where s.id =r.sys_id and r.id =" + id);

		return rec;
	}
	/**
	 * 修改权限
	 * @param role_id
	 * @param idList
	 * @param sys_id
	 * @param role_name
	 * @param name
	 * @return
	 */
	public int updateRole(String role_id,String idList,String sys_id,String role_name,String name) {
		String[] str = idList.split(";");
		boolean flag = false;
		//1.修改角色信息
		//1.1查询角色和系统的关系，没有关系就新增，有关系就修改
		List<Record> roleAndSys = Db.query("select * from sso_roles a ,sso_system b where a.sys_id=b.id and a.id=? and b.id=?",role_id,sys_id);
		if(roleAndSys.size() > 0){
			Db.update("update sso_roles set role_name=?,name=? where id=?",role_name,name,role_id);
		}else{
			Db.update("insert into sso_roles values(?,?,?,?,?)",role_id,role_name,name,sys_id,1);
		}
		
		//2.修改权限信息
		//2.1如果之前存在，现在也存在，那么就不管；如果先前存在，现在不存在，就删除原来的；如果先前不存在，就添加
		List<Record> list = findMenu(role_id);
		
		Db.update("delete from sso_role_menu where role_id=? and sys_id=?",role_id,sys_id);
		for(int i=0;i<str.length;i++){
			int c = Db.update("insert into sso_role_menu values(?,?,?)",role_id,str[i],sys_id);
		}
		
		return 1;
	}

	/**
	 * 添加权限
	 * @param idList
	 * @param role_id
	 * @return
	 */
	public int add(String idList,String sys_id,String role_name,String name){
		String[] str = idList.split(";");
		int a = 0;
		
		//先保存角色信息
		Db.save("sso_roles", new Record().set("role_name", role_name).set("name", name).set("sys_id", sys_id).set("status", "1"));
		int role_id = Db.findFirst("select id from sso_roles where role_name=? and name=? and sys_id=?",role_name,name,sys_id).get("id");
		for(int i=0;i<str.length;i++){
		
			Record r = new Record().set("role_id", role_id).set("menu_id", str[i]).set("sys_id", sys_id);
			boolean flag = Db.save("sso_role_menu", r);
			if(flag){
				a++;
			}
		}
		if(a == str.length){
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 获取角色信息
	 * @param role_id
	 * @return
	 */
	public Record findRole(String role_id){
		
		return Db.findFirst("select * from sso_roles where id=?",role_id);
		
	}
	/**
	 * 查询权限
	 * @param role_id
	 * @return
	 */
	public List<Record> findMenu(String role_id){
		
		return Db.find("select * from sso_role_menu a ,sso_menu b where a.menu_id=b.id and role_id=?",role_id);
	}
	/**
	 * 获取菜单
	 * @param sys_id
	 * @return
	 */
	public List<Record> getMenu(String sys_id){
		
		return Db.find("select * from sso_menu where sys_id=?",sys_id);
	}

}
