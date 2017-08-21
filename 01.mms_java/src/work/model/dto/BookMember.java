package work.model.dto;

public class BookMember {

	/** 회원아이디 정보*/
	private String userId = "guest";
	/** 회원이름 정보*/
	private String userName;
	/** 회원비밀번호 정보*/
	private String userPw;
	/** 회원 휴대폰  정보 (형식 : 010-0000-0000) */
	private String mobile;
	/** 회원 이메일 정보 (형식 email + @ eamil.com) */
	private String email;
	/** 회원 등급 정보*/
	private String grade;
	/** 계좌잔고 정보 (형식 : 100,000) */
	private int bankBalance;
	
	
	//상속시, 디폴트로 자식 클래스는 super로 부모의 기본생성자를 실행한다. 명시해주지 않으면 오류가 날 수 있다. 
	//(그러므로, 생성자 오버로딩했을 시는, 왠만하면 기본생성자도 같이 써주자);
	//기본 생성자
	public BookMember () {}
	
	/**
	 * 필수 데이터 초기화 생성자
	 * @param userId 아이디
	 * @param userName 이름
	 * @param userPw 비밀번호
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param grade 등급
	 * @param bankBalance 계좌잔고
	 */
	 //필수 생성자
	public BookMember (String userId, String userName, String userPw, String mobile,
			String email, String grade, int bankBalance) {
		
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.mobile = mobile;
		this.email = email;
		this.grade = grade;
		this.bankBalance = bankBalance;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPw
	 */
	public String getUserPw() {
		return userPw;
	}

	/**
	 * @param userPw the userPw to set
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the bankBalance
	 */
	public int getBankBalance() {
		return bankBalance;
	}

	/**
	 * @param bankBalance the bankBalance to set
	 */
	public void setBankBalance(int bankBalance) {
		this.bankBalance = bankBalance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookMember [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userPw=");
		builder.append(userPw);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", bankBalance=");
		builder.append(bankBalance);
		builder.append("]");
		return builder.toString();
	}
}
