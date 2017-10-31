package com.manji.backstage.service.monitor;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.monitor.InfoCorrecting;
import com.manji.backstage.model.monitor.Manager;
import com.manji.backstage.model.monitor.ManagerLog;
import com.manji.backstage.model.monitor.ManagerRecharge;
import com.manji.backstage.model.monitor.ManagerRole;
import com.manji.backstage.model.monitor.ManagerRoleValue;
import com.manji.backstage.vo.monitor.InfoCorrectingVo;
import com.manji.backstage.vo.monitor.ManagerLogVo;
import com.manji.backstage.vo.monitor.ManagerRechargeVo;
import com.manji.backstage.vo.monitor.ManagerRoleValueVo;
import com.manji.backstage.vo.monitor.ManagerRoleVo;
import com.manji.backstage.vo.monitor.ManagerVo;

public interface MonitorService {

	
	Page<Manager> queryManager(ManagerVo vo);
	
	void addManager(Manager Manager);
	
	Manager getManager(int id);
	
	boolean updManager(Manager Manager);
	
	boolean delManager(int id);
	
	
	Page<ManagerLog> queryManagerLog(ManagerLogVo vo);
	
	void addManagerLog(ManagerLog ManagerLog);
	
	ManagerLog getManagerLog(int id);
	
	boolean updManagerLog(ManagerLog ManagerLog);
	
	boolean delManagerLog(int id);
	
	
	Page<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo);
	
	void addManagerRecharge(ManagerRecharge ManagerRecharge);
	
	ManagerRecharge getManagerRecharge(int id);
	
	boolean updManagerRecharge(ManagerRecharge ManagerRecharge);
	
	boolean delManagerRecharge(int id);
	
	
	Page<ManagerRole> queryManagerRole(ManagerRoleVo vo);
	
	void addManagerRole(ManagerRole ManagerRole);
	
	ManagerRole getManagerRole(int id);
	
	boolean updManagerRole(ManagerRole ManagerRole);
	
	boolean delManagerRole(int id);
	
	
	Page<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo);
	
	void addManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	ManagerRoleValue getManagerRoleValue(int id);
	
	boolean updManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	boolean delManagerRoleValue(int id);
	
	
	Page<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo);
	
	void addInfoCorrecting(InfoCorrecting ic);
	
	InfoCorrecting getInfoCorrecting(int id);
	
	boolean updInfoCorrecting(InfoCorrecting ic);
	
	boolean delInfoCorrecting(int id);
	
	
	
}
