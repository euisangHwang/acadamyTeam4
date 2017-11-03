package kr.ac.syu.sieun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(value="main.do")
	public String toMain () {
		
		return "main/main.tiles";
	}
	
	
}
