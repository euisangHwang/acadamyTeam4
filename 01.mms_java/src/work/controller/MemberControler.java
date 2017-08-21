package work.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import work.model.dto.Member;
import work.model.service.MemberService;
import work.util.MemberUtil;

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
public class MemberControler {
	
	private MemberService service = new MemberService(); 
	
	/**
	 * 회원가입, 데이터 검증
	 * --필수 항목 : 아이디, 비밀번호, 이름, 연락처, 이메일
	 * -- 아이디 : 6~20자리 길이제한
	 * -- 비밀번호 8~20자리 길이제한
	 * 
	 * 1. Member객체  null 체크
	 * 2. 필수 데이터 추출 및 null체크
	 * 3. trim후 필수 데이터 공백 체크
	 * 4. 길이 제한  
	 * 
	 * @param dto 회원객체
	 * @return 결과 메시지
	 * 
	 */
	public ArrayList<String> addMember (Member dto) {
		
		ArrayList<String> message = new ArrayList<String>();
		
		if(dto != null) {
			
			ArrayList<String> list = new ArrayList<String>();
			list.add(dto.getMemberId());
			list.add(dto.getMemberPw());
			list.add(dto.getMemberName());
			list.add(dto.getMobile());
			list.add(dto.getEmail());
			
			//null체크
			for(int i=0; i<list.size(); i++) {
				
				String me = "";
				
				if((me = isValid(list.get(i))) != "") {
					
					message.add(me);
				} else {
					
					if(i == 0) {
					
						if(me.trim().length() < 6 || me.trim().length() > 20) {
							
							message.add("아이디를 올바른 길이로 작성하세요.");
						} 
					} else if (i == 1) {
						
						if(me.trim().length() < 6 || me.trim().length() > 20) {
							
							message.add("비밀번호를 올바른 길이로 작성하세요.");
						} 
					} else {};
				}
			}
		} else 
			message.add("회원가입 정보가 없습니다.");
		
		//로그인 페이지로 이동
		
		if(!message.isEmpty()) {
			
			return message;
		} else {
			
		}
		
		return message;
	};
	
	public String isValid (String param) {
		
		String result = "";
		
		if(param == null) {
			
			result = param+"(은)가 없습니다.";
		} else {
			
		}
		
		return result;
	}

	/**
	 * 아이디 중복확인
	 * @param memberId
	 * @return 아이디가 있으면 false, 아이디가 없으면 true
	 */
	public boolean isExist (String memberId) {
		int result = service.isExist(memberId);
		
		if(result == 0) return false;
		else return true;
	}
	
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPw
	 * @return id와 pw가 존재하면 
	 */
	public String login (String memberId, String memberPw) {
		
		if(memberId != null && memberPw != null) {
			
			String grade = service.logIn(memberId, memberPw);
			
			if(grade != null) {
				
				return grade;
			} else {
				return null;
			}
		} else {
			
			return null;
		}
		//로그인 페이지로 이동
		//마일리지 누적 메서드
		//회원 등급 등업 및 담당자 설정
	}
	
	/**
	 * 로그아웃
	 */
	public void logOut () {
		
		
		//홈으로 이동
	}
	
	/*+ myInfo(memberId:String) : Member*/
	/**
	 * 내 정보 조회
	 * @param memberId 아이디
	 * @return 회원 정보 도메인 객체 
	 */
	public Member myInfo (String memberId) {
		
		return null;
	}
	
	/**
	 * 내 정보 변경
	 * @param memberId 아이디
	 * @return 
	 */
	public String updateMemberInfos (Member member) {
		
		
		//로그인 페이지로 이동
		return null;
	}
	
	/**
	 * 비밀번호 변경
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 새 비밀번호
	 * @return
	 */
	public String updateOnePassword(String memberId, String memberPw, String modifyMemberPw) {
		
		return null;
	}
	
	/**
	 * 회원탈퇴
	 * @param memberId 아이디
	 * @return 결과 메시지
	 */
	public String deleteOneMember (String memberId) {
		
		//회원 탈퇴
		return null;
	}
	
	/**
	 * 아이디 찾기
	 * @param mobile 연락처
	 * @param email 이메일
	 * @return 아이디
	 */
	public String selectOneMemberId(String mobile, String email) {
		
		if (mobile != null && email != null) {
			
			String id = service.selectOneMemberId(mobile, email);
			
			if(id != null) {
				
				MemberUtil util = new MemberUtil(); 
				int secureNum = util.createSecureNumber(10);
				
				return secureNum+id;
			}
		}
		
		return null;
	} 

	/**
	 * 비밀번호 찾기
	 * @param mobile 연락처
	 * @param email 이메일
	 * @return 보안문자
	 */
	public String selectOneMemberPw (String mobile, String email) {
		
		//보안문자 비교
		
		return null;
	}
	
	/**
	 * 마일리지 업데이트
	 * @param memberId
	 * @param mileage
	 */
	public void updateOneMileage (String memberId, int mileage) {
		
	}

	/**
	 * 마일리지 업데이트
	 * @param memberId 아이디
	 */
	public void gradeUpdate (String memberId) {
		
	}
	
	/**
	 * 전체회원 정보 조회
	 * @return
	 */
	public ArrayList<Member> selectListMemberById () {
		
		return null;
	}
	
	/**
	 * 관리자용 유저 다중조건검색
	 * @param dto
	 * @return
	 */
	public ArrayList<Member> selectUserInfo (Member dto) {
		
		return null;
	}
	
	/**
	 * 관리자 회원정보 변경
	 * @param memberId
	 * @return 결과 메시지
	 */
	public String mUpdateListMemberInfos (String memberId)  {
		
		return null;
	}

	public void mileageUpdate (String memberId) {
		
		
	} 
	
	public void levelUp (String memberId) {
		
		//등업 및 
	}
	
	public void changeManager (String memberId) {
		
		//담당자 배정
	}
	
	public Map<String, Object> selectSessionInfo () {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	/**
	 * 회원등급변경 
	 * @param memberId 아이디
	 * @param grade 등급
	 * @return 메시지
	 */
	public String updateOneGrade (String memberId, String grade) {
		
		return null;
	}
	
	
	
}
