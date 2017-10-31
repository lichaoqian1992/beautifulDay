package com.manji.singleSign.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import net.sf.json.JSONArray;
import net.sf.json.JSONString;

import java.util.ArrayList;
import java.util.List;

public class UserService extends BaseService{

    /**
     * 查询全部登录账户基本信息
     */
    public Page<Record> findAllUser(int page,String username,int dept_id){
        StringBuffer sql=new StringBuffer("from sso_users left join sso_dept on sso_users.dept_id= sso_dept.id where 1=1");

        //如果添加了通过用户名查询
        if (username!=""&&username!=null){
            sql.append(" and sso_users.username like '%"+username+"%'");
        }

        //如果选择了部门筛选
        if (dept_id!=-1){
                sql.append(" and sso_users.dept_id = "+dept_id+"");
        }

        Page<Record> recordPage=Db.paginate(page,20,
                "select sso_users.realName,sso_users.mobile,sso_users.id,sso_users.username,sso_users.password,sso_users.dept_id,sso_users.status,sso_dept.id as did,sso_dept.dept_name,sso_dept.type,sso_dept.status as dstatus",
                sql.toString());
        return recordPage;
    }

    /**
     * 新增账户
     */
    @Before(Tx.class)
    public Record saveUser(String username,String password,String dept_id,String realName,String mobile){
        Record r =new Record();
        r.set("username",username);
        r.set("password",password);
        r.set("dept_id",dept_id);
        r.set("realName",realName);
        r.set("mobile",mobile);
        r.set("status","1");
        Db.save("sso_users",r);
        return r;
    }

    /**
     * 新增关系表数据
     */
    @Before(Tx.class)
    public boolean saveUserRoleSys(String user_id,String[] system){
        for(String sys : system){
    		String[] sysArray=sys.split(",");
    		Record record=new Record();
    		record.set("user_id",user_id);
    		record.set("sys_id",sysArray[0]);
    		record.set("role_id",sysArray[1]);
    		Db.save("sso_user_role",record);
    	}
        return true;
    }

    //通过id查询账户基本信息
    public Record finduserByid(String id){
        return  Db.findFirst("select * from sso_users where id=?",id);
    }

    //通过userid查询中间表()
    public List<Record> findUserRoleSystemByid(String id){
        return  Db.find("select sso_user_role.role_id,sso_user_role.sys_id,sso_user_role.id as id,sso_system.system_name as s,sso_roles.role_name as r from sso_user_role left join sso_system on sso_user_role.sys_id=sso_system.id left join sso_roles on sso_user_role.role_id=sso_roles.id where sso_user_role.user_id=?;",id);
    }

    //修改账户基本信息
    @Before(Tx.class)
    public boolean updateUsers(String id ,String username,String password,String dept_id,String status,String realName,String mobile){
        int rowUp=Db.update("update  sso_users set username=?,password=?,dept_id=?,status=?,realName=?,mobile=? where id=? ",username,password,dept_id,status,realName,mobile,id);
		boolean stu=false;
        if(rowUp>0){
        	stu=true;
		}
        return stu;
    }

    //修改中间表
    @Before(Tx.class)
    public boolean updateuserRole(String id,String[] system){
    	Db.update("delete from sso_user_role where user_id=?",id);
    	for(String sys : system){
    		String[] sysArray=sys.split(",");
    		Record record=new Record();
    		record.set("user_id",id);
    		record.set("sys_id",sysArray[0]);
    		record.set("role_id",sysArray[1]);
    		Db.save("sso_user_role",record);
    	}
    	
        return true;
    }
    
    public int verificationName(String username,int id){
    	int row=0;
    	if(id==0){
            row=Db.findFirst("select count(1) count from sso_users where username=?",username).getInt("count");
        }else{
            row=Db.findFirst("select count(1) count from sso_users where id<>? and username=?",id,username).getInt("count");
        }

    	return row;
    }
}
