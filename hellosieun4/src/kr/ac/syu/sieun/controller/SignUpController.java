package kr.ac.syu.sieun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.syu.sieun.dao.SignUpService;
import kr.ac.syu.sieun.dto.Members;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;
	
	
	@RequestMapping(value="signUp.do")
	public String signUp () throws Exception {
		
		
		return "login/signUp.tiles";
	}
	
	@RequestMapping(value="insertMember.do")
	public String insertMember (Members member) throws Exception {
		
		signUpService.insertMember(member);
		
		return "redirect:login.do";
	}
	
	
	
}
