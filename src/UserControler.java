

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardExam1.UserDTO;

@WebServlet("/UserControler")
public class UserControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uiService = new UserInfoService();
	private UserShow usService = new UserShowService();
	private UserCheck ucService = new UserCheckService();
	
    public UserControler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGO(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGO(request, response);
	}

	private void doGO (HttpServletRequest request, HttpServletResponse response) {
		
		int contextPathLeng = (request.getContextPath()+"/user/").length();
		String uri = request.getRequestURI().substring(contextPathLeng);
		String pageHint = null;
		
		//회원가입
		if (uri == "userInsert") {
			
			HashMap<String,Object> map = UserDTO.toMap(request);
			uiService.userInsert(map);
			pageHint = "mainPage.jsp";
		
		//회원 인증(로그인)
		} else if (uri == "userCheck") {
			
			HashMap<String,Object> map = UserDTO.toMap(request);
			boolean isUser = ucService.userCheck(map);
			request.setAttribute("isUser", isUser);
			
			//추후 aop처리 꼭 해봐!
			if(isUser) {
				
				request.getSession().setAttribute("sessionId", map.get("id"));
				pageHint = "mainPage.jsp";
			} else 
				pageHint = "loginPage.jsp";

		//내 정보 조회(마이페이지)
		} else if (uri == "userShow") {
			
			checkSession(request, response);
				
			String id = (String)(request.getSession().getAttribute("sessionId"));
			HashMap<String, Object> map = usService.selectUser(id);
			pageHint = "mypage.jsp";
					
		//내 정보 변경
		} else if (uri == "userUpdate") {
			
			checkSession(request, response);
			
			String id = (String)(request.getSession().getAttribute("sessionId"));
			HashMap<String, Object> map = UserDTO.toMap(request);
			uiService.userUpdate(map);
			pageHint = request.getContextPath()+"/user/userShow";
			try {
				response.sendRedirect(pageHint);
			} catch (IOException e) {
				e.printStackTrace();
			}
		// 회원 탈퇴
		} else if (uri == "userDelete") {
			
			checkSession(request, response);
			
			String id = (String)(request.getSession().getAttribute("sessionId"));
			uiService.userDelete(id);
			request.getSession().setAttribute("sessionId", null);
			pageHint = "mainPage.jsp"; 
		}
	}
	
	private void checkSession (HttpServletRequest request, HttpServletResponse response) {
		
		if((String)(request.getSession().getAttribute("sessionId")) != null) {
			
			try {
				response.sendRedirect("loginPage.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
