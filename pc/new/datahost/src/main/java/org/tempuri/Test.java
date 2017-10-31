package org.tempuri;

import org.tempuri.SmsMsgWcfStub.TimingTaskSend;
import org.tempuri.UserWcfStub.UserRePassword;
import org.tempuri.UserWcfStub.UserRePasswordModel;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SmsMsgWcfStub clinet = new SmsMsgWcfStub();
		//TimingTaskSend 
		org.tempuri.SmsMsgWcfStub.TimingTaskSend abc = new TimingTaskSend();
		abc.setMobiles("18523017068");
		abc.setTitle("haha");
		abc.setContent("两款手机的过啦");
		abc.setIp("192.168.1.1");
		abc.setPass(1);
		clinet.timingTaskSend(abc);
		
//		UserWcfStub uws = new UserWcfStub();
//		UserRePassword uu = new UserRePassword();
//		UserRePasswordModel model = new UserRePasswordModel();
//		model.setAccount("");
//		model.setNewPassword("");
//		model.setSessionId("");
//		uu.setModel(model);
//		uws.userRePassword(uu);
	}

}
