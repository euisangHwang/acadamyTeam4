package work.model.service;

import java.util.ArrayList;

import work.model.dao.BookDao;
import work.model.dto.Book;

/**
 * 비즈니스 로직, 회원관리
 * 회원가입 서비스 : 회원이 입력한 정보를 데이터베이스에 저장하는 작업을 하는 곳.
 * @author User
 * 
 */
public class BookService {

	private BookDao dao = BookDao.getInstance();
	
	public String insertBook(Book book) {
		return dao.insertBook(book);
	}

	public ArrayList<Book> selectListAllBook() {
		return dao.selectListAllBook();
	}

	public Book selectOneBook(int bookNum) {
		return dao.selectOneBook(bookNum);
	}

	public String updateStockByBookNum(int bookNum, int stock) {
		return dao.updateStockByBookNum(bookNum, stock);
	}
	
	public ArrayList<Integer> selectStockByBookNum(int bookNum) {
		return dao.selectStockByBookNum(bookNum);
	}

	public String deleteBookByBookNum(int bookNum) {
		return dao.deleteBookByBookNum(bookNum);
	}

	public ArrayList<Book> selectListAllBookByUser() {
		return dao.selectListAllBookByUser();
	}

	public Book selectOneBookByUser(int bookNum) {
		return dao.selectOneBookByUser(bookNum);
	}

	
}
