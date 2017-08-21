package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import work.model.dto.BookMember;
import work.util.BookUtil;

public class BookMemberDao {

	private static BookMemberDao instance = new BookMemberDao();
	private FactoryDao fDao = FactoryDao.getInstance();
	
	private BookMemberDao () {
		
	}
	
	public static BookMemberDao getInstance () {
		
		return instance;
	}

	public String insertBookMember(BookMember member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO USERS VALUES (?,?,?,?,?,?,?)";
		String result = "회원가입에 실패 했습니다.";
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserName());
			pstmt.setString(3, member.getUserPw());
			pstmt.setString(4, member.getMobile());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getGrade());
			pstmt.setInt(7, member.getBankBalance());
			
			if (pstmt.executeUpdate() != 0) {
				result = "회원가입에 성공했습니다.";
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public String updateOneUserPw(String userId, String userPw) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE USERS SET USER_PW = ? WHERE USER_ID = ?";
		String result = "비밀번호 변경에 실패 했습니다.";
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			if(pstmt.executeUpdate() != 0) {
				
				result = "비밀번호 가"+userPw+" 로 변경되었습니다.";
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public String deleteOneBookMember(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM USERS WHERE USER_ID = ?";
		String result = "계정 삭제에 실패 했습니다.";
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			if(pstmt.executeUpdate() != 0) {
				
				result = userId+" 계정이 삭제되었습니다..";
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public String updateOneUserBalance(String userId, int money) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE USERS SET BANK_BALANCE = ? WHERE USER_ID = ?";
		String result = "fail";
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, userId);
			
			if(pstmt.executeUpdate() != 0) {
				
				result = userId+"님 계좌잔고 : "+money;
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public int selectOneUserBalance(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT BANK_BALANCE FROM USERS WHERE USER_ID = ?";
		int result = 0;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("BANK_BALANCE");
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public int selectBookMoney(int bookNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT BOOK_PRICE FROM BOOKS WHERE BOOK_NUM = ?";
		int result = 0;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("BOOK_PRICE");
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
	}

	public String purchaseBook(int createSecureNumber, int bookNum,
			String userId, String currentDate, int stock, Integer integer) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO P_HISTORY VALUES (?,?,?,?,?,?)";
		String result = "구매에 실패했습니다.";
		
/*		ORDER_NUM   NOT NULL NUMBER       
		BOOK_NUM    NOT NULL NUMBER       
		USER_ID     NOT NULL VARCHAR2(20) 
		ORDER_DATE  NOT NULL VARCHAR2(20) 
		ORDER_STOCK NOT NULL NUMBER       
		ORDER_PRICE NOT NULL NUMBER      */ 
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, createSecureNumber);
			pstmt.setInt(2, bookNum);
			pstmt.setString(3, userId);
			pstmt.setString(4, currentDate);
			pstmt.setInt(5, stock);
			pstmt.setInt(6, integer);
			
			if (pstmt.executeUpdate() != 0) {
				result = "구매에 성공했습니다.";
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		return result;
		
		
	}
}
