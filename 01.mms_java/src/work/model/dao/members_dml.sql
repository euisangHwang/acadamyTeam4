/* 회원테이블  CRUD 스키마 파일 : members_dml.sql*/

/* 
 * ## JDBC Patten Programming
 * 
 * 1.DTO Patten
 * 2.DAO Patten
 * 3.Singleton Patten
 * 4.Factory Patten
 * 
 * ## Singleton Patten
 * -하나의 클래스에 대해서 하나의 인스턴스(객체)설계
 * -DAO 클래스에 적용 설계
 * -규칙 : 
 * 	1. 생성자 private접근제한 => private 생성자
 * 	2. 인스턴스 리턴 메서드 작성 = > public static 클래스(반환타입) getInstance () {return instance}
 *  3. private static 클래스() instance = new 클래스() 
 * -클래스 사용
 * 	클래스이름 참조변수명 = 클래스이름.getInstance();
 * 
 * ## Factory Patten
 * -FactoryDao 클래스
 * -DAO 클래스들이 사용
 * -Connection 반환
 * -close() 자원해제
 * 
 * */

-- 회원 등록 		
INSERT INTO MEMBERS
VALUES ('user01', 'password01', '홍길동', '010-1234-1111',	'user01@work.com', '2017.05.05', 'G', 7500, null) 

INSERT INTO MEMBERS
VALUES ('user02', 'password02',	'강감찬', '010-1234-1112',	'user02@work.com', '2017.05.06', 'G', 9500, null) 

INSERT INTO MEMBERS
VALUES ('user03', 'password03', '이순신', '010-1234-1113',	'user03@work.com', '2017.05.07', 'G', 7500, null)

INSERT INTO MEMBERS
VALUES ('user04', 'password04', '김유신', '010-1234-1114',	'user04@work.com', '2017.05.08', 'S', null, '송중기') 

INSERT INTO MEMBERS
VALUES ('user05', 'password05', '유관순', '010-1234-1115',	'user05@work.com', '2017.05.09', 'A', null, null)
	
-- 아이디 중복 조회
SELECT COUNT(*) FROM MEMBERS WHERE MEMBER_ID = ?

-- 로그인
SELECT  FROM MEMBERS WHERE MEMBER_ID = ? AND MEMBER_PW = ?

-- 내정보 조회
SELECT * FROM MEMBERS WHERE MEMBER_ID = 'user01'

-- 내정보 변경
UPDATE MEMBERS
SET MEMBER_ID = ?,
	MEMBER_PW = ?,
	MEMBER_NAME = ?,
	MOBILE = ?,
	EMAIL = ?
	WHERE MEMBER_ID = ?

-- 비밀번호 변경 
UPDATE MEMBERS
SET MEMBER_PW = ?
WHERE MEMBER_ID = ?

-- 회원탈퇴
DELETE FROM MEMBERS
WHERE MEMBER_ID = ?

-- 아이디찾기
SELECT MEMBER_ID FROM MEMBERS
WHERE MOBILE = ? AND EMAIL = ?

-- 비밀번호찾기
SELECT MEMBER_PW FROM MEMBERS
WHERE MOBILE = ? AND EMAIL = ?

-- 마일리지 적립
UPDATE MEMBERS
SET MILEAGE = ?
WHERE MEMBER_ID = ?

-- 회원등급변경
UPDATE MEMBERS
SET GRADE = ?
WHERE MEMBER_ID = ?

-- 전체회원조회
SELECT * FROM MEMBERS

-- 다중조건검색 조회
SELECT * FROM MEMBERS
WHERE GRADE = ? AND
	  MEMBER_ID = ? AND
	  MOBILE LIKE ? AND
	  
-- 등급별
-- 아이디
-- 휴대폰번호뒷4자리
-- 이름
-- 기타"

-- 매니저 회원정보변경
-- =>> 값을 검사해서, (StringBuffer)쿼리문에   AND 파라미터  = ? 를 각각 추가
--  

UPDATE MEMBERS
SET MEMBER_ID = ?,
	MEMBER_PW = ?,
	MEMBER_NAME = ?,
	MOBILE = ?,
	EMAIL = ?,
	ENTRY_DATE = ?,
	GRADE = ?,
	MILEAGE = ?,
	MANAGER =?





--