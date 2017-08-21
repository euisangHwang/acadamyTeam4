package work.view;

import java.util.ArrayList;

import work.controller.BookControler;
import work.controller.BookMemberControler;
import work.model.dto.Book;
import work.model.dto.BookMember;
import work.util.BookUtil;

public class BookTest {

	
	
	public static void main(String[] args) {

		BookControler controler = new BookControler();
		BookMemberControler memControler = new BookMemberControler();
		
		Book book = new Book(BookUtil.createSecureNumber(), "book01", "author01", 1, 10000,
				20, BookUtil.getCurrentDate(), "art");
		
		BookMember member = new BookMember("user01", "userName01", "pw01", "010-4666-8470", "eamil@eamil.com", "G", 100000); 
		BookMember manager = new BookMember("manager01", "userName01", "pw01", "010-4666-8470", "eamil@eamil.com", "G", 100000); 
		
		
		
		
		ArrayList<String> message1 = controler.insertBook(book);
		
		for (int i=0; i<message1.size(); i++) {
			
			System.out.println(message1.get(i));
		}
		
		ArrayList<Book> book2 = controler.selectListAllBook();
		System.out.println(book2);
		
		book =  controler.selectOneBook(0);
		System.out.println(book);
		
		String message = controler.updateStockByBookNum(0, 30);
		System.out.println(message);
		
/*		message = controler.deleteBookByBookNum(0);
		System.out.println(message);*/
		
		book2 = controler.selectListAllBookByUser(); 
		System.out.println(book2);
		
		book =  controler.selectOneBookByUser(0);
		System.out.println(book);
		
		message = memControler.insertBookMember(member);
		System.out.println(message);
		
		message = memControler.updateOneUserPw("user01", "pw01");
		System.out.println(message);
		
/*		message = memControler.deleteOneBookMember("user01");
		System.out.println(message);*/
		
		message = memControler.purchaseBook("user01", 0, 3);
		System.out.println(message);
		
	}
	
	
	

}
