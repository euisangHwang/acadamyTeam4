package kr.ac.syu.sieun.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public int isUser(Map<String, Object> param) throws Exception {
		return loginDao.isUser(param);
	}

}
