package work.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import work.model.dao.MemberDao;
import work.model.dto.Member;

/**
 * 비즈니스 로직, 회원관리
 * 회원가입 서비스 : 회원이 입력한 정보를 데이터베이스에 저장하는 작업을 하는 곳.
 * @author User
 * 
 */
public class MemberService {

	private MemberDao dao = MemberDao.getInstance();

	/**
	 * 회원등록 서비스
	 * -- 가입일 	: 현재날짜
	 * -- 등급 	: G
	 * -- 마일리지 	: 1000 
	 * @param dto 회원객체 (아이디, 암호, 이름, 연락처, 이메일)
	 * @return 
	 */
	public String addMember (Member dto) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		dto.setEntryDate(format.format(new java.util.Date()));
		dto.setGrade("G");
		dto.setMileage(1000);
		
		return null;
	}
	
	/**
	 * 아이디 중복확인
	 * @param memberId
	 * @return 아이디가 있으면 false, 아이디가 없으면 true
	 */
	public int isExist (String memberId) {
		return dao.isExist(memberId);
	}
	
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPw
	 * @return id와 pw가 존재하면 성공, 아니면 실패
	 */
	public String logIn (String memberId, String memberPw) {
		return dao.login(memberId, memberPw);
	}
	
	/**
	 * 내 정보 조회
	 * @param memberId 아이디
	 * @return 회원 정보 도메인 객체 
	 */
	public Member myInfo (String memberId) {
		return dao.myInfo(memberId);
	}
	
	/**
	 * 내 정보 변경
	 * @param memberId 아이디
	 * @return 메시지
	 */
	public String updateMemberInfos (Member member) {
		return dao.updateMemberInfos(member);
	}
	
	/**
	 * 비밀번호 변경
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 새 비밀번호
	 * @return
	 */
	public String updateOnePassword(String memberId, String memberPw, String modifyMemberPw) {
		return dao.updateOnePassword(memberId, modifyMemberPw);
	}
	
	/**
	 * 회원탈퇴
	 * @param memberId 아이디
	 * @return 결과 메시지
	 */
	public String deleteOneMember (String memberId) {
		return dao.deleteOneMember(memberId);
	}
	
	/**
	 * 아이디 찾기
	 * @param mobile 연락처
	 * @param email 이메일
	 * @return 아이디
	 */
	public String selectOneMemberId (String mobile, String email) {
		return dao.selectOneMemberId (mobile, email);
	}
	
	/**
	 * 비밀번호 찾기
	 * @param mobile
	 * @param email
	 * @return 비밀번호
	 */
	public String selectOneMemberPw (String mobile, String email) {
		return dao.selectOneMemberPw(mobile, email);
	}
	
	/**
	 * 마일리지 업데이트
	 * @param memberId
	 * @param mileage
	 */
	public String updateOneMileage (String memberId, int mileage) {
		return dao.updateOneMileage(memberId, mileage);
	}
	
	/**
	 * 회원등급변경 
	 * @param memberId 아이디
	 * @param grade 등급
	 * @return 메시지
	 */
	public String updateOneGrade (String memberId, String grade) {
		return dao.updateOneGrade(memberId, grade);
	}
	
	
	/**
	 * 매니저 전체 회원 조회
	 * @param memberId 아이디
	 * @return 전체 회원 정보
	 */
	public ArrayList<Member> selectListMemberById (String memberId) {
		return dao.selectListMemberById(memberId);
	}
	
	//
	/**
	 * 매니저 회원정보변경
	 * @param member 변경할 회원정보
	 * @return 메시지
	 */
	public String mUpdateListMemberInfos (Member member) {
		return dao.mUpdateListMemberInfos(member);
	}
}
