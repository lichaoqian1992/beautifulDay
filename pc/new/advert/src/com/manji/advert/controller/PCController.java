package com.manji.advert.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.ChannelData;
import com.manji.advert.model.Result;
import com.manji.advert.service.AppService;
import com.manji.advert.service.PCService;

public class PCController extends Controller {

	private PCService service = new PCService();

	public void index() {
		renderText("pc");
	}

	public void channelInfo() {

		String loc = getPara("code");
		String name = getPara("channel");

		Result r = new Result();

		if (name == null || "".equals(name)) {

			r.setCode("0001");
			r.setData("频道（channel）参数错误");
			renderJson(r);
			return;
		}

		String location = service.getLocation(loc);

		ChannelData cd = service.getChannelData(location, name);

		r.setCode("0000");
		r.setData(cd);

		renderJson(r);

	}

	public void homepage() {

		String loc = getPara("code");

		String location = service.getLocation(loc);

		List<Record> channelList = service.getChannelList();

		List<ChannelData> channels = new ArrayList<ChannelData>();

		for (int i = 0; i < channelList.size(); i++) {

			String name = channelList.get(i).get("ch_name");

			ChannelData cd = service.getChannelData(location, name);

			channels.add(cd);

		}

		Result r = new Result();

		r.setCode("0000");
		r.setData(channels);

//		service.setBrowse("view", channels);

		renderJson(r);

	}

}
