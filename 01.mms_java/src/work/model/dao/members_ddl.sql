/* 회원테이블 스키마 파일 : members_ddl.sql */

	/**
	 * 전체 데이터 초기화 생성자
	 * @param memberId 아이디 		=> 	member_id 	varchar2(20) primary key
	 * @param memberPw 비밀번호		=> 	member_pw 	varchar2(16) not null
	 * @param memberName 이름		=>  member_name varchar2(15) not null
	 * @param mobile 휴대폰		=>  mobile		varchar2(13) 
	 * @param email 이메일			=>  email		varchar2(20) 
	 * @param entryDate 가입일		=>  entry_date  char(10)
	 * @param mileage 마일리지		=>  mileage		number
	 * @param manager 담당자		=>  manager		varchar2(15)
	 */

-- 테이블 생성

-- 제약 추가

-- 제약조건 이름  : 조건_테이블_컬럼명
-- NOT NULL은 칼럼 레벨에서만 가능
-- 실무에서는 제약조건을 (NOT NULL제외) ALTER로 테이블레벨로 부여하는 것이 일반적 (가독성 때문에)

--purge recyclebin; (쓰레기 파일 지우기 쿼리)