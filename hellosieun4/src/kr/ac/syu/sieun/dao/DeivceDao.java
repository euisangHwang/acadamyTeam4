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
	
	public Map<String, Object> selectAllDevice(int deviceCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectOne(ns+"selectAllDevice", deviceCode);
	}

	public List<Map<String, Object>> selectAllMusic(int uMCode) {
		
		String ns = "sieun.Devices.";
		return sqlSession.selectList(ns+"selectAllMusic", uMCode);
	}

	public void insertSound(Map<String, Object> params) {
		String ns = "sieun.Devices.";
		sqlSession.insert(ns+"insertSound", params);
		
	}

	public List<Map<String, Object>> selectAllPicture(int deviceCode) {
		String ns = "sieun.Devices.";
		return sqlSession.selectList(ns+"selectAllPicture", deviceCode);
	}


	
	
}
