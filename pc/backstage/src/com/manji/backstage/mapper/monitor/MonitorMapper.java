package com.manji.backstage.mapper.monitor;

import java.util.List;

import javax.annotation.Resource;

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

@Resource
public interface MonitorMapper {
	
	int countManager(ManagerVo vo);
	
	List<Manager> queryManager(ManagerVo vo);
	
	void addManager(Manager manager);
	
	Manager getManager(int id);
	
	int updManager(Manager manager);
	
	int delManager(int id);
	
	
	int countManagerLog(ManagerLogVo vo);
	
	List<ManagerLog> queryManagerLog(ManagerLogVo vo);
	
	void addManagerLog(ManagerLog ManagerLog);
	
	ManagerLog getManagerLog(int id);
	
	int updManagerLog(ManagerLog ManagerLog);
	
	int delManagerLog(int id);
	
	
	int countManagerRecharge(ManagerRechargeVo vo);
	
	List<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo);
	
	void addManagerRecharge(ManagerRecharge ManagerRecharge);
	
	ManagerRecharge getManagerRecharge(int id);
	
	int updManagerRecharge(ManagerRecharge ManagerRecharge);
	
	int delManagerRecharge(int id);
	
	
	int countManagerRole(ManagerRoleVo vo);
	
	List<ManagerRole> queryManagerRole(ManagerRoleVo vo);
	
	void addManagerRole(ManagerRole ManagerRole);
	
	ManagerRole getManagerRole(int id);
	
	int updManagerRole(ManagerRole ManagerRole);
	
	int delManagerRole(int id);
	
	
	int countManagerRoleValue(ManagerRoleValueVo vo);
	
	List<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo);
	
	void addManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	ManagerRoleValue getManagerRoleValue(int id);
	
	int updManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	int delManagerRoleValue(int id);
	
	int countInfoCorrecting(InfoCorrectingVo vo);
	
	List<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo);
	
	void addInfoCorrecting(InfoCorrecting ic);
	
	InfoCorrecting getInfoCorrecting(int id);
	
	int updInfoCorrecting(InfoCorrecting ic);
	
	int delInfoCorrecting(int id);	
}
