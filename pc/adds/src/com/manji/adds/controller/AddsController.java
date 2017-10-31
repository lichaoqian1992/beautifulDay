package com.manji.adds.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manji.adds.model.Adds;
import com.manji.adds.model.AddsMsg;
import com.manji.adds.service.AddsService;
import com.manji.adds.service.RecordService;
import com.manji.adds.utils.JsonUtils;
import com.manji.adds.utils.PicUtils;

@Controller
public class AddsController {

	@Autowired
	private AddsService service;
	@Autowired
	private RecordService recService;

//	private static String location = "F:/pic/";
	 private static String location ="D:/pic/";

	/**
	 * 跳转到广告页面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/toAdds")
	public String toAdds(HttpServletRequest req) {

		return "adds";
	}

	/**
	 * 跳转到新增广告页面
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/toAddAdds")
	public String toAddAdds(HttpServletRequest req) {

		return "adds_add";
	}
	
	@RequestMapping("/toAddPCAdds")
	public String toAddPCAdds(HttpServletRequest req) {

		return "adds_add_pc";
	}

	// /**
	// * 跳转到广告修改页面
	// * @param req
	// * @return
	// */
	// method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	// @ResponseBody
	// @RequestMapping(value="/toUpdAdds",
	// public String toUpdAdds( String ad_id,HttpServletRequest req){
	//
	// Adds adds =service.queryAdds(ad_id);
	//
	//
	//
	// List<String> picList =PicUtils.getPics(ad_id);
	//
	// req.setAttribute("addsInfo", adds);
	// req.setAttribute("pics", picList);
	//
	// AddsMsg msg =new AddsMsg()
	// }

	/**
	 * 新增广告
	 * 
	 * @param adds
	 * @param req
	 * @return
	 */

	@RequestMapping("/addAdds")
	public String addAdds(Adds adds, @RequestParam MultipartFile[] files, HttpServletRequest req) {

		String region = adds.getRegion();
		region = region.substring(0, region.length() - 1);

		String code = adds.getCode();
//		code = code.substring(0, code.length() - 1);

		adds.setRegion(region);
		adds.setCode(code);

		String ad_id = service.getSeqNo();

		adds.setAd_id(ad_id);

		// String pic_url ="http://localhost:8900/upload/"+ad_id+"1.jpg";

		// adds.setPic_url(pic_url);
		adds.setPic_url("");
		adds.setState("0");
		adds.setCount(0);

		if (null != files) {
			for (int i = 0; i < files.length; i++) {

				try {
					MultipartFile file = files[i];
					String myFileName = file.getOriginalFilename();

					String picpath = location + ad_id + "/" + myFileName;

					File targetFile = new File(picpath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					file.transferTo(targetFile);
				} catch (IllegalStateException | IOException e) {

					e.printStackTrace();
				}
			}
		}

		service.addAdds(adds);
		recService.addRecord(adds, "1", req.getSession().getAttribute("username").toString());

		return "redirect:/toAdds";
	}

	@RequestMapping("/updAdds")
	public String updAdds(Adds adds, @RequestParam MultipartFile[] files, HttpServletRequest req) {

		System.out.println(JsonUtils.getJson(adds));
		AddsMsg msg = new AddsMsg();

		File orinFile = new File(location + adds.getAd_id());

//		int index = orinFile.listFiles().length;
		
		
		for (int i = 0; i < files.length; i++) {
			
			MultipartFile file = files[i];
			
			String myFileName = file.getOriginalFilename();
			
			System.out.println(file.isEmpty());
			

			String picpath = location + adds.getAd_id() + "/" + myFileName;

			File targetFile = new File(picpath);
			
			if(!targetFile.isDirectory()){
				
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}


				try {
					file.transferTo(targetFile);
				} catch (IllegalStateException | IOException e) {

					e.printStackTrace();
				}
			}
			
		}

		if (service.updAdds(adds)) {

			recService.addRecord(adds, "2", req.getSession().getAttribute("username").toString());

			msg.setResult("修改成功");
			return "redirect:/toAdds";
		} else {
			msg.setErrCode("1");
			msg.setErrInfo("修改失败");
			return "fault";
		}

	}

	@RequestMapping(value = "/queryAdds", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdds(Adds queryMap, HttpServletRequest req) {
//		System.out.println(JsonUtils.getJson(queryMap));
		List<Adds> addsList = service.queryAddsList(queryMap);

		AddsMsg msg = new AddsMsg();

		if (null != addsList) {
			msg.setResult(addsList);
		} else {
			msg.setErrCode("1");
			msg.setErrInfo("未查询到相应记录");
		}

		return JsonUtils.getJson(msg);
	}

	/**
	 * 根据id查询广告内容
	 * 
	 * @param ad_id
	 * @param req
	 * @return
	 */
	@RequestMapping("/queryAddsById")
	@ResponseBody
	public String queryAddsById(String ad_id, HttpServletRequest req) {

		Adds adds = service.queryAdds(ad_id);

		AddsMsg msg = new AddsMsg();

		msg.setResult(adds);

		return JsonUtils.getJson(msg);
	}

	@RequestMapping("/updAddsState")
	@ResponseBody
	public String updAddsState(String ad_id, String state, HttpServletRequest req) {

		Adds adds = service.queryAdds(ad_id);

		boolean result = service.updState(ad_id, state);

		AddsMsg msg = new AddsMsg();

		if (result) {
			if ("1".equals(state)) {

				recService.addRecord(adds, "3", req.getSession().getAttribute("username").toString());

			} else {
				recService.addRecord(adds, "4", req.getSession().getAttribute("username").toString());

			}

			msg.setResult("修改成功");
		} else {
			msg.setErrCode("1");
			msg.setErrInfo("修改失败");
		}

		return JsonUtils.getJson(msg);
	}

	@RequestMapping("/delPic")
	@ResponseBody
	public String delPic(String pic_url) {

		AddsMsg msg = new AddsMsg();

		if (PicUtils.delPic(pic_url)) {
			msg.setResult("删除成功");
		} else {
			msg.setErrCode("1");
			msg.setErrInfo("修改失败");
		}
		return JsonUtils.getJson(msg);
	}

	@RequestMapping("/toAddAddsFromPlace")
	public String toAddAddsFromPlace(HttpServletRequest req) {

		String channel = req.getParameter("channel");
		String pl_id = req.getParameter("pl_id");

		req.setAttribute("channel", channel);
		req.setAttribute("pl_id", pl_id);

		return "adds_add";
	}

	@RequestMapping(value = "/getFirstPagePara", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getFirstPagePara(HttpServletRequest req) {

		Map<String, String> map = service.getCounts();

		System.out.println(JsonUtils.getJson(map));

		return JsonUtils.getJson(map);

	}

}
