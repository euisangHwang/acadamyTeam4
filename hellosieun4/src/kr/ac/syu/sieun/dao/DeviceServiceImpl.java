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
import org.apache.commons.net.ftp.FTPReply;
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
		
		System.out.println("�Դ�.");
		return deviceDao.selectAllDevice(uMCode, deviceSort);
	}

	@Override
	public Map<String, Object> selectAllDevice(HashMap<String, Object> param) {
		
		System.out.println("�Դ�2.");
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
		System.out.println("targetNames ����Ʈ�� ::: "+targetFNames.toString());
		
		//FTP ����
		String host = ftpProperties.getProperty("host");
		String port = ftpProperties.getProperty("availableServerPort");
		String userid = ftpProperties.getProperty("userid");
		String password = ftpProperties.getProperty("password");
		String remoteFilePath = "/home/ftpuser/ftpuser";
		
		System.out.println("host:port = "+host+":"+port);
		
		FTPClient ftp = null;
		boolean login = false;
		boolean isSuccess = false;
		FileInputStream iStream = null;
		
		try {
			int numnum = 0;
			if(targetFNames.hasNext()) {
				
				numnum++;
				
			//��ü���� ��Ʈ	
				//���ϰ�ü ����
				String targetFName = targetFNames.next();
				System.out.println("targetName ::: "+targetFName);
				System.out.println("contextPath ::: "+mreq.getServletContext().getRealPath("/"));
				//FTP��ü ����
				ftp = new FTPClient();
				ftp.setControlEncoding("UTF-8");  // ���� �ڵ带 UTF-8�� ���ڵ�
				ftp.connect(host, Integer.parseInt(port));
				int reply = ftp.getReplyCode();
				System.out.println("Ŀ�ؼ� ���� : "+reply);
				
				if(FTPReply.isPositiveCompletion(reply)) {
					
					login = ftp.login(userid, password);
					System.out.println("�α��� �������� : "+login);
				}
				
			//�۾� ��Ʈ
				List<MultipartFile> multiFile = mreq.getFiles(targetFName);
				
				for(int i=0; i<multiFile.size(); i++) {
				//���ε�	
					MultipartFile mFile = multiFile.get(i);
					
					//���� �̸� ����
					String nowDate = DateUtil.getDateTime();
					String orgFileName = mFile.getOriginalFilename();
					String fullFileName = nowDate+orgFileName;
					
					//��� ����
						//���� ���
	/*					String nasPath = fileServerProperty.getProperty("nas.path");
						String fServerPath = fileServerProperty.getProperty("ddns.path");*/
						//���� ��� ����(���ϻ�����)
						String orgNasfilePath = mreq.getServletContext().getRealPath("/")+fullFileName;
						String filePath = properties.getProperty("ddns.path")+fullFileName;
					
					//���� ���� ����
						//�������� ����
						byte[] img = mFile.getBytes();
						FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
						System.out.println("beforeWrite ::: "+numnum);
						oStream.write(img);
						System.out.println("afforeWrite ::: "+numnum);
						oStream.close();
						System.out.println("afterClose ::: "+numnum);
						
					//����� ���� ���� ����
					File file = new File(orgNasfilePath);
					
					//�Ķ���� ����
					params.put("orgFileName", orgFileName);
					params.put("fullFileName", fullFileName);
					params.put("filePath", filePath);
					params.put("uid", uid);
					
					deviceDao.insertSound(params);
				
					System.out.println("���� ���ε� ������");	
					
				//FTP���ε�
					if(login) {
						
						System.out.println("���Ͼ��ε� ����");
						
						ftp.setFileType(FTP.BINARY_FILE_TYPE);
						ftp.enterLocalPassiveMode(); // Passive Mode ������ ��
						ftp.changeWorkingDirectory(remoteFilePath);
						System.out.println("��ŷ���丮 : "+ftp.printWorkingDirectory());
						iStream = new FileInputStream(file);
						isSuccess = ftp.storeFile(orgFileName, iStream);
						System.out.println("���ε� �������� : "+isSuccess);
					}
					
					if(!isSuccess) 
						result = "FAIL";
				}
			}
		} catch (IOException e) {
		
			result = "FAIL";
			System.out.println("�������� : "+e.getMessage()+"�ڵ� : "+ftp.getReplyCode());
		} finally {
			
			try {
				if(iStream != null) iStream.close();
				if(login) ftp.logout();
				if(ftp != null) ftp.disconnect();
				
			} catch (IOException iex) {
				System.out.println(iex.getMessage());
			}
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

	@Override
	public String updateCmdWork(int comCode) {
		
		String result = "SUCCESS";
		try {
			
			deviceDao.updateCmdWork(comCode);
		} catch (Exception e) {
			result = "FAIL";
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> selectDeviceByMusic(int musicCode) {
		return deviceDao.selectDeviceByMusic(musicCode);
	}
	
	//���� �� Ŀ��� ���
	//Ŀ��� �ް� ��ġ����  (������Ʈ Ŀ��� �����)
	//
	
}
