package kr.ac.syu.sieun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.syu.sieun.dto.Members;
// POJO 
@Service
public class MemeberServiceImpl implements IMemeberService {
    
	@Autowired
	private MemberDao memberDao;
	
	@Override
	@Transactional
	public void addMembers(Members member) {
		memberDao.addMembers(member);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Members> getAllMembers() {
		return memberDao.getAllMembers();
	}



}
