package work.view;

import java.util.ArrayList;

import work.controller.MemberControler;
import work.model.dto.Member;
import work.util.MemberUtil;

public class MemberTest {

	public static void main(String[] args) {

		MemberControler controler = new MemberControler();
		
/*		System.out.println("회원가입");
		Member mem = new Member(null, null, null, null, null); 
		ArrayList<String> result = controler.addMember(mem);
		System.out.println(result+"\n");
		
		System.out.println("로그인 ");
		System.out.println(controler.login("user01", "password01")+"\n");*/
		
		System.out.println("아이디 찾기");
		String secure = controler.selectOneMemberId("010-1234-1111", "user01@work.com");
		System.out.println(secure);
		
		String id = MemberUtil.getIdFromSecure(secure, secure);
		System.out.println(id);
	}
	//테스트를 위한 객체생성없이 아규먼트로 전달받은 문자열을 출력하는 메서드 구현
	//메서드이름 : print
}
