package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.*;
import java.sql.Timestamp;

public class WriteProQnaAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//값을 입력을 받아서 QnaDTO에 저장 ->테이블에 저장하는 구문
		
	     //한글처리
	     request.setCharacterEncoding("utf-8");
	     QnaDTO article = new QnaDTO();
	     
	     article.setB2_num(Integer.parseInt(request.getParameter("b2_num")));   
	     
	     article.setB2_title(request.getParameter("b2_title"));
	     article.setB2_content(request.getParameter("b2_content"));
	     article.setUserID(request.getParameter("userID"));
	     //----------------------------------------여기까지 문자String
	     article.setB2_date(new Timestamp(System.currentTimeMillis()));//작성날짜
	     article.setB2_reply(Integer.parseInt(request.getParameter("b2_reply")));
	     article.setB2_step(Integer.parseInt(request.getParameter("b2_step")));
	      
	     QnaDAO dbPro=new QnaDAO();
	     dbPro.insertArticle(article);
		return "/writeProQna.jsp";
	}

}
