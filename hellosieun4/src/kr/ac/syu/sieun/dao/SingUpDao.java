package kr.ac.syu.sieun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.syu.sieun.dto.Members;

@Repository
public class SingUpDao {

	@Autowired
	private SqlSession sqlSession;

	String ns = "sieun.Members.";
	public void insertMember(Members member) {
		sqlSession.insert(ns+"insertMember", member);
	}
	
	
}
