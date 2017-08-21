package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.Member;

public class MemberDao {

	private static MemberDao instance = new MemberDao();
	private FactoryDao fDao = FactoryDao.getInstance();
	
	private MemberDao () {
		
	}
	
	public static MemberDao getInstance () {
		
		return instance;
	}
	
	public String login (String memberId, String memberPw) {
		
		String sql = "SELECT grade FROM MEMBERS WHERE MEMBER_ID = '"+memberId+"' AND MEMBER_PW = '"+memberPw+"'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = fDao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("grade");
			}
			
		} catch (SQLException e) {
			
		} finally {
			
			fDao.close(rs, stmt, conn);
		}
		
		return null;
	}
	
	//회원 등록
	public int insertMember(Member member) {
		
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMobile());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getEntryDate());
			pstmt.setString(7, member.getGrade());
			pstmt.setInt(8, member.getMileage());
			pstmt.setString(9, member.getManager());
			
			return pstmt.executeUpdate();
					
		} catch (SQLException e) {
			
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return 0;
	}
	
	//아이디 중복 확인
	public int isExist (String memberId) {
		
		
		String sql = "SELECT COUNT(*) FROM MEMBERS WHERE MEMBER_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt(1);
				
			} else {
				
				System.out.println("아이디가 없습니다.");
			}
			
		} catch (SQLException e) {}
		
		finally {
			
			fDao.close(rs,pstmt,conn);
		}
		
		return 0;
	}
	
	//다중조건 검색
	public ArrayList<Member> selectListMemberByMulti (String grade, String memberId, String mobile) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from members");
		ArrayList<Member> list = new ArrayList<Member>();
		
		
		if (grade != null || memberId != null || mobile != null) {
			sql.append(" where ");
			
			if(grade != null) sql.append("grade = ? ");
			if(memberId != null) sql.append("AND member_id = ? ");
			if(mobile != null) sql.append("AND mobile like ?");
		}
		
		try {
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			int num = 1;
			if(grade != null)
			{pstmt.setString(num, grade); num++;}
			
			if(memberId != null)
			{pstmt.setString(num, memberId); num++;}
			
			if(mobile != null)
			{pstmt.setString(num, mobile); num++;};

			Member mem = null;
			
			rs = pstmt.executeQuery();
			
			int num2 = 0;
			while(rs.next()) {
				
				mem = new Member();
				mem.setMemberId(rs.getString("member_id"));
				mem.setMemberPw(rs.getString("member_pw"));
				mem.setMemberName(rs.getString("member_name"));
				mem.setMobile(rs.getString("mobile"));
				mem.setEmail(rs.getString("email"));
				mem.setEntryDate(rs.getString("entry_date"));
				mem.setGrade(rs.getString("grade"));
				mem.setMileage(rs.getInt("mileage"));
				mem.setManager(rs.getString("manager"));
				
				list.add(mem);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//내 정보 조회
	public Member myInfo (String memberId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where member_id = ?";
		Member member = null;
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_Name"));
				member.setMobile(rs.getString("mobile"));
				member.setEmail(rs.getString("email"));
				member.setEntryDate(rs.getString("entry_date"));
				member.setGrade(rs.getString("grade"));
				member.setMileage(rs.getInt("mileage"));
				member.setManager(rs.getString("manager"));
				
				return member;
			}
			
		} catch (SQLException e) {
			
		}finally {
			
			fDao.close(rs, pstmt, conn);
		}
		return null;
	}
	
	//내 정보 변경
	public String updateMemberInfos (Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_PW = ?, MEMBER_NAME = ?, MOBILE = ?, EMAIL = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMobile());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getManager());
			pstmt.setString(6, member.getMemberId());
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "업데이트 오류입니다.";
			
			else
				return result+"행 변경되었습니다.";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//비밀번호 변경
	public String updateOnePassword (String memberId, String memberPw) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_PW = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "업데이트 오류입니다.";
			
			else
				return result+"행 변경되었습니다.";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return null;
	}
	
	//회원 탈퇴
	public String deleteOneMember (String memberId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "	DELETE FROM MEMBERS WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "회원이 아니시거나, 이미 탈퇴되셨습니다.";
			else
				return result+"행이 삭제되었습니다.";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return null;
	} 
	
	/**
	 * 아이디 찾기
	 * @param mobile
	 * @param email
	 * @return 아이디
	 */
	public String selectOneMemberId (String mobile, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MEMBER_ID FROM MEMBERS WHERE MOBILE = ? AND EMAIL = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				
				return rs.getString("member_id");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return null;
	}
	
	/**
	 * 비밀번호 찾기
	 * @param mobile
	 * @param email
	 * @return 비밀번호
	 */
	public String selectOneMemberPw (String mobile, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "	SELECT MEMBER_PW FROM MEMBERS WHERE MOBILE = ? AND EMAIL = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("member_pw");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return null;
	}
	
	/**
	 * 마일리지 업데이트
	 * @param memberId
	 * @param mileage
	 */
	public String updateOneMileage (String memberId, int mileage) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MILEAGE = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, mileage);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "업데이트 오류입니다.";
			
			else
				return result+"행 변경되었습니다.";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return null;
	}
	
	//회원등급변경
	public String updateOneGrade (String memberId, String grade) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "	UPDATE MEMBERS SET GRADE = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, grade);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "업데이트 오류입니다.";
			
			else
				return result+"행 변경되었습니다.";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return null;
	}
	
	//매니저 전체 회원 조회
	public ArrayList<Member> selectListMemberById (String memberId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBERS WHERE MEMBER_ID = ?";
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			Member member = null;
			
			while (rs.next()) {
				
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberName(rs.getString("member_Name"));
				member.setMobile(rs.getString("mobile"));
				member.setEmail(rs.getString("email"));
				member.setEntryDate(rs.getString("entry_date"));
				member.setGrade(rs.getString("grade"));
				member.setMileage(rs.getInt("mileage"));
				member.setManager(rs.getString("manager"));
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//매니저 회원정보변경
	public String mUpdateListMemberInfos (Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_PW = ?, MEMBER_NAME = ?, MOBILE = ?, EMAIL = ?, ENTRY_DATE = ?, GRADE = ?, MILEAGE = ?, MANAGER =? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = fDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMobile());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getEntryDate());
			pstmt.setString(6, member.getGrade());
			pstmt.setInt(7, member.getMileage());
			pstmt.setString(8, member.getManager());
			pstmt.setString(9, member.getMemberId());
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "업데이트 오류입니다.";
			
			else
				return result+"행 변경되었습니다.";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			fDao.close(pstmt, conn);
		}
		
		return null;
	}
}
