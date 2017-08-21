package work.model.dto;

/**
 * <pre> 
 * 회원 도메인 클래스 모델링
 * 
 * ## 자바 적용 기술
 * -- 클래스 선언, 멤버변수, 메서드, 생성자
 * -- 데이터타입  : 기본형, 객체형
 * -- Encapsulation : 은닉성(데이터, 알고리즘  information hiding) - private
 * -- 접근권한 (Access modifier) : public, protected, default(friendly), private
 * -- DAO =>  직렬화 객체
 * 
 * ## 회원 property(멤버변수)
 * 1.아이디 	문자열 : memberId
 * 2.비밀번호	문자열 : memberPw
 * 3.이름		문자열 : memberName
 * 4.연락처	문자열 (기본형식 : 010-1234-1234) : mobile
 * 5.이메일	문자열 : email
 * 6.가입일	문자열 (기본형식 : 년도4자리, 월2자리, 일2자리) : entryDate
 * 7.등급		문자열	(회원종류 : 일반(G), 우수(S), 관리자(M)) : grade
 * 8.마일리지	숫자	: 일반회원 : mileage
 * 9.담당자	문자열 : 우수회원 : manager
 * </pre>
 * 
 * @author 황의성
 *@version ver 1.0
 *@since jdk1.8
 */
 
/**
 * ##자바 소스 코드 작성
 * 
 * 첫줄 : 패키지 선언문 :
 * 	-형식:package top.sub;
 * 	-위치:소스코드 첫번째 수행문
 * 
 * import 선언문 :
 *  -선언회수 : 0 ~ n
 *  -위치 : package 선언문 바로 뒤에
 *  -형식	  :
 *   => import.java.util.ArrayList (권장-무엇을 쓰는지 확인가능)
 *   => import.java.util.*
 * 	 => 다른 패키지에 같은  클래스이름 사용 시 전체 패키지명 포함 클래스이름 지정
 * 
 * 	-생략 가능 패키지 : java.lang, same package
 *  -다중 import 시 선언 순서
 *   =>java 기본패키지
 *   =>javax 패키지
 *   =>open source[frame work] 패키지
 *   =>사용자 패키지
 * 	
 * class 선언문
 * 	-선언회수 : 1 ~ n
 *   =>[비권장], (개발자가 간단하게 테스트 할 시 사용)
 * 	 =>[권장]
 *  -위치 : import 선언문 뒤에
 *  -구성요소 및 선언순서 :
 *   => 멤버변수
 *   => 생성자 : 기본, 필수, 전체
 *   => 메서드
 *  -형식 : public class 클래스이름 
 *  		[extends 부모클래스명]
 *  		[implements 부모인터페이스]{
 *  
 *  	멤버변수, 생성자, 메서드
 *  }	 
 *  
 * ## 멤버변수 선언방법
 * -[접근제한자][사용제한자][타입 변수명];
 * -[접근제한자][사용제한자][타입 변수명]=명시적초기화;
 * 
 * ##제한자(modifier)
 * 1.접근제한자 (access modifier)
 * 	-[+]public    : universe
 * 	-[#]protected : sub class[상속] 에서만  
 * 	-디폴트(생략)  : same package
 *  -[-]private   : same class
 *  
 *  -클래스 		  : public, 생략[default]
 *  -멤버변수		  : public, protected, 생략(default), private
 *  -지역변수[매개변수] : 생략(default)
 *  -메서드		  : public, protected, 생략(default), private
 *  -생성자		  : public, protected, 생략(default), private
 *  
 * 2.사용제한자[usage modifier]
 * 	-static, final, abstract
 *
 *
 * ##객체지향 언어의 특징 
 * 1.abstraction  [추상성]
 *   -객체의 주요특징 추출해서 클래스 설계과정
 * 2.encapsulation[은닉성]
 *   -Information hiding
 *   -데이터, 알고리즘[프로세스로직]
 * 3.inheritance  [상속]
 * 4.polymorphism [다형성]
 * 
 * ## encapsulation[은닉성]설게 규칙
 * - 도메인클래스[dto]
 * - 1. priavte 멤버변수 선언
 * - 2. public getter, setter
 * - 3. 
 * 
 * ## 생성자[constructor]
 * -역할 : 1.멤버변수 초기화, 2. 선행처리 로직
 * -호출시점 : 객체생성시에 호출(new)
 * -모든 클래스는 최소 1개이상의 생성자 존재
 * -개발자가 명시적으로 생성자를 선언하지 않으면 javax[컴파일 시점에서 자동 생성]
 * -생성자는 중복정의 가능 : 이름동일, 아규먼트 다름[갯수,순서,타입]
 * -기본생성자형식 : public 클래스이름(){}
 * 
 * ## this 참조변수
 * -객체생성시에 자동으로 제공하는 참조변수
 * -현재객체를 지칭 : this
 * -멤버변수 지칭 : this.멤버변수명 => 지역변수명과 동일변수명 사용가능
 * -현재객체의 다른생성자 호출 : this(params) => 중복코드 제거, 공통로직 수행(for 유지보수)
 * 
 * ## java api:문자열 관련
 * java.lang.String
 * java.lang.StringBuffer
 * java.lang.StringBuilder
 * java.util.StringTokenizer
 * 
 * ## 자바 데이터타입 기본값
 * 정수형 : 0
 * 실수형 : 0.0
 * 논리형 : false
 * 단일문자 : 공백문자("\u0000")
 * 객채형 : null
 * 
 * ## null 의미 : 어떠한 객체도 참조하지 않고있음;
 */

/*실습 1 : Member 클래스 작성
 * -- encapsulatiuon
 * -- 직렬화객체
 * -- 생성자 중복정의
 * -- => 기본생성자
 * 	  => 전체데이터 초기화생성자
 *    => 필수데이터 초기생성자 ()*/

public class Member {

	/** 회원아이디 정보*/
	private String memberId = "guest";
	/** 회원비밀번호 정보*/
	private String memberPw;
	/** 회원이름 정보*/
	private String memberName;
	/** 회원 휴대폰  정보 (형식 : 123-1234-1234) */
	private String mobile;
	/** 회원 이메일 정보*/
	private String email;
	/** 회원 가입일 정보  (형식 : 1234-12-12*/
	private String entryDate;
	/** 회원 등급 정보*/
	private String grade;
	/** 회원 마일리지 정보*/
	private int mileage = 0;
	/** 회원(우수) 담당자 정보*/
	private String manager = "";
	
	
	//상속시, 디폴트로 자식 클래스는 super로 부모의 기본생성자를 실행한다. 명시해주지 않으면 오류가 날 수 있다. 
	//(그러므로, 생성자 오버로딩했을 시는, 왠만하면 기본생성자도 같이 써주자);
	//기본 생성자
	public Member () {}
	
	/**
	 * 필수 데이터 초기화 생성자
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param memberName 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 */
	 //필수 생성자
	public Member (String memberId, String memberPw, String memberName, String mobile, String email) {
		
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.mobile = mobile;
		this.email = email;
	}
	
	/**
	 * 전체 데이터 초기화 생성자
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param memberName 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param entryDate 가입일
	 * @param mileage 마일리지
	 * @param manager 담당자
	 */
	//전제 생성자
	public Member (String memberId, String memberPw, String memberName, String mobile, String email, String entryDate,
			String  grade, int mileage, String manager) {
		
		this(memberId, memberPw, memberName, mobile, email);
		
		this.entryDate = entryDate;
		this.grade = grade;
		this.mileage = mileage;
		this.manager = manager;
	}

	//getter, stter
	public String getMemberId () {
		
		return this.memberId;
	}
	
	public void setMemberId (String memberId) {
		
		this.memberId = memberId;
	}
	
	/**
	 * 아이디 검증 메서드
	 * 검증규칙 : 아이디는 최소6자리 ~ 20자리 이내
	 * 
	 * @see java.lang.String#length()
	 * @param memberId
	 * @return
	 */
	public boolean isMemberId (String memberId) {
		
		if (memberId != null) {
		
			int length = memberId.length();
			if (length >= 0 && length <= 20) {
				
				this.memberId = memberId;
				return true;
			} else {
			
				System.out.println("길이를 정확히 지켜주세요.");
				return false;
			}
		} else {
			
			return false;
		}
	}
	
	public String getMemberPw () {
		
		return this.memberPw;
	}
	
	public void setMemberPw (String memberPw) {
		
		this.memberPw = memberPw;
	}
	
	public String getMemberName () {
		
		return this.memberName;
	}
	
	public void setMemberName (String memberName) {
		
		this.memberName = memberName;
	}
	
	public String getMobile () {
		
		return this.mobile;
	}
	
	public void setMobile (String mobile) {
		
		this.mobile = mobile;
	}
	
	public String getEmail () {
		
		return this.email;
	}
	
	public void setEmail (String email) {
		
		this.email = email;
	}
	
	public String getEntryDate () {
		
		return this.entryDate;
	}
	
	public void setEntryDate (String entryDate) {
		
		this.entryDate = entryDate;
	}
	
	public String getGrade () {
		
		return this.grade;
	}
	
	public void setGrade (String grade) {
		
		this.grade = grade;
	}
	
	public int getMileage () {
		
		return this.mileage;
	}
	
	public void setMileage (int mileage) {
		
		this.mileage = mileage;
	}	
	
	public String getManager () {
		
		return this.manager;
	}
	
	public void setManager (String manager) {
		
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("memberId=");
		builder.append(memberId);
		builder.append(", memberPw=");
		builder.append(memberPw);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append(", entryDate=");
		builder.append(entryDate);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", mileage=");
		builder.append(mileage);
		builder.append(", manager=");
		builder.append(manager);
		builder.append("]");
		return builder.toString();
	}
}
