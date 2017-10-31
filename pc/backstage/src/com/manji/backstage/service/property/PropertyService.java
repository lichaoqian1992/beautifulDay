package com.manji.backstage.service.property;

import java.util.List;

import com.manji.backstage.model.property.Distribution;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.property.Config;
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

public interface PropertyService {



	
	/////////////////////////////////////////////////
	
	
	
	Page<SmsTemplate> querySmsTemplate(SmsTemplateVo vo);
	SmsTemplate getSmsTemplate(int id);
	boolean updSmsTemplate(SmsTemplate st);
	boolean delSmsTemplate(int id);
	void addSmsTemplate(SmsTemplate st);
	
	
	/////////////////////////////////////////////////



	
	
	Page<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo);
	EmailTemplate getEmailTemplate(int id);
	boolean updEmailTemplate(EmailTemplate et);
	boolean delEmailTemplate(int id);
	void addEmailTemplate(EmailTemplate et);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	void addConfig(Config conf);
	Config getConfig(int id);
	boolean updConfig(Config conf);
	boolean delConfig(int id);
	Page<Config> queryConfig(ConfigVo vo);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	Page<Distribution> queryDistribution(DistributionVo vo);
	void addDistribution(Distribution dist);
	Distribution getDistribution(int id);
	boolean updDistribution(Distribution dist);
	boolean delDistribution(int id);


	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	Page<Express> queryExpress(ExpressVo vo);
	void addExpress(Express ex);
	Express getExpress(int id);
	boolean updExpress(Express ex);
	boolean delExpress(int id);


	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	Page<Payment> queryPayment(PaymentVo vo);
	void addPayment(Payment pay);
	Payment getPayment(int id);
	boolean updPayment(Payment pay);
	boolean delPayment(int id);

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	Page<Medal> queryMedal(MedalVo vo);
	void addMedal(Medal medal);
	Medal getMedal(int id);
	boolean updMedal(Medal medal);
	boolean delMedal(int id);
	
}
