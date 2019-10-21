package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.InterDAO;

public class InterDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String userID=request.getParameter("userID");
		String bookID=request.getParameter("bookID");
		System.out.println("userID=>"+userID);
		System.out.println("bookID=>"+bookID);
		
		InterDAO inter = new InterDAO();
		inter.deleteInter(userID, bookID);
		
		return "/interDel.jsp";
	}

}
