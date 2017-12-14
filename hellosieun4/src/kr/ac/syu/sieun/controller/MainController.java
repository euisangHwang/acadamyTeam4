package kr.ac.syu.sieun.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cmmn.JsonUtil;
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
			
			//메시지 셋팅
			model.addAttribute("uploadMsg", req.getParameter("uploadMsg"));
			page = "main/sound_main.tiles";
		}
		
		return page;
	}
	
	@RequestMapping(value="toDetailMain.do")
	public String toDetailMain (HttpServletRequest req, Model model) {
		
		HashMap<String,Object> param = new HashMap<String,Object>();
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		param.put("deviceCode", deviceCode);
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//장치정보 가져오기
			Map<String,Object> cctvInfo =  deviceService.selectAllDevice(param);
			model.addAttribute("cctvInfo", cctvInfo);
			
			System.out.println(cctvInfo.toString());
			
			page = "main/detailMain.tiles";
		}
		return page;
	}
	
	@RequestMapping(value="toShowPicture.do")
	public String toShowPicture (HttpServletRequest req, Model model) {
		
		int deviceCode = Integer.parseInt((String)req.getParameter("deviceCode"));
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//사진 목록 가져오기
			List<Map<String,Object>> pictures =  deviceService.selectAllPicture(deviceCode);
			model.addAttribute("pictures", pictures);
			
			System.out.println("picture값 : "+pictures.toString());
			
			page = "main/showPictures.tiles";
		}
		return page;
	}
	
	@RequestMapping(value="insertSound.do")
	public String insertSound (MultipartHttpServletRequest mreq, Model model) {
		
		String result = "insert"+deviceService.insertSound(mreq);
		return "redirect:toSound_main.do?uploadMsg="+result;
	}
	
	@RequestMapping(value="deleteSound.do")
	public String deleteSound (HttpServletRequest req, Model model) {
		
		String[] params = req.getParameterValues("delMusics");
		int[] delMusics = new int[params.length];
		for(int i=0; i<params.length; i++) {
			
			delMusics[i] = Integer.parseInt(params[i]);
		}
		
		String result = "delete"+deviceService.deleteSound(delMusics);
		return "redirect:toSound_main.do?uploadMsg="+result;
	}
	
	@RequestMapping(value="toSetting.do")
	public String toSetting (HttpServletRequest req, Model model) {
		return "main/setting.tiles";
	}
	
	@RequestMapping(value="deviceSet.do")
	public String deviceSet (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		
		if(memberCode != 0) {
			//스피커,센서 목록 가져오기
			List<Map<String,Object>> devices =  deviceService.selectAllDevices(memberCode);
			//도면사진 가져오기
			Map<String,Object> homeimg = deviceService.selectHomeImg(memberCode);
			
			model.addAttribute("devices", devices);
			model.addAttribute("homeimg", homeimg);
			
			System.out.println(devices.toString());
			
			page = "main/deviceSet.tiles";
		}
		return page;
	}
	
	@RequestMapping(value="deviceDetail.do")
	public String deviceDetail (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		
		if(memberCode != 0) {
			//음악목록 가져오기
			List<Map<String,Object>> musics =  deviceService.selectAllMusic(memberCode);
			//장치정보 + 매치된 음원정보 가져오기
			Map<String,Object> matchInfo = deviceService.selectMatchSpeakMusic(deviceCode);
			
			model.addAttribute("musics", musics);
			model.addAttribute("matchInfo", matchInfo);
			
			page = "main/deviceDetail.tiles";
		}
		
		return page;
	}
	
	@RequestMapping(value="deviceDetail2.do")
	public void deviceDetail2 (HttpServletRequest req, HttpServletResponse res) throws Exception {

		res.setCharacterEncoding("UTF-8");
		HashMap<String,Object> toMap = new HashMap<String,Object>();
		
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		int deviceCode = Integer.parseInt(req.getParameter("deviceCode"));
		
		if(memberCode != 0) {
			//음악목록 가져오기
			List<Map<String,Object>> musics =  deviceService.selectAllMusic(memberCode);
			//장치정보 + 매치된 음원정보 가져오기
			Map<String,Object> matchInfo = deviceService.selectMatchSpeakMusic(deviceCode);
			
			toMap.put("musics", musics);
			toMap.put("matchInfo", matchInfo);
		}
		
		PrintWriter out = res.getWriter();
		out.write(JsonUtil.HashMapToJson(toMap));
	}
	
	@RequestMapping(value="matchSpeack_Music.do")
	public String matchSpeack_Music (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("speakerCode", Integer.parseInt(req.getParameter("speakerCode")));
		param.put("musicCode", Integer.parseInt(req.getParameter("musicCode")));
		
		if(memberCode != 0) {
			//음원과 장치정보를 등록할 것.
			String result = "match"+deviceService.insertSpeakMusicMatch(param);
			
			page = "redirect:deviceSet.do?matchMsg="+result;
		}
		
		return page;
	}
	
	@RequestMapping(value="updateMatch.do")
	public String updateMatch (HttpServletRequest req, Model model) {
		
		String page = "redirect:login.do";
		int memberCode = (int)req.getSession().getAttribute("sessMCode");
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("speakerCode", Integer.parseInt(req.getParameter("speakerCode")));
		param.put("musicCode", Integer.parseInt(req.getParameter("musicCode")));
		
		if(memberCode != 0) {
			//음원과 장치정보를 등록할 것.
			String result = "match"+deviceService.updateMatch(param);
			
			page = "redirect:deviceSet.do?matchMsg="+result;
		}
		
		return page;
	}
	
	
}
