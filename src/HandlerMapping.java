import java.util.HashMap;


public class HandlerMapping {

	private HashMap<String, Object> modelAndView = null;
	
	public HashMap<String, Object> getModelAndView(String requestURI) {
		
		System.out.println("requestURI : "+requestURI);
		HashMap<String,Object> modelAndView = new HashMap<String,Object>();
		UserControler uControler = new UserControler();
		BoardControler bControler = new BoardControler();
		
		if(requestURI.equals("userInsert")) {
			
			uControler.userInsert
			
		} else if (requestURI.equals("userShow")) {
			
		} else if (requestURI.equals("userCheck")) {
			
		} else if (requestURI.equals("boardInsert")) {
			
		} else if (requestURI.equals("boardUpdate")) {
			
		} else if (requestURI.equals("boardDelete")) {
			
		} else if (requestURI.equals("boardShowAll")) {
			
		} else if (requestURI.equals("boardShowOne")) {
			
		} else if (requestURI.equals("commentInsert")) {
			
		} else if (requestURI.equals("commentUpdate")) {
			
		} else if (requestURI.equals("commentDelete")) {
			
		} else {}
		
		return modelAndView;
	}
}
