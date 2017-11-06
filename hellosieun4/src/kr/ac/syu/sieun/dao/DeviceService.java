package kr.ac.syu.sieun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DeviceService {

	List<Map<String, Object>> selectAllDevice(int uMCode, String deviceSort);
	
	Map<String, Object> selectAllDevice(HashMap<String, Object> param);
	
	List<Map<String, Object>> selectAllMusic(int uMCode);

	String insertSound(MultipartHttpServletRequest mreq);

	List<Map<String, Object>> selectAllPicture(int deviceCode);
	String deleteSound(int[] delMusics);

	void insertCmd(HashMap<String, Object> param);
	
	List<Map<String, Object>> selectAllDevices(int uMCode);

	String insertSpeakMusicMatch(HashMap<String, Object> param);

	Map<String, Object> selectMatchSpeakMusic(int deviceCode);

	String updateMatch(HashMap<String, Object> param);

	Map<String, Object> selectHomeImg(int memberCode);
	
}
