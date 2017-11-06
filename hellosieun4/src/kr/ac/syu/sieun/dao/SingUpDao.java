package kr.ac.syu.sieun.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.syu.sieun.dto.Members;

@Repository
public class SingUpDao {

	@Autowired
	private SqlSession sqlSession;

	String ns = "sieun.Members.";
	public void insertMember(HashMap<String, Object> params) {
		sqlSession.insert(ns+"insertMember", params);
	}
	
	public void updateMember(HashMap<String, Object> params) {
		sqlSession.insert(ns+"updateMember", params);
	}
	
	public HashMap<String, Object> selectOneMember(int memberCode) {
		return sqlSession.selectOne(ns+"selectOneMember", memberCode);
	}
	public void insertImg(HashMap<String, Object> params) {
		String ns = "sieun.Devices.";
		sqlSession.insert(ns+"insertImg", params);
	}
	public int selectOnePicture(String fullFileName) {
		String ns = "sieun.Devices.";
		return sqlSession.selectOne(ns+"selectOnePicture", fullFileName);
	}
	public void updateImg(HashMap<String, Object> params) {
		String ns = "sieun.Devices.";
		sqlSession.selectOne(ns+"updateImg", params);
	}

	public void deleteImg(int beforeImgCode) {
		String ns = "sieun.Devices.";
		sqlSession.selectOne(ns+"deleteImg", beforeImgCode);
	}

	
	
}
