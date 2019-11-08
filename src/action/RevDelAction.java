package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import db.RevDAO;

public class RevDelAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("UTF-8");
		  
		String userID=request.getParameter("userID");
		String bookID=request.getParameter("bookID");
		System.out.println("userID=>"+userID);
		System.out.println("bookID=>"+bookID);
		
		RevDAO rev = new RevDAO();
		rev.deleteRev(userID, bookID);
		
		BookDAO book = new BookDAO();
		String id=request.getParameter("bookID");
		System.out.println("bookID=>"+id);
		book.revBookCancel(id);
		
		return "/revDel.jsp";
	}
}
