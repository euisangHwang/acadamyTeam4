package kr.ac.syu.sieun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.syu.sieun.dto.Members;

@Service
public class SingUpServiceImpl implements SignUpService{

	@Autowired
	private SingUpDao signUpDao;
	
	@Override
	public void insertMember(Members member) throws Exception {
		signUpDao.insertMember(member);
	}

}
