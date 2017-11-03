package kr.ac.syu.sieun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.syu.sieun.dto.Members;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private String ns="sieun.Members.";
	public void addMembers(Members member){
		sqlSession.insert(ns+"addMembers",member);
	}
	
	public List<Members> getAllMembers(){
		return sqlSession.selectList(ns+"getAllMembers");
	}
	
}
