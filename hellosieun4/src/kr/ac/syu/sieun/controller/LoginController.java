package kr.ac.syu.sieun.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.syu.sieun.dao.LoginService;
import kr.ac.syu.sieun.dto.Members;

@Controller
public class LoginController {
	private static final Logger logger = 
			LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "login.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String login() {
		
		return "login/login.tiles";
	}
	
	@RequestMapping(value = "logout.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest req) {
		
		req.getSession().invalidate();
		
		return "login/login.tiles";
	}
	
	@RequestMapping(value = "isUser.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String isUser(HttpServletRequest req, Model model) throws Exception {
		logger.info("Welcome LoginController login! "+ new Date());
		
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println("uid: "+req.getParameter("uid"));
		System.out.println("upass: "+req.getParameter("upass"));
		param.put("uid", req.getParameter("uid"));
		param.put("upass", req.getParameter("upass"));
		
		int uMCode = loginService.isUser(param); 
		if(uMCode != 0) {
			
			req.getSession().setAttribute("sessId", req.getParameter("uid"));
			req.getSession().setAttribute("sessMCode", uMCode);
			return "redirect:main.do";
		} else {
			
			model.addAttribute("msg", "fail");
			return "login/login.tiles";
		}
	}
}

