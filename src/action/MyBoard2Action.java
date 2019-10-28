package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDAO;

public class MyBoard2Action implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("userID");
		System.out.println("userID=>"+id);
		
		int count=0;
		List board2list=null;
		
		BoardDAO board=new BoardDAO();
		count=board.getBoardCount(id);
		
		if (count>0) {
			board2list=board.getMyBoards(id, count);
		} else {
			board2list=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("count", count);
		request.setAttribute("board2list", board2list);
		return "/myboard2.jsp";
  
	}

}
