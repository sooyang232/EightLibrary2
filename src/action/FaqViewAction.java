package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;

public class FaqViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
	   int num=Integer.parseInt(request.getParameter("num"));
	   String pageNum=request.getParameter("pageNum");
	  
	   FaqDAO  dbPro=new FaqDAO();
	   FaqDTO article=dbPro.getArticle(num);//select * frm board where num=?
	
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    
		return "/faqView.jsp";
	}

}




