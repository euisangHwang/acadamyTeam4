package kr.ac.syu.sieun.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Resource(name="ftpUser")
	private Properties ftpProperties;
	
	@Override
	public List<Map<String, Object>> selectAllDevice(int uMCode, String deviceSort) {
		
		System.out.println("왔다.");
		return deviceDao.selectAllDevice(uMCode, deviceSort);
	}

	@Override
	public Map<String, Object> selectAllDevice(HashMap<String, Object> param) {
		
		System.out.println("왔다2.");
		return deviceDao.selectAllDevice(param);
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
		System.out.println("targetNames 투스트링 ::: "+targetFNames.toString());
		
		//FTP 정보
		String host = ftpProperties.getProperty("host");
		String port = ftpProperties.getProperty("availableServerPort");
		String userid = ftpProperties.getProperty("userid");
		String password = ftpProperties.getProperty("password");
		String remoteFilePath = "";
		
		try {
			int numnum = 0;
			if(targetFNames.hasNext()) {
				
				numnum++;
				
			//객체생성 파트	
				//파일객체 생성
				String targetFName = targetFNames.next();
				System.out.println("targetName ::: "+targetFName);
				//FTP객체 생성
				FTPClient ftp = new FTPClient();
				ftp.setControlEncoding("UTF-8");  // 문자 코드를 UTF-8로 인코딩
				ftp.connect(host, Integer.parseInt(port));
				ftp.login(userid, password);
				ftp.enterLocalPassiveMode(); // Passive Mode 접속일 때
				ftp.changeWorkingDirectory(remoteFilePath);
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				
				
			//작업 파트
				List<MultipartFile> multiFile = mreq.getFiles(targetFName);
				
				for(int i=0; i<multiFile.size(); i++) {

				//업로드	
					MultipartFile mFile = multiFile.get(i);
					
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
						System.out.println("beforeWrite ::: "+numnum);
						oStream.write(img);
						System.out.println("afforeWrite ::: "+numnum);
						oStream.close();
						System.out.println("afterClose ::: "+numnum);
						
					//저장된 파일 정보 추출
					File file = new File(orgNasfilePath);
					
					//파라미터 정리
					params.put("orgFileName", orgFileName);
					params.put("fullFileName", fullFileName);
					params.put("filePath", filePath);
					params.put("uid", uid);
					
					deviceDao.insertSound(params);
					
				//FTP업로드
					FileInputStream iStream = new FileInputStream(file);
					boolean isSuccess = ftp.storeFile(orgFileName, iStream);
					
					if(!isSuccess)
						result = "FAIL";
					
					iStream.close();
					ftp.logout();
					ftp.disconnect();
				}
			}
		} catch (IOException e) {
			result = "FAIL";
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectAllPicture(int deviceCode) {
		return deviceDao.selectAllPicture(deviceCode);
	}

	@Override
	public String deleteSound(int[] delMusics) {
		
		String result = "SUCCESS";
		
		try{
			for(int i=0; i<delMusics.length; i++) {
				
				Map<String,Object> eachMusic = deviceDao.selectOneMusic(delMusics[i]);
				String filePath = properties.getProperty("nas.path")+(String)eachMusic.get("mFullName");

				File file = new File(filePath);
				file.delete();
				
				deviceDao.deleteSound(delMusics[i]);
			}
		} catch (Exception e) {
			result = "FAIL";
		}
		return result;
	}
	
	@Override
	public void insertCmd(HashMap<String, Object> param) {
		deviceDao.insertCmd(param);
	}
	
	@Override
	public List<Map<String, Object>> selectAllDevices(int uMCode) {
		return deviceDao.selectAllDevices(uMCode);
	}

	@Override
	public String insertSpeakMusicMatch(HashMap<String, Object> param) {
		
		String result = "SUCCESS";
		
		try {
			
			deviceDao.insertSpeakMusicMatch(param);
		} catch (Exception e) {
			result = "FAIL";
		}
		
		return result;
	}

	@Override
	public Map<String, Object> selectMatchSpeakMusic(int deviceCode) {
		return deviceDao.selectMatchSpeakMusic(deviceCode);
	}

	@Override
	public String updateMatch(HashMap<String, Object> param) {
		
		String result = "SUCCESS";
		
		try {
			
			deviceDao.updateMatch(param);
		} catch (Exception e) {
			result = "FAIL";
		}
		
		return result;
	}

	@Override
	public Map<String, Object> selectHomeImg(int memberCode) {
		return deviceDao.selectHomeImg(memberCode);
	}

	@Override
	public List<Map> selectCmd(int deviceCode) {
		return deviceDao.selectCmd(deviceCode);
	}
}
