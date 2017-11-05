package kr.ac.syu.sieun.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.syu.sieun.dao.DeviceService;

@Controller
public class MainController {

	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value="main.do")
	public String toMain (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//cctc 목록 가져오기
			List<Map<String,Object>> devices =  deviceService.selectAllDevice(memberCode, "cctv");
			model.addAttribute("devices", devices);
			page = "main/main.tiles";
		}
		
		return page;
	}
	
	@RequestMapping(value="toSound_main.do")
	public String toSound_main (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//cctc 목록 가져오기
			List<Map<String,Object>> musics =  deviceService.selectAllMusic(memberCode);
			model.addAttribute("musics", musics);
			
			System.out.println(musics.toString());
			
			page = "main/sound_main.tiles";
		}
		
		return page;
	}
	
/*	@RequestMapping(value="toDetailMain.do")
	public String toDetailMain (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//사진 목록 가져오기
			List<Map<String,Object>> pictures =  deviceService.selectAllPicture(memberCode);
			model.addAttribute("pictures", pictures);
			
			System.out.println(pictures.toString());
			
			page = "main/detailMain.tiles";
		}
		
		return page;
	}*/
	
	
	@RequestMapping(value="insertSound.do")
	public String insertSound (MultipartHttpServletRequest mreq, Model model) {
		
		String result = deviceService.insertSound(mreq);
		model.addAttribute("uploadMsg", result);
		
		return "redirect:toSound_main.do";
	}
}
