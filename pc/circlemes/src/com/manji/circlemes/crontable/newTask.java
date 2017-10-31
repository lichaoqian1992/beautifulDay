package com.manji.circlemes.crontable;

import com.manji.circlemes.common.ITask;
import com.manji.circlemes.utils.MD5Utils;

public class newTask implements ITask {

	@Override
	public void run() {
		
		System.out.println(MD5Utils.getCurrentTime());
		
	}

	@Override
	public void stop() {
		System.out.println("==========================================================");
		
	}

}
