package kr.ac.syu.sieun.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {

	@Autowired
	private SqlSession sqlSession;
	
	String ns = "sieun.Members.";
	public int isUser(Map<String, Object> param) throws Exception{
		return sqlSession.selectOne(ns+"isUser", param);
	}

}
