package work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class BookUtil {

	/**
	 * 보안문자 발급
	 * @param secureLength 보안문자 자릿수
	 * @return 보안문자
	 */
	public static int createSecureNumber () {
		
		int secureLength = 4;
		
		Random ran = new Random();
		int result = ran.nextInt(10);
		
		for(int i=0; i<secureLength; i++) {
			
			result = result * 10;
		}
		
		return result;
	}
	
	public static int createSecureNumber (int secureLength) {
		
		if(secureLength == 0) {
			secureLength = 4;
		}
		
		Random ran = new Random();
		int result = ran.nextInt(10);
		
		for(int i=0; i<secureLength; i++) {
			
			result = result * 10;
		}
		
		return result;
	}
	
	/**
	 * 현재날짜 조회
	 * @return 현재날짜 문자열
	 */
	public static String getCurrentDate() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		
		
		return currentDate;
	}
	
	/**
	 * 보안문자로 변환
	 * @param memberPw 비밀번호
	 * @param secureIndex 보안할 문자 자릿수
	 * @return 변환된 보안문자
	 */
	public static String changeSecure (String memberPw, int secureIndex) {
		
		int totLeng =  memberPw.length();
		String changePw = memberPw.substring(0, secureIndex);
		int pwLeng = changePw.length();
		
		for(int i =pwLeng; i<totLeng; i++) {
			
			changePw += "*";
		}
		
		return changePw;
	} 
	
	/**
	 * 천단위 수로 변경하기
	 * @param mileage 마일리지
	 * @return 천단위 마일리지
	 */
	public static String changeInteger (int value) {
		
		String result = NumberFormat.getInstance().format(value);
		
		return result;
	}
	
	/**
	 * 원화로 변경하기
	 * @param value
	 * @return 원(\)숫자
	 */
	public static String toWon (int value) {
		
		String result = NumberFormat.getCurrencyInstance().format(value);
		
		return result;
	}
	
	public static String getIdFromSecure (String secure, String newSecure) {
		
		if (secure != null && newSecure != null) {
			
			if(secure.equals(newSecure)) {
				
				int tot = secure.length();
				
				return secure.substring(10, tot);
			}
		}
		
		return null;
	}
}
