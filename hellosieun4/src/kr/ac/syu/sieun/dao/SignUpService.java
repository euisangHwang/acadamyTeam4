package kr.ac.syu.sieun.dao;

import kr.ac.syu.sieun.dto.Members;

public interface SignUpService {

	void insertMember(Members member) throws Exception;
}
