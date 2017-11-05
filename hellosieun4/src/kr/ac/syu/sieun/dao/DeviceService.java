package kr.ac.syu.sieun.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DeviceService {

	List<Map<String, Object>> selectAllDevice(int uMCode, String deviceSort);
	
	List<Map<String, Object>> selectAllMusic(int uMCode);

	String insertSound(MultipartHttpServletRequest mreq);

	List<Map<String, Object>> selectAllPicture(int deviceCode);

	Map<String, Object> selectAllDevice(int deviceCode);

}
