package com.manji.backstage.service.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.agent.AgentMapper;
import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;
import com.manji.backstage.vo.message.NoticeVo;
@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	private AgentMapper mapper;


	@Override
	public Page<Notice> queryAgentNotice(NoticeVo vo) {
	
		Page<Notice> page =new Page<Notice>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Notice> dataList =mapper.queryAgentNotice(vo);
		int totalCount =mapper.countAgentNotice(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
		
		
		
	}
	
	@Override
	public void addAgentNotice(Notice notice) {
		mapper.addAgentNotice(notice);
	}
	
	@Override
	public Notice getAgentNotice(int id) {
		return mapper.getAgentNotice(id);
	}
	
	@Override
	public boolean updAgentNotice(Notice notice) {
		if(mapper.updAgentNotice(notice)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delAgentNotice(int id) {
		if(mapper.delAgentNotice(id)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public Page<AgentInfo> queryAgentInfo(AgentInfoVo vo) {

		Page<AgentInfo> page =new Page<AgentInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countAgentInfo(vo);
		List<AgentInfo> dataList =mapper.queryAgentInfo(vo);
		
		page.transform(count, dataList);
		
		return page;
	}

	@Override
	public AgentInfo getAgentInfo(int id) {
		return mapper.getAgentInfo(id);
	}

	@Override
	public boolean updAgentInfo(AgentInfo ai) {
		
		if(mapper.updAgentInfo(ai)>0){
			return true;
		}
		
		return false;
		
	}

	@Override
	public void addAgentInfo(AgentInfo ai) {
		mapper.addAgentInfo(ai);
	}
	
	@Override
	public boolean delAgentInfo(int id) {
		
		if(mapper.delAgentInfo(id)>0){
			return true;
		}
		return false;
	}
	
	

	@Override
	public Page<AgentInfoTemp> queryAgentInfoTemp(AgentInfoTempVo vo) {

		Page<AgentInfoTemp> page =new Page<AgentInfoTemp>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countAgentInfoTemp(vo);
		List<AgentInfoTemp> dataList =mapper.queryAgentInfoTemp(vo);
		page.transform(count, dataList);
		
		return page;
	}

	@Override
	public boolean updAgentInfoTemp(AgentInfoTemp ait) {
			
			if(mapper.updAgentInfoTemp(ait)>0){
				return true;
			}
			
			return false;
			
		}

	@Override
	public AgentInfoTemp getAgentInfoTemp(int ait) {
		return mapper.getAgentInfoTemp(ait);
	}
	
	@Override
	public void addAgentInfoTemp(AgentInfoTemp ait) {
		mapper.addAgentInfoTemp(ait);
	}
	
	@Override
	public boolean delAgentInfoTemp(int id) {
		
		if(mapper.delAgentInfoTemp(id)>0){
			return true;
		}
		return false;
	}

}
