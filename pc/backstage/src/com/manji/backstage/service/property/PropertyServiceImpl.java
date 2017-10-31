package com.manji.backstage.service.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.property.PropertyMapper;
import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.property.Config;
import com.manji.backstage.model.property.Distribution;
import com.manji.backstage.model.property.EmailTemplate;
import com.manji.backstage.model.property.Express;
import com.manji.backstage.model.property.Medal;
import com.manji.backstage.model.property.Payment;
import com.manji.backstage.model.property.SmsTemplate;
import com.manji.backstage.vo.property.ConfigVo;
import com.manji.backstage.vo.property.DistributionVo;
import com.manji.backstage.vo.property.EmailTemplateVo;
import com.manji.backstage.vo.property.ExpressVo;
import com.manji.backstage.vo.property.MedalVo;
import com.manji.backstage.vo.property.PaymentVo;
import com.manji.backstage.vo.property.SmsTemplateVo;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private PropertyMapper mapper;


	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<SmsTemplate> querySmsTemplate(SmsTemplateVo vo) {

		Page<SmsTemplate> page =new Page<SmsTemplate>();
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countSmsTemplate(vo);
		List<SmsTemplate> dataList =mapper.querySmsTemplate(vo);
		
		page.transform(count, dataList);
		
		return page;
	}
	
	@Override
	public void addSmsTemplate(SmsTemplate st) {
		mapper.addSmsTemplate(st);
	}

	@Override
	public SmsTemplate getSmsTemplate(int id) {
		return mapper.getSmsTemplate(id);
	}

	@Override
	public boolean updSmsTemplate(SmsTemplate st) {
		if(mapper.updSmsTemplate(st)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delSmsTemplate(int id) {
		if(mapper.delSmsTemplate(id)>0){
			return true;
		}
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	


	@Override
	public void addEmailTemplate(EmailTemplate et) {
		mapper.addEmailTemplate(et);
	}

	@Override
	public EmailTemplate getEmailTemplate(int id) {
		return mapper.getEmailTemplate(id);
	}

	@Override
	public boolean updEmailTemplate(EmailTemplate et) {
		
		if(mapper.updEmailTemplate(et)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delEmailTemplate(int id) {
		if(mapper.delEmailTemplate(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo) {

		Page<EmailTemplate> page =new Page<EmailTemplate>();
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countEmailTemplate(vo);
		List<EmailTemplate> dataList =mapper.queryEmailTemplate(vo);
		
		page.transform(count, dataList);
		
		return page;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void addConfig(Config conf) {
		mapper.addConfig(conf);
	}
	@Override
	public Config getConfig(int id) {
		return mapper.getConfig(id);
	}

	@Override
	public boolean updConfig(Config conf) {
		
		if(mapper.updConfig(conf)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delConfig(int id) {
		if(mapper.delConfig(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<Config> queryConfig(ConfigVo vo) {
		
		Page<Config> page =new Page<Config>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countConfig(vo);
		List<Config> dataList=mapper.queryConfig(vo);
		page.transform(count, dataList);
		
		return page;
	}

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<Distribution> queryDistribution(DistributionVo vo) {

		Page<Distribution> page =new Page<Distribution>();
		
		if(vo.getIndex()==0){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countDistribution(vo);
		List<Distribution> dataList=mapper.queryDistribution(vo);
		page.transform(count, dataList);
		
		return page;
		
	}

	@Override
	public void addDistribution(Distribution dist) {
		mapper.addDistribution(dist);
	}

	@Override
	public Distribution getDistribution(int id) {
		return mapper.getDistribution(id);
	}

	@Override
	public boolean updDistribution(Distribution dist) {

		if(mapper.updDistribution(dist)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delDistribution(int id) {

		if(mapper.delDistribution(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public Page<Express> queryExpress(ExpressVo vo) {
		Page<Express> page=new Page<Express>();
		
		if(vo.getIndex()==0){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countExpress(vo);
		List<Express> dataList =mapper.queryExpress(vo);
		page.transform(count, dataList);
		
		return page;
	}

	@Override
	public void addExpress(Express ex) {
		mapper.addExpress(ex);
	}

	@Override
	public Express getExpress(int id) {

		return mapper.getExpress(id);
	}

	@Override
	public boolean updExpress(Express ex) {
		if(mapper.updExpress(ex)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delExpress(int id) {
		if(mapper.delExpress(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	

	@Override
	public void addPayment(Payment pay) {
		mapper.addPayment(pay);
		
	}

	@Override
	public Payment getPayment(int id) {
		return mapper.getPayment(id);
	}

	@Override
	public boolean updPayment(Payment pay) {
		if(mapper.updPayment(pay)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPayment(int id) {
		if(mapper.delPayment(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<Payment> queryPayment(PaymentVo vo) {
		Page<Payment> page=new Page<Payment>();
		
		if(vo.getIndex()==0){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countPayment(vo);
		List<Payment> dataList =mapper.queryPayment(vo);
		page.transform(count, dataList);
		
		return page;
	}


	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	

	@Override
	public Page<Medal> queryMedal(MedalVo vo) {
		Page<Medal> page=new Page<Medal>();
		
		if(vo.getIndex()==0){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countMedal(vo);
		List<Medal> dataList =mapper.queryMedal(vo);
		page.transform(count, dataList);
		
		return page;
	}
	



	@Override
	public void addMedal(Medal medal) {
		mapper.addMedal(medal);
	}

	@Override
	public Medal getMedal(int id) {
		return mapper.getMedal(id);
	}

	@Override
	public boolean updMedal(Medal medal) {
		if(mapper.updMedal(medal)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delMedal(int id) {
		if(mapper.delMedal(id)>0){
			return true;
		}
		return false;
	}

	
}
