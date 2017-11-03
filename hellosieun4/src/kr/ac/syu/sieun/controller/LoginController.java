package kr.ac.syu.sieun.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.syu.sieun.dao.IMemeberService;
import kr.ac.syu.sieun.dto.Members;

@Controller
public class LoginController {
	private static final Logger logger = 
			LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private IMemeberService iMemeberService;
	
	@RequestMapping(value = "login.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		logger.info("Welcome LoginController login! "+ new Date());
		model.addAttribute("title", "회원가입");
		return "login/login.tiles";
	}//
	@RequestMapping(value = "addmember.do", method = {RequestMethod.POST })
	public String addmember(Members member,Model model) {
		logger.info("Welcome LoginController addmember! "+ new Date());
		model.addAttribute("title", "회원가입");
		iMemeberService.addMembers(member);
		return "login/login.tiles";
	}//
	
	
	
	
	
	
}

