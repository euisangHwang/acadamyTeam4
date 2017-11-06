package kr.ac.syu.sieun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.syu.sieun.dao.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;
	
	
	@RequestMapping(value="signUp.do")
	public String signUp () throws Exception {
		
		
		return "login/signUp.tiles";
	}
	
	@RequestMapping(value="insertMember.do")
	public String insertMember (MultipartHttpServletRequest mreq) throws Exception {
		
		String result = signUpService.insertMember(mreq);
		
		return "redirect:login.do?UpdateMsg="+result;
	}
	
	@RequestMapping(value="updateMember.do")
	public String updateMember (MultipartHttpServletRequest req) throws Exception {
		
		String result = signUpService.updateMember(req);
		
		return "redirect:toMypage.do?UpdateMsg="+result;
	}
	
	@RequestMapping(value="toMypage.do")
	public String toMypage (HttpServletRequest req, Model model) throws Exception {
		
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		HashMap<String,Object> member = new HashMap<String,Object>();
		member = signUpService.selectOneMember(memberCode);
		
		model.addAttribute("member", member);
		
		return "login/signUp.tiles";
	}
	
	
}
