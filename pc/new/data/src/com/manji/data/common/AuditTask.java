package com.manji.data.common;


import com.jfinal.plugin.cron4j.ITask;
import com.manji.data.utils.AuditInput;

public class AuditTask  implements ITask{

	private AuditInput ai = new AuditInput();
	
	@Override
	public void run() {
		
		ai.enterAudit();
		ai.goodsAudit();
		ai.companyAudit();
		//ai.scopeAudit();
		
		System.out.println("调用开始");
	}

	@Override
	public void stop() {
		
		System.out.println("结束");
	}
	
}
