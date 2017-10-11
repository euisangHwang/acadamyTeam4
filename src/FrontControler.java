

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.collections.MappingChange.Map;



/**
 * 맵핑 객체 생성
 * 맵핑 객체에 uri를 입력하고, map으로 모델+ 페이지힌트 리턴받기
 * 맵핑 객체에서, 핸들링 컨트롤러 생성 및 컨트롤러의 적절한 메서드 실행 (url이 각 메서드를 의미하게끔 맵핑할 것)
 * 프론트 컨트롤러는 모델 + 페이지힌트만 받으면 됨.
 */
@WebServlet("/FrontControler")
public class FrontControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HandlerMapping hm = new HandlerMapping();   
    private HashMap<String,Object> modelAndView = new HashMap<String, Object>();
	
    public FrontControler(HttpServletRequest request) {
        super();
        modelAndView = hm.getModelAndView(request.getRequestURI());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doControler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doControler(request, response);
	}
	
	private void doControler (HttpServletRequest request, HttpServletResponse response) {
		
		
		String pageHint = (String)modelAndView.get("view");
		String method = (String)modelAndView.get("method");
		
		if(method == "forward") {
			
			Object model =  modelAndView.get("model");
			request.setAttribute("model", model);
			RequestDispatcher rd = request.getRequestDispatcher(pageHint);
			rd.forward(request, response);
			
		} else {
			
			response.sendRedirect(pageHint);
		}
	}
}
