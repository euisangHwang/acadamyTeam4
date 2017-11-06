package kr.ac.syu.sieun.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cmmn.DateUtil;
import kr.ac.syu.sieun.dto.Members;

@Service
public class SingUpServiceImpl implements SignUpService{

	@Autowired
	private SingUpDao signUpDao;
	
	@Resource(name="fileServer")
	private Properties properties;
	
	@Override
	@Transactional
	public String insertMember(MultipartHttpServletRequest mreq) throws Exception {
		
		String result = "SUCCESS";
		String fileExist = mreq.getParameter("fileExist");
		HashMap<String,Object> params = new HashMap<String,Object>();
		Iterator<String> targetFNames = mreq.getFileNames();
		
		int pictureCode = 0;
		try {
		
			if(fileExist == "Y" && targetFNames.hasNext()) {
				
				//파일객체 생성
				String targetFName = targetFNames.next();
				MultipartFile mFile = mreq.getFile(targetFName);
				
				//파일 이름 조정
				String nowDate = DateUtil.getDateTime();
				String orgFileName = mFile.getOriginalFilename();
				String fullFileName = nowDate+orgFileName;
				
				String tOrgFileName = "thumb_"+orgFileName;
				String tfullFileName = nowDate+tOrgFileName;
				
				//경로 설정
					//서버 경로
					String nasPath = properties.getProperty("nas.path");
					String fServerPath = properties.getProperty("ddns.path");
					
					//파일 경로 설정(파일생성용)
					String orgNasfilePath = nasPath+fullFileName;
					String filePath = fServerPath+fullFileName;
					
					String orgThumbFilePath = nasPath+tfullFileName;
					String tFilePath = fServerPath+tfullFileName;
				
				//파일 생성 절차
					
					//원본파일 생성
					byte[] img = mFile.getBytes();
					FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
					oStream.write(img);
					oStream.close();
				
					//썸네일파일 생성
						//원본파일 객체
						File output = new File(orgNasfilePath);
						//썸네일 경로의 객체
						File rOutput = new File(orgThumbFilePath);
						//원본파일 이미지 랜더링 
						BufferedImage renderedImg = null; 
						BufferedImage bi = ImageIO.read(output);
						renderedImg = new BufferedImage(318, 220, BufferedImage.TYPE_3BYTE_BGR);
						java.awt.Graphics2D g = renderedImg.createGraphics();
						g.drawImage(bi, 0, 0, 318, 220, null);
						
						//썸네일파일 생성
						ImageIO.write(renderedImg, "JPEG", rOutput);
					
				//파라미터 정리
				params.put("orgFileName", orgFileName);
				params.put("fullFileName", fullFileName);
				params.put("filePath", filePath);

				params.put("tOrgFileName", tOrgFileName);
				params.put("tfullFileName", tfullFileName);
				params.put("tFilePath", tFilePath);
				params.put("deviceCode", 0);
				
				signUpDao.insertImg(params);
				
				pictureCode = signUpDao.selectOnePicture(fullFileName);
			}
			
				Enumeration<String> names = mreq.getParameterNames();
				params = new HashMap<String,Object>();
				
				//회원정보 저장 시작
				while (names.hasMoreElements()) {
					
					String key = names.nextElement();
					if(key == "_homeimg") {
						
						if(pictureCode != 0)
							params.put("homeimg", pictureCode);
						else 
							params.put("homeimg", 0);
					} else {
						
						String value = mreq.getParameter(key);
						params.put(key, value);
					}
				}
				signUpDao.insertMember(params);
				
			
		} catch (IOException e) {
			result = "FAIL";
		}
		
		return result;
	}

	@Override
	public HashMap<String, Object> selectOneMember(int memberCode) throws Exception {
		return signUpDao.selectOneMember(memberCode);
	}

	@Override
	@Transactional
	public String updateMember(MultipartHttpServletRequest mreq) throws Exception {
		
		String result = "SUCCESS";
		String fileExist = mreq.getParameter("fileExist");
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		Iterator<String> targetFNames = mreq.getFileNames();
		
		int pictureCode = 0;
		try {
			
			if(fileExist.equals("Y") && targetFNames.hasNext()) {
				
				//기존 도면 사진 삭제
				int beforeImgCode = Integer.parseInt((String)mreq.getParameter("beforeImgCode"));
				String bHomeImgPath = mreq.getParameter("beforeImgPath");
				
				if(beforeImgCode != 0) {
					
					File imgFile = new File(bHomeImgPath);
					imgFile.delete();
					
					signUpDao.deleteImg(beforeImgCode);
				}
				
				//업로드 시작
				//파일객체 생성
				String targetFName = targetFNames.next();
				System.out.println("targetFName : "+targetFName);
				
				MultipartFile mFile = mreq.getFile(targetFName);
				
				//파일 이름 조정
				String nowDate = DateUtil.getDateTime();
				String orgFileName = mFile.getOriginalFilename();
				String fullFileName = nowDate+orgFileName;
				
				String tOrgFileName = "thumb_"+orgFileName;
				String tfullFileName = nowDate+tOrgFileName;
				
				//경로 설정
					//서버 경로
					String nasPath = properties.getProperty("nas.path");
					String fServerPath = properties.getProperty("ddns.path");
					
					//파일 경로 설정(파일생성용)
					String orgNasfilePath = nasPath+fullFileName;
					String filePath = fServerPath+fullFileName;
					
					String orgThumbFilePath = nasPath+tfullFileName;
					String tFilePath = fServerPath+tfullFileName;
				
				//파일 생성 절차
					
					//원본파일 생성
					byte[] img = mFile.getBytes();
					
					FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
					oStream.write(img);
					oStream.close();
				
					//썸네일파일 생성
						//원본파일 객체
						File output = new File(orgNasfilePath);
						//썸네일 경로의 객체
						File rOutput = new File(orgThumbFilePath);
						//원본파일 이미지 랜더링 
						BufferedImage renderedImg = null; 
						BufferedImage bi = ImageIO.read(output);
						renderedImg = new BufferedImage(318, 220, BufferedImage.TYPE_3BYTE_BGR);
						java.awt.Graphics2D g = renderedImg.createGraphics();
						g.drawImage(bi, 0, 0, 318, 220, null);
						
						//썸네일파일 생성
						ImageIO.write(renderedImg, "JPEG", rOutput);
					
				//파라미터 정리
				params.put("orgFileName", orgFileName);
				params.put("fullFileName", fullFileName);
				params.put("filePath", filePath);

				params.put("tOrgFileName", tOrgFileName);
				params.put("tfullFileName", tfullFileName);
				params.put("tFilePath", tFilePath);
				params.put("deviceCode", 0);
				params.put("beforeImgCode", beforeImgCode);
				
				signUpDao.insertImg(params);
				
				pictureCode = signUpDao.selectOnePicture(fullFileName);
			}
			
			Enumeration<String> names = mreq.getParameterNames();
			int memberCode = (int)mreq.getSession().getAttribute("sessMCode");
			params = new HashMap<String,Object>();
			params.put("memberCode", memberCode);
			
			//회원정보 저장 시작
			while (names.hasMoreElements()) {
				
				String key = names.nextElement();
				if(key == "homeimg") {
					
					if(pictureCode != 0)
						params.put("homeimg", pictureCode);
					else 
						params.put("homeimg", 0);
				} else {
					
					String value = mreq.getParameter(key);
					params.put(key, value);
				}
			}
			signUpDao.updateMember(params);
				
				
		} catch (Exception e) {
			result = "FAIL";
		}
	
		return result;		
	}


}
