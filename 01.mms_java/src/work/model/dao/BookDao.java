package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Book;

public class BookDao {

	private static BookDao instance = new BookDao();
	private FactoryDao fDao = FactoryDao.getInstance();
	
	private BookDao () {
		
	}
	
	public static BookDao getInstance () {
		
		return instance;
	}

	public String insertBook(Book book) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOOKS VALUES (?,?,?,?,?,?,?,?)";
		String result = "저장에 실패했습니다.";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, book.getBookNum());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getAuthorName());
			pstmt.setInt(4, book.getPublisherNum());
			pstmt.setInt(5, book.getBookPrice());
			pstmt.setInt(6, book.getStock());
			pstmt.setString(7, book.getPublishDate());
			pstmt.setString(8, book.getCategory());
			
			if(pstmt.executeUpdate() != 0) {
				
				result = "저장을 성공했습니다.";
			};
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(pstmt, conn);
		}
		
		return result;
	}

	public ArrayList<Book> selectListAllBook() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOOKS";
		ArrayList<Book> bookArray = new ArrayList<Book>();
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Book book = new Book();
				book.setBookNum(rs.getInt("BOOK_NUM"));
				book.setBookName(rs.getString("BOOK_NAME"));
				book.setAuthorName(rs.getString("AUTHOR_NAME"));
				book.setPublisherNum(rs.getInt("PUBLISHER_NUM"));
				book.setBookPrice(rs.getInt("BOOK_PRICE"));
				book.setStock(rs.getInt("STOCK"));
				book.setPublishDate(rs.getString("PUBLISH_DATE"));
				book.setCategory(rs.getString("CATEGORY"));
				
				bookArray.add(book);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return bookArray;
	}

	public Book selectOneBook(int bookNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOOKS";
		Book book= null;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				book = new Book();
				book.setBookNum(rs.getInt("BOOK_NUM"));
				book.setBookName(rs.getString("BOOK_NAME"));
				book.setAuthorName(rs.getString("AUTHOR_NAME"));
				book.setPublisherNum(rs.getInt("PUBLISHER_NUM"));
				book.setBookPrice(rs.getInt("BOOK_PRICE"));
				book.setStock(rs.getInt("STOCK"));
				book.setPublishDate(rs.getString("PUBLISH_DATE"));
				book.setCategory(rs.getString("CATEGORY"));
				
				return book;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return book;
	}

	public String updateStockByBookNum(int bookNum, int stock) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOOKS SET STOCK = ? WHERE BOOK_NUM = ?";
		String result = "fail";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock);
			pstmt.setInt(2, bookNum);
			
			int num = 0;
			if ((num = pstmt.executeUpdate()) != 0) {
				
				result = num+" 행이 변경되었습니다.";
			};
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(pstmt, conn);
		}
		
		return result;
	}

	public String deleteBookByBookNum(int bookNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOOKS WHERE BOOK_NUM = ?";
		String result = "도서 삭제에 실패했습니다.";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			
			if (pstmt.executeUpdate() != 0) {
				
				result = "해당 도서삭제를 성공했습니다.";
			};
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(pstmt, conn);
		}
		
		return result;
	}
	
	public ArrayList<Book> selectListAllBookByUser() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOOKS";
		ArrayList<Book> bookArray = new ArrayList<Book>();
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Book book = new Book();
				book.setBookNum(rs.getInt("BOOK_NUM"));
				book.setBookName(rs.getString("BOOK_NAME"));
				book.setAuthorName(rs.getString("AUTHOR_NAME"));
				book.setPublisherNum(rs.getInt("PUBLISHER_NUM"));
				book.setBookPrice(rs.getInt("BOOK_PRICE"));
				book.setPublishDate(rs.getString("PUBLISH_DATE"));
				book.setCategory(rs.getString("CATEGORY"));
				
				bookArray.add(book);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return bookArray;
	}

	public Book selectOneBookByUser(int bookNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOOKS";
		Book book= null;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				book = new Book();
				book.setBookNum(rs.getInt("BOOK_NUM"));
				book.setBookName(rs.getString("BOOK_NAME"));
				book.setAuthorName(rs.getString("AUTHOR_NAME"));
				book.setPublisherNum(rs.getInt("PUBLISHER_NUM"));
				book.setBookPrice(rs.getInt("BOOK_PRICE"));
				book.setPublishDate(rs.getString("PUBLISH_DATE"));
				book.setCategory(rs.getString("CATEGORY"));
				
				return book;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return book;
	}

	public ArrayList<Integer> selectStockByBookNum(int bookNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT STOCK, BOOK_PRICE FROM BOOKS WHERE BOOK_NUM = ?";
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result.add(rs.getInt("STOCK"));
				result.add(rs.getInt("BOOK_PRICE"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return result;
	}
}
