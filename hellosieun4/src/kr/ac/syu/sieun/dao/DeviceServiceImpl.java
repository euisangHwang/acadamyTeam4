package kr.ac.syu.sieun.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cmmn.DateUtil;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeivceDao deviceDao;
	
	@Resource(name="fileServer")
	private Properties properties;
	
	@Override
	public List<Map<String, Object>> selectAllDevice(int uMCode, String deviceSort) {
		return deviceDao.selectAllDevice(uMCode, deviceSort);
	}

	@Override
	public List<Map<String, Object>> selectAllMusic(int uMCode) {
		return deviceDao.selectAllMusic(uMCode);
	}

	@Override
	public String insertSound(MultipartHttpServletRequest mreq) {
		
		String result = "SUCCESS";
		int uid = (int)mreq.getSession().getAttribute("sessMCode");
		Map<String,Object> params = new HashMap<String,Object>();
		Iterator<String> targetFNames = mreq.getFileNames();
		
		try {
			while(targetFNames.hasNext()) {
				
				//파일객체 생성
				String targetFName = targetFNames.next();
				MultipartFile mFile = mreq.getFile(targetFName);
				
				//파일 이름 조정
				String nowDate = DateUtil.getDateTime();
				String orgFileName = mFile.getOriginalFilename();
				String fullFileName = nowDate+orgFileName;
				
				//경로 설정
					//서버 경로
/*					String nasPath = fileServerProperty.getProperty("nas.path");
					String fServerPath = fileServerProperty.getProperty("ddns.path");*/
					
					//파일 경로 설정(파일생성용)
					String orgNasfilePath = properties.getProperty("nas.path")+fullFileName;
					String filePath = properties.getProperty("ddns.path")+fullFileName;
				
				//파일 생성 절차
					
					//원본파일 생성
					byte[] img = mFile.getBytes();
					FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
					oStream.write(img);
					oStream.close();
				
					
				//저장된 파일 정보 추출
				File file = new File(orgNasfilePath);
				
				//파라미터 정리
				params.put("orgFileName", orgFileName);
				params.put("fullFileName", fullFileName);
				params.put("filePath", filePath);
				params.put("uid", uid);
				
				deviceDao.insertSound(params);
			}
		} catch (IOException e) {
			result = "FAIL";
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> selectAllPicture(int deviceCode) {
		return deviceDao.selectAllPicture(deviceCode);
	}

	@Override
	public Map<String, Object> selectAllDevice(int deviceCode) {
		return deviceDao.selectAllDevice(deviceCode);
	}
}
