package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.RevDAO;

public class Mypage2Action implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id=request.getParameter("userID");
		System.out.println("userID=>"+id);
		
		int count=0;
		List revlist=null;
		
		RevDAO rev = new RevDAO();
		count=rev.getRevCount(id);
		
		if(count>0) {
			revlist=rev.getRevBooks(id, count);
		}else {
			revlist=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("revlist", revlist);
		return "/mypage2.jsp";
	}

}
