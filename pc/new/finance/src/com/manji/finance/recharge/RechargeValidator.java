package com.manji.finance.recharge;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.manji.finance.recharge.model.RechargeDetail;
/**
 * 校验
 * @author Administrator
 *
 */
public class RechargeValidator extends Validator{

	protected void validate(Controller c) {
		/*validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "请选择审批金额");
		validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "充值账号不能为空");
		validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "姓名有误");
		validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "身份证有误");
		validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "充值金额不能为空");
		validateRequiredString("rechargeDetail.PERSON_APPROVING", "checkPeople", "请选择是否可提现");*/
		
	}

	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.keepModel(RechargeDetail.class);
		c.render("launch.html");
	}

}
