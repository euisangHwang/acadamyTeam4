package kr.ac.syu.sieun.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.syu.sieun.dao.DeviceService;


@Controller
public class DeviceController {
	
	@Autowired
	private DeviceService deiviceService;
}
