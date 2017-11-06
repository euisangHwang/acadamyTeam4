package kr.ac.syu.sieun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeivceDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Map<String, Object>> selectAllDevice(int uMCode, String deviceSort) {
		
		String ns = "sieun.Devices.";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("uMCode", uMCode);
		param.put("deviceSort", deviceSort);
		
		return sqlSession.selectList(ns+"selectAllDevice", param);
	}
	
	public Map<String, Object> selectAllDevice(HashMap<String, Object> param2) {
		String ns = "sieun.Devices.";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("deviceCode", param2);
		return sqlSession.selectOne(ns+"selectAllDevice", param2);
	}

	public List<Map<String, Object>> selectAllMusic(int uMCode) {
		
		String ns = "sieun.Devices.";
		return sqlSession.selectList(ns+"selectAllMusic", uMCode);
	}
	
	public Map<String, Object> selectOneMusic(int musicCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectOne(ns+"selectOneMusic", musicCode);
	}

	public void insertSound(Map<String, Object> params) {
		String ns = "sieun.Devices.";
		sqlSession.insert(ns+"insertSound", params);
		
	}

	public List<Map<String, Object>> selectAllPicture(int deviceCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectList(ns+"selectAllPicture", deviceCode);
	}

	public void deleteSound(int delMusic) {
		String ns = "sieun.Devices.";
		sqlSession.delete(ns+"deleteSound", delMusic);
	}

	public void insertCmd(HashMap<String, Object> param) {
		String ns = "sieun.Devices.";
		sqlSession.insert(ns+"insertCmd", param);
	}

	public List<Map<String, Object>> selectAllDevices(int uMCode) {
		
		String ns = "sieun.Devices.";
		return sqlSession.selectList(ns+"selectAllDevices", uMCode);
	}

	public void insertSpeakMusicMatch(HashMap<String, Object> param) {
		String ns = "sieun.Devices.";
		sqlSession.selectList(ns+"insertSpeakMusicMatch", param);
	}

	public Map<String, Object> selectMatchSpeakMusic(int deviceCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectOne(ns+"selectMatchSpeakMusic", deviceCode);
	}

	public void updateMatch(HashMap<String, Object> param) {
		String ns = "sieun.Devices.";
		sqlSession.selectOne(ns+"updateMatch", param);
	}

	public Map<String, Object> selectHomeImg(int memberCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectOne(ns+"selectHomeImg", memberCode);
	}
	
	
}
