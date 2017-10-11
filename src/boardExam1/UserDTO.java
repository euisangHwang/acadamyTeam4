package boardExam1;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class UserDTO {
	
	private UserDTO () {
		
	}
	
	static public HashMap<String,Object> toMap (HttpServletRequest request) {
		
		Enumeration<String> names = request.getParameterNames();
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		while(names.hasMoreElements()) {
			
			String name = names.nextElement();
			int leng = request.getParameterValues(name).length;
			
			if(leng >1) 
				map.put(name, request.getParameterValues(name));
			else 
				map.put(name, request.getParameter(name));
		}
		
		return map;
	}
}
