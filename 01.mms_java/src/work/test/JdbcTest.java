package work.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.Member;
import work.util.MemberUtil;

/**
 * ## JDBC 프로그래밍 절차 : 아까 정리한 것 참고해서 본인이 스스로 정리먼저하세요 ^^ 
 * @author User
 *
 * 1. 드라이버 로딩 : 생성자
 * 2. db서버 연결 : 메서드
 * 3. sql 통로 개설
 * 4. sql 실행
 * 5. 결과처리
 * 6. 자원 해제 : close(rs, stmt, conn), close(stmt, conn)
 *
 * ## JDBC Property : 환결성정
 * 1. driver = ""
 * 2. url = ""
 * 3. password = ""
 *
 * ## JDBC Drivet 위치
 * -- Oracle : ojdbc.jar = > jdk0.0 jdbc 구현 driver
 * 1. 컴퓨터시스템(공통) 사용 : jdk\jre\lib\ext> 폴더에 복사
 * 
 * 2. 프로젝트 단위 사용 : 별도의 classpath 추가 설정
 * 
 * ## javac / java 사용한 클래스를 찾아가는 검색 경로 : classpath
 * 찾는 순서 
 * 1. rt.jar : Java SE (표준 API)
 * 2. jdk\jre\lib\ext> 폴더에 있는 *.jar
 * 3. set classpath=환결설정폴더 지정된 파일
 * 4. classpath 환경변수 미설정시에는 현재 폴더 (working directory)
 * 
 * ## Statement
 * 장점
 * - 동적 SQL 수행 통로
 * 단점
 * - 실행시점 SQL구문에 오류 발생
 * - SQL 문자열 변환
 * - N/W traffic 발생 : sql 구문, data가 함께 전송
 * - SQL injection 발생 위험 
 * 
 * ## PreparedStatement
 * 장점 
 * - 정적 SQL 전용 통로
 * - N/W traffic 감소 : DATA만 전송
 * 
 * ## CallableStatement
 * -DB 서버에 지참된 Stored Procedure, Stored Function 호출 사용
 * -
 */

class MemberDao {
	private String Driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private String user = "system";
	private String password = "root";
	
	public MemberDao () {
		
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
 			
			e.printStackTrace(); //개발시 디버깅 목적으로 사용 => logg4j
		}
	}
	
	public Connection getConnection () {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {}
		
		return conn;
	}
	
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		
		try {
			if (rs != null) {rs.close();};
			if (stmt != null) {stmt.close();};
			if (conn != null) {conn.close();};
		} catch (SQLException e) {}
	}
	
	public void close(Statement stmt, Connection conn) {
		
		close(null, stmt, conn);
	}
	
	public String login (String memberId, String memberPw) {
		
		String sql = "SELECT grade FROM MEMBERS WHERE MEMBER_ID = '"+memberId+"' AND MEMBER_PW = '"+memberPw+"'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("grade");
			}
			
		} catch (SQLException e) {
			
		} finally {
			
			close(rs, stmt, conn);
		}
		
		return null;
	}
	
	//회원 등록
	public int insertMember(Member member) {
		
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();
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
			
			close(pstmt, conn);
		}
		
		return 0;
	}
	
	//아이디 중복 확인
	public int isExist (Member member) {
		
		
		String sql = "SELECT COUNT(*) FROM MEMBERS WHERE MEMBER_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt(1);
				
			} else {
				
				System.out.println("아이디가 없습니다.");
			}
			
		} catch (SQLException e) {}
		
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
			conn = getConnection();
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
		
		return list;
	}
	
	//내 정보 변경
	public String updateMemberInfos (Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_PW = ?, MEMBER_NAME = ?, MOBILE = ?, EMAIL = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = getConnection();
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
			
			conn = getConnection();
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
		}
		
		return null;
	}
	
	//회원 탈퇴
	public String deleteOneMember (String memberId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "	DELETE FROM MEMBERS WHERE MEMBER_ID = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) 
				return "회원이 아니시거나, 이미 탈퇴되셨습니다.";
			else
				return result+"행이 삭제되었습니다.";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	} 
	
	//아이디찾기
	public String selectOneMemberId (String mobile, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MEMBER_ID FROM MEMBERS WHERE MOBILE = ? AND EMAIL = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				
				return rs.getString("member_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//비밀번호찾기
	public String selectOneMemberPw (String mobile, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "	SELECT MEMBER_PW FROM MEMBERS WHERE MOBILE = ? AND EMAIL = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("member_pw");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//마일리지 적립
	public String updateOneMileage (String memberId, int mileage) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MILEAGE = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = getConnection();
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
		}
		
		return null;
	}
	
	//회원등급변경
	public String updateOneGrade (String memberId, String grade) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "	UPDATE MEMBERS SET GRADE = ? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = getConnection();
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
			
			conn = getConnection();
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
		}
		
		return list;
	}
	
	//매니저 회원정보변경
	public String mUpdateListMemberInfos (Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_PW = ?, MEMBER_NAME = ?, MOBILE = ?, EMAIL = ?, ENTRY_DATE = ?, GRADE = ?, MILEAGE = ?, MANAGER =? WHERE MEMBER_ID = ?";
		
		try {
			
			conn = getConnection();
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
		}
		
		return null;
	}
	
}

public class JdbcTest {

	public static void main(String[] args) {

		MemberDao dao = new MemberDao();
		
		System.out.println("회원등록");
		Member mem = new Member("user06", "password06", "황의성", "", "", MemberUtil.getCurrentDate(), "G", 1000, null);
		int result = dao.insertMember(mem);
		System.out.println(result);
		
		System.out.println("아이디 중복 확인");
		int result2 = dao.isExist(mem);
		System.out.println(result2);
		
		System.out.println("로그인");
		
		String grade = dao.login("user01", "password01");
		System.out.println(grade);
		
		System.out.println("내 정보 조회");
		ArrayList<Member> result3 = dao.selectListMemberById("user01");
		System.out.println(result3);
		
		System.out.println("다중조건검색");
		ArrayList<Member> resultx = dao.selectListMemberByMulti("G", "user01", "%1111%");
		System.out.println(resultx);
		
		System.out.println("내 정보 변경");
		String result4 = dao.updateMemberInfos(mem);
		System.out.println(result4);
		
		System.out.println("비밀번호 변경");
		String result5 = dao.updateOnePassword("user06", "password06");
		System.out.println(result5);
		
	}	
}
