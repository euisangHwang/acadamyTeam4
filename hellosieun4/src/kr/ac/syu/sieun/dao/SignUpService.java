package kr.ac.syu.sieun.dao;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.syu.sieun.dto.Members;

public interface SignUpService {

	String insertMember(MultipartHttpServletRequest req) throws Exception;

	HashMap<String, Object> selectOneMember(int memberCode) throws Exception;

	String updateMember(MultipartHttpServletRequest req) throws Exception;

}
