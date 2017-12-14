package kr.ac.syu.sieun.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmmn.JsonUtil;
import kr.ac.syu.sieun.dao.DeviceService;


@Controller
public class DeviceController {
	
	@Autowired
	private DeviceService deiviceService;
	
	@RequestMapping(value="insertCmd.do", method = {RequestMethod.GET, RequestMethod.POST })
	public void insertCmd (HttpServletRequest req) throws Exception {
		
		HashMap<String,Object> param = new HashMap<String, Object>();
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		int comandLevel = Integer.parseInt(req.getParameter("comandLevel"));
		 param.put("deviceCode", deviceCode);
		 param.put("comandLevel", comandLevel);
		 
		 deiviceService.insertCmd(param);
	}
	
	@RequestMapping(value="insertDevice.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String insertDevice (HttpServletRequest req) throws Exception {
		
		HashMap<String,Object> param = new HashMap<String, Object>();
		param.put("deviceIP", req.getParameter("deviceIP"));
		param.put("deviceName", req.getParameter("deviceName"));
		param.put("deviceSort", req.getParameter("deviceSort"));
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		param.put("memberCode", memberCode);
		
		deiviceService.insertDevice(param);
		
		return "redirect:deviceSet.do";
	}
	
	@RequestMapping(value="selectCmd.do")
	public void selectCmd (HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		List<Map> cmdInfo = deiviceService.selectCmd(deviceCode);
		
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(JsonUtil.ListToJson(cmdInfo));
	}
	
	@RequestMapping(value="selectDeviceByMusic.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String selectDeviceByMusic (@RequestParam int musicCode) throws Exception {
		
		List<Map<String,Object>> devices = deiviceService.selectDeviceByMusic(musicCode);
		return JsonUtil.ListToJson(devices);
	}
	
	@RequestMapping(value="updateCmdWork.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateCmdWork (@RequestParam int comCode) throws Exception {
		
		String result = deiviceService.updateCmdWork(comCode);
		return result;
	}
	
	@RequestMapping(value="insertImg.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insertImg (@RequestParam String pFullName, int deviceCode, HttpServletRequest req) throws Exception {
		
		String result = deiviceService.insertImg(pFullName, deviceCode, req);
		System.out.println("result : "+result);
		return result;
	}
}
