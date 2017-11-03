package kr.ac.syu.sieun.dao;

import java.util.List;

import kr.ac.syu.sieun.dto.Members;

public interface IMemeberService {
	 void addMembers(Members member);
	 List<Members> getAllMembers();
}
