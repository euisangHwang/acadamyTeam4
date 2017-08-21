package work.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import work.model.dto.Book;
import work.model.service.BookService;

public class BookControler {

	private BookService service = new BookService();
	
	
	
	public ArrayList<String> insertBook (Book book) {
	
		ArrayList<String> message = new ArrayList<String>();
		
		if (book != null) {
			
			int bookNum = book.getBookNum();
			String bookName = book.getBookName();
			String authorName = book.getAuthorName();
			int publisherNum = book.getPublisherNum();
			int bookPrice = book.getBookPrice();
			int stock = book.getStock();
			String publishDate = book.getPublishDate();
			String category = book.getCategory();
			
			if (bookNum != 0) {
				
			} else {
				message.add("도서 번호가 없습니다.");
			}
			
			if (bookName == null) { 
				message.add("도서 이름이 없습니다.");
			}
			
			if (authorName == null || authorName.trim().length() == 0) {
				message.add("저자명을 잘못 입력하셨습니다.");
			}
			
			if (publisherNum == 0) {
				message.add("출판사번호가 없습니다.");
			}
			
			if (bookPrice == 0) {
				message.add("도서 가격이 없습니다.");
			}
			
			if (stock == 0) {
				message.add("재고 정보가 없습니다.");
			}
			
			if (publishDate == null) {
				message.add("출판 일시가 없습니다.");
			}
			
			if (category == null) {
				message.add("카테고리가 없습니다.");
			}
			
		} else {
			message.add("도서 정보를 입력해주세요");
		}
		
		if (!message.isEmpty()) {
			message.add(service.insertBook(book));
		}
		
		return message;
	}
	
	public String isValid (String value) {
		
		if(value != null) {
			
			return null;
		} else {
			
			return "값이 없습니다";
		}
	}
	public String isValid (int value) {
		
		if(value != 0) {
			
			return null;
		} else {
			
			return "값이 없습니다";
		}
	}

	public ArrayList<Book> selectListAllBook() {
		return service.selectListAllBook();
	}

	public Book selectOneBook(int bookNum) {
		return service.selectOneBook(bookNum);
	}

	public String updateStockByBookNum(int bookNum, int stock) {
		return service.updateStockByBookNum(bookNum, stock);
	}

	public String deleteBookByBookNum(int bookNum) {
		return service.deleteBookByBookNum(bookNum);
	}

	public ArrayList<Book> selectListAllBookByUser() {
		return service.selectListAllBookByUser();
	}

	public Book selectOneBookByUser(int bookNum) {
		return service.selectOneBookByUser(bookNum);
	}
	
}
