package kr.ac.syu.sieun.dao;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.syu.sieun.dto.Members;

public interface SignUpService {

	String insertMember(MultipartHttpServletRequest req) throws Exception;

	HashMap<String, Object> selectOneMember(int memberCode) throws Exception;

	String updateMember(MultipartHttpServletRequest req) throws Exception;

	String sendSMSMsg(HttpServletRequest req, HttpServletResponse res) throws Exception;

	String IsUserIdDuplicate(String userId) throws Exception;

	String findIdByPhone(String phone) throws Exception;

	String checkPhone(HashMap<String, Object> param) throws Exception;

	String updatePw(HashMap<String, Object> param) throws Exception;

	

}
