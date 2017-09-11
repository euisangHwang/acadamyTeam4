package egovframework.example.upload.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.example.cmmn.DateUtil;
import egovframework.example.cmmn.JsonUtil;
import egovframework.example.upload.service.UploadService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class UploadController {

	@Resource(name = "uploadService")
	private UploadService uploadService;

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
	
	@RequestMapping(value = "/main.do")
	public String main() throws Exception {

		
		return "upload/main";
	}
	
	@RequestMapping(value = "/Fupload.do")
	public void Fupload(MultipartHttpServletRequest mRequest, HttpServletResponse response, ModelMap model) throws Exception {
		
		System.out.println("오긴오냐");

/**업로드 파일을 타겟으로 잡는 작업 **/
		Iterator<String> Iterator =  mRequest.getFileNames(); //file : js에 있는 필드(설정된)값 리턴 [파일을 타겟으로 잡기 위한]
		String fileName2 = Iterator.next(); //값 : file
		
		System.out.println("fileName2 ::: "+fileName2);
		MultipartFile multipartFile2 = mRequest.getFile(fileName2); 
		
		String jinjjaName2 = multipartFile2.getOriginalFilename();
		System.out.println("jinjjaName2 ::: "+jinjjaName2);
		
		
		MultipartFile multipartFile = mRequest.getFile("file"); //이제 멀티파트 파일안에, 타겟이 잡힘 (진짜 이름과 file이 담김)


/**업로드 파일의  경로(경로+날짜+이름) 및 파일(추출) 설정 작업 **/		
		String jinjjaName = multipartFile.getOriginalFilename();
		System.out.println("fileName ::: file");
		System.out.println("jinjjaName ::: "+jinjjaName);
		String nowDate 	  = DateUtil.getDateTime();
		String RfileName  = nowDate + jinjjaName;
		String nasPath	  = propertiesService.getString("nas.path");
		String ddnsPath	  = propertiesService.getString("ddns.path");
		
		String RfilePath = nasPath + RfileName;
		byte[] Rfile	 = multipartFile.getBytes();

/**파일을 해당 경로에 저장하는 작업 **/		
		FileOutputStream fos = new FileOutputStream(RfilePath); //해당 파일경로에 아웃풋스트림 생성
		
		fos.write(Rfile);  // 해당 경로에 파일 저장
		fos.close();	   // 아웃풋 스트림 종료
		response.getWriter().println(Rfile);

/**저장된 파일을 불러내 내려주는 작업 **/		
		
		File savedFile = new File(RfilePath);
		long fileSize  = savedFile.length();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("jinjjaName", jinjjaName);
		params.put("fileSize", fileSize);
		params.put("RfileName", RfileName);
		params.put("RfilePath", ddnsPath + RfileName);
		
		uploadService.insertFile(params);
		
		System.out.println(nasPath);
	}
	
	@RequestMapping(value = "/onUpload.do")
	public void onUpload(@RequestParam String signTrgNo, HttpServletResponse response) throws Exception {

		System.out.println("여기 와라");
		
		response.setCharacterEncoding("UTF-8");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("signTrgNo", signTrgNo);
		
		List<Map<String, Object>> Files = uploadService.selectFiles(params);
		
		PrintWriter out = response.getWriter();
		
		out.write(JsonUtil.ListToJson(Files).toString());
	}
}
