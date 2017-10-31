package com.manji.mlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.mlife.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {

	private CardService service;
	
	@RequestMapping(value = "/gameList", method = RequestMethod.GET)
	@ResponseBody 
	public String gameList(){
		
		String str ="";//service.getGame();
		return str;
	}
	
	
}
