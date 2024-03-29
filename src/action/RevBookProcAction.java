package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import db.RevDAO;
import db.RevDTO;

public class RevBookProcAction implements CommandAction{

	public String requestPro(HttpServletRequest request,
            HttpServletResponse response)
            throws Throwable{
		
		  request.setCharacterEncoding("UTF-8");
		  
		  RevDTO rev = new RevDTO();
		  
		  rev.setUserID(request.getParameter("userID"));
		  rev.setBookID(request.getParameter("bookID"));
		  rev.setBookName(request.getParameter("bookName"));
		  rev.setBookWriter(request.getParameter("bookWriter"));
		  rev.setRevStartDate(new Timestamp(System.currentTimeMillis()));
		  rev.setRevEndDate(new Timestamp(System.currentTimeMillis()+7*24*60*60*1000));
		  
		  RevDAO revdao = new RevDAO();
		  revdao.insertRev(rev);
		  
		  BookDAO book = new BookDAO();
		  String id=request.getParameter("bookID");
		  System.out.println("bookID=>"+id);
		  book.revBookUpdate(id);
		  
		  
	      return "/revBookProc.jsp";
	}
}
