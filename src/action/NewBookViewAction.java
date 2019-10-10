package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;
import db.BookDTO;

public class NewBookViewAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		
		BookDAO bookdao = new BookDAO();
		BookDTO book=bookdao.getBook(id);
		
		//ó������� �޸𸮿� ����
	    request.setAttribute("id", id);
	    request.setAttribute("book", book);
	    
	    return "/newbookView.jsp";
	}
}
