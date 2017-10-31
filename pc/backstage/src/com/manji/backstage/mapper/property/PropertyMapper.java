package com.manji.backstage.mapper.property;

import java.util.List;

import javax.annotation.Resource;

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

@Resource
public interface PropertyMapper {

	
	
	
	
	


	

	

	

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	List<SmsTemplate> querySmsTemplate(SmsTemplateVo vo);
	int countSmsTemplate(SmsTemplateVo vo);
	void addSmsTemplate(SmsTemplate st);
	SmsTemplate getSmsTemplate(int id);
	int updSmsTemplate(SmsTemplate st);
	int delSmsTemplate(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////


	List<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo);
	int countEmailTemplate(EmailTemplateVo vo);
	void addEmailTemplate(EmailTemplate et);
	EmailTemplate getEmailTemplate(int id);
	int updEmailTemplate(EmailTemplate et);
	int delEmailTemplate(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	


	int countConfig(ConfigVo vo);
	List<Config> queryConfig(ConfigVo vo);
	Config getConfig(int id);
	int updConfig(Config conf);
	int delConfig(int id);
	void addConfig(Config conf);

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	int countDistribution(DistributionVo vo);
	List<Distribution> queryDistribution(DistributionVo vo);
	void addDistribution(Distribution dist);
	Distribution getDistribution(int id);
	int updDistribution(Distribution dist);
	int delDistribution(int id);

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	int countExpress(ExpressVo vo);
	List<Express> queryExpress(ExpressVo vo);
	void addExpress(Express ex);
	Express getExpress(int id);
	int updExpress(Express ex);
	int delExpress(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	int countPayment(PaymentVo vo);
	List<Payment> queryPayment(PaymentVo vo);
	void addPayment(Payment pay);
	Payment getPayment(int id);
	int updPayment(Payment pay);
	int delPayment(int id);

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	int countMedal(MedalVo vo);
	List<Medal> queryMedal(MedalVo vo);
	void addMedal(Medal medal);
	Medal getMedal(int id);
	int updMedal(Medal medal);
	int delMedal(int id);
	

}
