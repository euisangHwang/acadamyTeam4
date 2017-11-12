package kr.ac.syu.sieun.controller;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cmmn.JsonUtil;
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
	
	@RequestMapping(value="sendSMSMsg.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String sendSMSMsg (HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String result = signUpService.sendSMSMsg(req, res);
		return JsonUtil.OneStringToJson(result);
	}	
	
	@RequestMapping(value="checkUserDuplicate.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkUserDuplicate (@RequestParam String userId) throws Exception {
		
		System.out.println("userId : "+userId);
		
		String result = signUpService.IsUserIdDuplicate(userId);
		
		System.out.println("최종 result : "+result);
		
		return JsonUtil.OneStringToJson(result);
	}
	
	@RequestMapping(value="toFindIdPw.do")
	public String toFindIdPw () throws Exception {
		
		return "login/find_idPw.tiles";
	}
	
	@RequestMapping(value="findIdByPhone.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findIdByPhone (@RequestParam String phone) throws Exception {
		
		System.out.println("phone : "+phone);
		
		String result = signUpService.findIdByPhone(phone);
		
		System.out.println("최종 result : "+result);
		
		return JsonUtil.OneStringToJson(result);
	}
	
	@RequestMapping(value="checkPhone.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkPhone (@RequestParam String id, String phone) throws Exception {
		
		System.out.println("id : "+id);
		System.out.println("phone : "+phone);
		
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("phone", phone);
		param.put("id", id);
		
		
		String result = signUpService.checkPhone(param);
		
		System.out.println("최종 result : "+result);
		
		return JsonUtil.OneStringToJson(result);
	}
	
	@RequestMapping(value="updatePw.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatePw (@RequestParam String newPw, String id) throws Exception {
		
		System.out.println("newPw : "+newPw);
		System.out.println("id : "+id);
		
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("newPw", newPw);
		param.put("id", id);
		
		String result = signUpService.updatePw(param);
		
		System.out.println("최종 result : "+result);
		
		return JsonUtil.OneStringToJson(result);
	}
	
}
