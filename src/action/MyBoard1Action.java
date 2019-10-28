package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.QnaDAO;

public class MyBoard1Action implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("userID");
		System.out.println("userID=>"+id);
		
		int count=0;
		List board1list=null;
		
		QnaDAO qna=new QnaDAO();
		count=qna.getBoardCount(id);
		
		if (count>0) {
			board1list=qna.getMyBoards(id, count);
		} else {
			board1list=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("count", count);
		request.setAttribute("board1list", board1list);
		return "/myboard1.jsp";
  
	}

}
