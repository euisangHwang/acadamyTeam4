package kr.ac.syu.sieun.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String IsUserIdDuplicate(String userId) throws Exception {
		
		String result = "FINE";
		int num = signUpDao.isUserIdDuplicate(userId);
		
		System.out.println("num : "+num);
		
		if(num > 0)
			 result = "DUPLICATE"; 
		
		return result;
	}	
	
	@Override
	public String findIdByPhone(String phone) throws Exception {
		
		String id = signUpDao.findIdByPhone(phone);
		if(id == null)
			id = "FAIL"; 
		
		return id;
	}
	
	@Override
	public String checkPhone(HashMap<String, Object> param) throws Exception {

		String result = "isValid";
		int num = signUpDao.checkPhone(param);
		
		if(num < 1) 
			result = "nonExist";
		
		return result;
	}
	
	@Override
	public String updatePw(HashMap<String, Object> param) throws Exception {
		
		String result = "SUCCESS";
		
		try {
			
			signUpDao.updatePw(param);
		} catch (Exception e) {
			
			result = "FAIL";
		}
		
		return result;
	}
	
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
				
				//���ϰ�ü ����
				String targetFName = targetFNames.next();
				MultipartFile mFile = mreq.getFile(targetFName);
				
				//���� �̸� ����
				String nowDate = DateUtil.getDateTime();
				String orgFileName = mFile.getOriginalFilename();
				String fullFileName = nowDate+orgFileName;
				
				String tOrgFileName = "thumb_"+orgFileName;
				String tfullFileName = nowDate+tOrgFileName;
				
				//��� ����
					//���� ���
					String nasPath = mreq.getServletContext().getRealPath("/");
					String fServerPath = mreq.getServletContext().getRealPath("/");
					
					System.out.println("nasPath : "+nasPath);
					System.out.println("fServerPath : "+fServerPath);
					
					//���� ��� ����(���ϻ�����)
					String orgNasfilePath = nasPath+fullFileName;
					String filePath = 11+fullFileName;
					
					String orgThumbFilePath = nasPath+tfullFileName;
					String tFilePath = fServerPath+tfullFileName;
				
				//���� ���� ����
					
					//�������� ����
					byte[] img = mFile.getBytes();
					FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
					oStream.write(img);
					oStream.close();
				
					//��������� ����
						//�������� ��ü
						File output = new File(orgNasfilePath);
						//����� ����� ��ü
						File rOutput = new File(orgThumbFilePath);
						//�������� �̹��� ������ 
						BufferedImage renderedImg = null; 
						BufferedImage bi = ImageIO.read(output);
						renderedImg = new BufferedImage(318, 220, BufferedImage.TYPE_3BYTE_BGR);
						java.awt.Graphics2D g = renderedImg.createGraphics();
						g.drawImage(bi, 0, 0, 318, 220, null);
						
						//��������� ����
						ImageIO.write(renderedImg, "JPEG", rOutput);
					
				//�Ķ���� ����
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
				
				//ȸ������ ���� ����
				while (names.hasMoreElements()) {
					
					String key = names.nextElement();
					if(key.equals("_homeimg")) {
						
						if(pictureCode != 0)
							params.put("homeimg", pictureCode);
						else 
							params.put("homeimg", 0);
						
						System.out.println("_homeimg : �����ϱ� ");
					} /*else if (key.equals("address")) {
						
						String value = mreq.getParameter("address").replaceAll(":", "/");
						System.out.println("jsuo �� : "+value);
						params.put(key, value);
						
					}*/ else {
						
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
				
				//���� ���� ���� ����
				int beforeImgCode = Integer.parseInt((String)mreq.getParameter("beforeImgCode"));
				String bHomeImgPath = mreq.getParameter("beforeImgPath");
				
				if(beforeImgCode != 0) {
					
					File imgFile = new File(bHomeImgPath);
					imgFile.delete();
					
					signUpDao.deleteImg(beforeImgCode);
				}
				
				//���ε� ����
				//���ϰ�ü ����
				String targetFName = targetFNames.next();
				System.out.println("targetFName : "+targetFName);
				
				MultipartFile mFile = mreq.getFile(targetFName);
				
				//���� �̸� ����
				String nowDate = DateUtil.getDateTime();
				String orgFileName = mFile.getOriginalFilename();
				String fullFileName = nowDate+orgFileName;
				
				String tOrgFileName = "thumb_"+orgFileName;
				String tfullFileName = nowDate+tOrgFileName;
				
				//��� ����
					//���� ���
					System.out.println("����´�****************************************************");
					String nasPath = mreq.getServletContext().getRealPath("/");
					String fServerPath = mreq.getServletContext().getRealPath("/");
				
					System.out.println("nasPath : "+nasPath);
					System.out.println("fServerPath : "+fServerPath);
				
					//���� ��� ����(���ϻ�����)
					String orgNasfilePath = nasPath+fullFileName;
					String filePath = fServerPath+fullFileName;
					
					String orgThumbFilePath = nasPath+tfullFileName;
					String tFilePath = fServerPath+tfullFileName;
				
				//���� ���� ����
					
					//�������� ����
					byte[] img = mFile.getBytes();
					
					FileOutputStream oStream = new FileOutputStream(orgNasfilePath);
					oStream.write(img);
					oStream.close();
				
					//��������� ����
						//�������� ��ü
						File output = new File(orgNasfilePath);
						//����� ����� ��ü
						File rOutput = new File(orgThumbFilePath);
						//�������� �̹��� ������ 
						BufferedImage renderedImg = null; 
						BufferedImage bi = ImageIO.read(output);
						renderedImg = new BufferedImage(318, 220, BufferedImage.TYPE_3BYTE_BGR);
						java.awt.Graphics2D g = renderedImg.createGraphics();
						g.drawImage(bi, 0, 0, 318, 220, null);
						
						//��������� ����
						ImageIO.write(renderedImg, "JPEG", rOutput);
					
				//�Ķ���� ����
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
				System.out.println("*************************************************pictureCode : "+pictureCode+"*******************");
			}
			
			Enumeration<String> names = mreq.getParameterNames();
			int memberCode = (int)mreq.getSession().getAttribute("sessMCode");
			params = new HashMap<String,Object>();
			params.put("memberCode", memberCode);
			System.out.println("pictureCode : "+pictureCode);
			
			if(pictureCode != 0) {
				System.out.println("0�ƴϴ�");
				params.put("homeimg", pictureCode);
			}
			else {
				System.out.println("0�̴�");
				params.put("homeimg", 0);
			}
			
			//ȸ������ ���� ����
			while (names.hasMoreElements()) {
				
				String key = names.nextElement();
				String value = mreq.getParameter(key);
				System.out.println("key : "+key+" / value�� : "+value+"*****************************************************");
					
				params.put(key, value);
			}
			
			System.out.println("homeimg : "+params.get("homeimg"));
			signUpDao.updateMember(params);
				
				
		} catch (Exception e) {
			result = "FAIL";
		}
	
		return result;		
	}
	
 /**==============================================================
    Description        :  ��� �Լ� ����
 ==============================================================**/
   /**
   * nullcheck
   * @param str, Defaultvalue
   * @return
   */

   public String nullcheck(String str,  String Defaultvalue ) throws Exception
   {
        String ReturnDefault = "" ;
        if (str == null)
        {
            ReturnDefault =  Defaultvalue ;
        }
        else if (str == "" )
       {
            ReturnDefault =  Defaultvalue ;
        }
        else
        {
                    ReturnDefault = str ;
        }
         return ReturnDefault ;
   }

   /**
   * BASE64 Encoder
   * @param str
   * @return
   */
  public String base64Encode(String str)  throws java.io.IOException {
      sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
      byte[] strByte = str.getBytes();
      String result = encoder.encode(strByte);
      return result ;
  }

  /**
   * BASE64 Decoder
   * @param str
   * @return
   */
  public String base64Decode(String str)  throws java.io.IOException {
      sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
      byte[] strByte = decoder.decodeBuffer(str);
      String result = new String(strByte);
      return result ;
  }   

/**==============================================================
  Description        : ĳ���ͼ� ����
  EUC-KR: @ page contentType="text/html;charset=EUC-KR
  UTF-8: @ page contentType="text/html;charset=UTF-8
==============================================================**/
  
  public String sendSMSMsg (HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	//msg��, �޴¹�ȣ �Ķ���ͷ� �ޱ�  
	//msg, rphone
	  
    String charsetType = "UTF-8"; //EUC-KR �Ǵ� UTF-8
    request.setCharacterEncoding(charsetType);
    response.setCharacterEncoding(charsetType);
    
        String sms_url = "";
        sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS ���ۿ�û URL
        String user_id = base64Encode("hes1937123"); // SMS���̵�
        String secure = base64Encode("3f292bcf1ca346e9d1d8617dbcd1037e");//����Ű
        String msg = base64Encode(nullcheck(request.getParameter("msg"), ""));
        String rphone = base64Encode(nullcheck(request.getParameter("rphone"), ""));
        String sphone1 = base64Encode("010");
        String sphone2 = base64Encode("4666");
        String sphone3 = base64Encode("8470");
        String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
        String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
        String mode = base64Encode("1");
        String subject = "";
        if(nullcheck(request.getParameter("smsType"), "").equals("L")) {
            subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
        }
        String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
        String destination = base64Encode(nullcheck(request.getParameter("destination"), ""));
        String repeatFlag = base64Encode(nullcheck(request.getParameter("repeatFlag"), ""));
        String repeatNum = base64Encode(nullcheck(request.getParameter("repeatNum"), ""));
        String repeatTime = base64Encode(nullcheck(request.getParameter("repeatTime"), ""));
        String returnurl = nullcheck(request.getParameter("returnurl"), "");
        String nointeractive = nullcheck(request.getParameter("nointeractive"), "");
        String smsType = base64Encode(nullcheck(request.getParameter("smsType"), ""));

        String[] host_info = sms_url.split("/");
        String host = host_info[2];
        String path = "/" + host_info[3];
        int port = 80;

        // ������ ���� ���� ����
        String arrKey[]
            = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
                        ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType", "subject"};
        String valKey[]= new String[arrKey.length] ;
        valKey[0] = user_id;
        valKey[1] = secure;
        valKey[2] = msg;
        valKey[3] = rphone;
        valKey[4] = sphone1;
        valKey[5] = sphone2;
        valKey[6] = sphone3;
        valKey[7] = rdate;
        valKey[8] = rtime;
        valKey[9] = mode;
        valKey[10] = testflag;
        valKey[11] = destination;
        valKey[12] = repeatFlag;
        valKey[13] = repeatNum;
        valKey[14] = repeatTime;
        valKey[15] = smsType;
        valKey[16] = subject;

        String boundary = "";
        Random rnd = new Random();
        String rndKey = Integer.toString(rnd.nextInt(32000));
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytData = rndKey.getBytes();
        md.update(bytData);
        byte[] digest = md.digest();
        for(int i =0;i<digest.length;i++)
        {
            boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
        }
        boundary = "---------------------"+boundary.substring(0,11);

        // ���� ����
        String data = "";
        String index = "";
        String value = "";
        for (int i=0;i<arrKey.length; i++)
        {
            index =  arrKey[i];
            value = valKey[i];
            data +="--"+boundary+"\r\n";
            data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
            data += "\r\n"+value+"\r\n";
            data +="--"+boundary+"\r\n";
        }

        //out.println(data);

        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(host, port);
        // ��� ����
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
        wr.write("POST "+path+" HTTP/1.0\r\n");
        wr.write("Content-Length: "+data.length()+"\r\n");
        wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
        wr.write("\r\n");

        // ������ ����
        wr.write(data);
        wr.flush();

        // ����� ���
        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),charsetType));
        String line;
        String alert = "";
        ArrayList tmpArr = new ArrayList();
        while ((line = rd.readLine()) != null) {
            tmpArr.add(line);
        }
        wr.close();
        rd.close();

        String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
        String[] rMsg = tmpMsg.split(",");
        String Result= rMsg[0]; //�߼۰��
        String Count= ""; //�ܿ��Ǽ�
        if(rMsg.length>1) {Count= rMsg[1]; }

                        //�߼۰�� �˸�
        if(Result.equals("success")) {
            alert = "���������� �߼��Ͽ����ϴ�.";
            alert += " �ܿ��Ǽ��� "+ Count+"�� �Դϴ�.";
        }
        else if(Result.equals("reserved")) {
            alert = "���������� ����Ǿ����ϴ�";
            alert += " �ܿ��Ǽ��� "+ Count+"�� �Դϴ�.";
        }
        else if(Result.equals("3205")) {
            alert = "�߸��� ��ȣ�����Դϴ�.";
        }
        else {
            alert = "[Error]"+Result;
        }

/*        PrintWriter out = response.getWriter();
        out.println(nointeractive);

        if(nointeractive.equals("1") && !(Result.equals("Test Success!")) && !(Result.equals("success")) && !(Result.equals("reserved")) ) {
            out.println("<script>alert('" + alert + "')</script>");
        }
        else if(!(nointeractive.equals("1"))) {
            out.println("<script>alert('" + alert + "')</script>");
        }


        out.println("<script>location.href='"+returnurl+"';</script>");*/
	  
	  return Result;
  }
}
