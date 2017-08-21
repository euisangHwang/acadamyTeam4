package work.controller;

import java.util.ArrayList;

import work.model.dto.BookMember;
import work.model.service.BookMemberService;
import work.model.service.BookService;
import work.util.BookUtil;

/**
 * 사용자가 요구하는 요청을
 * 적절한 기능으로 연결시켜주는 역할
 * 
 * ## Controler 클래스 역할
 * 1. 요청파악
 * 2. 요청데이터 추출 : 매개변수 전달
 * 3. 요청데이터 검증
 * 4. Model 요청처리 의뢰
 * 5. Model 요청결과 받아서 응답위한 설정
 * 6. 결과 응답  :  화면 이용
 */
public class BookMemberControler {
	
	private BookMemberService service = new BookMemberService();
	private BookService service2 = new BookService();
	
	public String insertBookMember(BookMember member) {
		return service.insertBookMember(member);
	}

	public String updateOneUserPw(String userId, String userPw) {
		return service.updateOneUserPw(userId, userPw);
	}

	public String deleteOneBookMember(String userId) {
		return service.deleteOneBookMember(userId);
	}

	//특정 회원 <구매> 특정 도서 => 
	//--특정 회원 계좌잔고 업데이트
	//--서점계좌(관리자계좌)잔고 업데이트
	//--특정 도서 재고 업데이트 
	//--회원아이디, 도서번호, 수량을 입력받고 / 구매일시 자동등록해서 구매이력에 등록 
	
	public String purchaseBook(String userId, int bookNum, int stock) {
		
		String result = null;
		
		System.out.println("오긴와?");
		
		if (userId != null && stock != 0) {
		
			System.out.println("여기는??");
			
			if (service.updateOneUserBalance(userId, bookNum, stock) != "fail") {
				
				System.out.println("여기오냐?");
				
				ArrayList<Integer> bStock = service2.selectStockByBookNum(bookNum);
				if (service2.updateStockByBookNum(bookNum, bStock.get(1)) != "fail") {
					
/*					ORDER_NUM   NOT NULL NUMBER       
					BOOK_NUM    NOT NULL NUMBER       
					USER_ID     NOT NULL VARCHAR2(20) 
					ORDER_DATE  NOT NULL VARCHAR2(20) 
					ORDER_STOCK NOT NULL NUMBER       
					ORDER_PRICE NOT NULL NUMBER  */     
					
					result = service.purchaseBook(BookUtil.createSecureNumber(5), bookNum, userId, BookUtil.getCurrentDate(), stock, bStock.get(1));
				}
			} else {
				
			}
		}
		return result;
	}
}
