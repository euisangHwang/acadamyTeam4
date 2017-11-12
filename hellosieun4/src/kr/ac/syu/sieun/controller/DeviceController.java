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

import cmmn.JsonUtil;
import kr.ac.syu.sieun.dao.DeviceService;


@Controller
public class DeviceController {
	
	@Autowired
	private DeviceService deiviceService;
	
	@RequestMapping(value="insertCmd.do")
	public void insertCmd (HttpServletRequest req) throws Exception {
		
		HashMap<String,Object> param = new HashMap<String, Object>();
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		int comandLevel = Integer.parseInt(req.getParameter("comandLevel"));
		 param.put("deviceCode", deviceCode);
		 param.put("comandLevel", comandLevel);
		 
		 deiviceService.insertCmd(param);
	}
	
	@RequestMapping(value="selectCmd.do")
	public void selectCmd (HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		List<Map> cmdInfo = deiviceService.selectCmd(deviceCode);
		
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(JsonUtil.ListToJson(cmdInfo));
	}
}
