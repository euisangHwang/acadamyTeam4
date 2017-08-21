package work.model.service;

import java.util.ArrayList;

import work.model.dao.BookDao;
import work.model.dao.BookMemberDao;
import work.model.dto.Book;
import work.model.dto.BookMember;

/**
 * 비즈니스 로직, 회원관리
 * 회원가입 서비스 : 회원이 입력한 정보를 데이터베이스에 저장하는 작업을 하는 곳.
 * @author User
 * 
 */
public class BookMemberService {

	private BookMemberDao dao = BookMemberDao.getInstance();

	public String insertBookMember(BookMember member) {
		return dao.insertBookMember(member);
	}

	public String updateOneUserPw(String userId, String userPw) {
		return dao.updateOneUserPw(userId, userPw);
	}

	public String deleteOneBookMember(String userId) {
		return dao.deleteOneBookMember(userId);
	}

	public int selectOneUserBalance(String userId) {
		return dao.selectOneUserBalance(userId);
	}
	
	public String updateOneUserBalance(String userId, int bookNum, int stock) {
		
		//이전에 있던 돈
		//책살 때 드는 돈 (수량)
		//계산 => 없으면 경고, 있으면 해당 결과를 매니저 계좌로 이체
		
		int beforMoney = dao.selectOneUserBalance(userId);
		int beforMoney2 = dao.selectOneUserBalance("manager01");
		int bookMoney = dao.selectBookMoney(bookNum);
		String result = null;
		
			int resultMoney = beforMoney - bookMoney;
			
			if(resultMoney <0) {
				
				result = "잔고가 부족합니다.";
			} else {
				
				result = dao.updateOneUserBalance(userId, resultMoney)+"\n";
				if(result != "fail") {
					
					result += dao.updateOneUserBalance("manager01", beforMoney2+bookMoney);
				} 
			}
			
			return result;
	}

	public String purchaseBook(int createSecureNumber, int bookNum,
			String userId, String currentDate, int stock, Integer integer) {
		
		return dao.purchaseBook(createSecureNumber, bookNum,
			userId, currentDate, stock, integer);
	}
	
	
}
