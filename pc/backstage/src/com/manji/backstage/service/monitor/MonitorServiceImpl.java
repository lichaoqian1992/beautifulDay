package com.manji.backstage.service.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.monitor.MonitorMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.monitor.InfoCorrecting;
import com.manji.backstage.model.monitor.Manager;
import com.manji.backstage.model.monitor.ManagerLog;
import com.manji.backstage.model.monitor.ManagerRecharge;
import com.manji.backstage.model.monitor.ManagerRole;
import com.manji.backstage.model.monitor.ManagerRoleValue;
import com.manji.backstage.model.operation.ApoProvince;
import com.manji.backstage.vo.monitor.InfoCorrectingVo;
import com.manji.backstage.vo.monitor.ManagerLogVo;
import com.manji.backstage.vo.monitor.ManagerRechargeVo;
import com.manji.backstage.vo.monitor.ManagerRoleValueVo;
import com.manji.backstage.vo.monitor.ManagerRoleVo;
import com.manji.backstage.vo.monitor.ManagerVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;

@Service
public class MonitorServiceImpl implements MonitorService{

	@Autowired
	private MonitorMapper mapper;
	@Override
	public Page<Manager> queryManager(ManagerVo vo) {
		Page<Manager> page =new Page<Manager>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Manager> dataList =mapper.queryManager(vo);
		int totalCount =mapper.countManager(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManager(Manager manager) {
		mapper.addManager(manager);
		
	}
	
	@Override
	public Manager getManager(int id) {
		
		return mapper.getManager(id);
	}
	
	@Override
	public boolean updManager(Manager manager) {
		if(mapper.updManager(manager)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManager(int id) {
		if(mapper.delManager(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerLog> queryManagerLog(ManagerLogVo vo) {
		Page<ManagerLog> page =new Page<ManagerLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerLog> dataList =mapper.queryManagerLog(vo);
		int totalCount =mapper.countManagerLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerLog(ManagerLog ml) {
		mapper.addManagerLog(ml);
		
	}
	
	@Override
	public ManagerLog getManagerLog(int id) {
		
		return mapper.getManagerLog(id);
	}
	
	@Override
	public boolean updManagerLog(ManagerLog ml) {
		if(mapper.updManagerLog(ml)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerLog(int id) {
		if(mapper.delManagerLog(id)>0){
			return true;	
		}
		return false;
	}

	
	@Override               
	public Page<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo) {
		Page<ManagerRecharge> page =new Page<ManagerRecharge>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRecharge> dataList =mapper.queryManagerRecharge(vo);
		int totalCount =mapper.countManagerRecharge(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRecharge(ManagerRecharge mr) {
		mapper.addManagerRecharge(mr);
		
	}
	
	@Override
	public ManagerRecharge getManagerRecharge(int id) {
		
		return mapper.getManagerRecharge(id);
	}
	
	@Override
	public boolean updManagerRecharge(ManagerRecharge mr) {
		if(mapper.updManagerRecharge(mr)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRecharge(int id) {
		if(mapper.delManagerRecharge(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerRole> queryManagerRole(ManagerRoleVo vo) {
		Page<ManagerRole> page =new Page<ManagerRole>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRole> dataList =mapper.queryManagerRole(vo);
		int totalCount =mapper.countManagerRole(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRole(ManagerRole mr) {
		mapper.addManagerRole(mr);
		
	}
	
	@Override
	public ManagerRole getManagerRole(int id) {
		
		return mapper.getManagerRole(id);
	}
	
	@Override
	public boolean updManagerRole(ManagerRole mr) {
		if(mapper.updManagerRole(mr)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRole(int id) {
		if(mapper.delManagerRole(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo) {
		Page<ManagerRoleValue> page =new Page<ManagerRoleValue>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRoleValue> dataList =mapper.queryManagerRoleValue(vo);
		int totalCount =mapper.countManagerRoleValue(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRoleValue(ManagerRoleValue mrv) {
		mapper.addManagerRoleValue(mrv);
		
	}
	
	@Override
	public ManagerRoleValue getManagerRoleValue(int id) {
		
		return mapper.getManagerRoleValue(id);
	}
	
	@Override
	public boolean updManagerRoleValue(ManagerRoleValue mrv) {
		if(mapper.updManagerRoleValue(mrv)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRoleValue(int id) {
		if(mapper.delManagerRoleValue(id)>0){
			return true;	
		}
		return false;
	}
	

	@Override               
	public Page<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo) {
		Page<InfoCorrecting> page =new Page<InfoCorrecting>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<InfoCorrecting> dataList =mapper.queryInfoCorrecting(vo);
		int totalCount =mapper.countInfoCorrecting(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addInfoCorrecting(InfoCorrecting ic) {
		mapper.addInfoCorrecting(ic);
		
	}
	
	@Override
	public InfoCorrecting getInfoCorrecting(int id) {
		
		return mapper.getInfoCorrecting(id);
	}
	
	@Override
	public boolean updInfoCorrecting(InfoCorrecting ic) {
		if(mapper.updInfoCorrecting(ic)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delInfoCorrecting(int id) {
		if(mapper.delInfoCorrecting(id)>0){
			return true;	
		}
		return false;
	}
	
}
