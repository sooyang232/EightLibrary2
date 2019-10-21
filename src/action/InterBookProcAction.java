package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.InterDAO;
import db.InterDTO;

public class InterBookProcAction implements CommandAction{

	public String requestPro(HttpServletRequest request,
            HttpServletResponse response)
            throws Throwable{
         
		  request.setCharacterEncoding("UTF-8");
		  
		  InterDTO inter = new InterDTO();
		  
		  inter.setUserID(request.getParameter("userID"));
		  inter.setBookID(request.getParameter("bookID"));
		  inter.setBookName(request.getParameter("bookName"));
		  inter.setBookWriter(request.getParameter("bookWriter"));
		  
		  InterDAO interdao = new InterDAO();
		  interdao.insertInter(inter);
		  
		  
	      return "/interBookProc.jsp";
	}
}
