package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//글쓰기폼(writeForm.jsp에서 글쓰기버튼을 누른경우)
//추가
import db.*;
import java.sql.Timestamp;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//값을 입력을 받아서 BoardDTO에 저장->테이블에 저장하는 구문
		
	     //한글처리
	     request.setCharacterEncoding("utf-8");
	     BoardDTO article=new BoardDTO();
	   
	     //article.setB1_num(Integer.parseInt(request.getParameter("b1_num")));
	     
	     article.setB1_title(request.getParameter("b1_title"));
	     article.setB1_content(request.getParameter("b1_content"));
	     article.setUserID(request.getParameter("userID"));
	     article.setB1_date(new Timestamp(System.currentTimeMillis()));//작성날짜
	      
	      BoardDAO dbPro=new BoardDAO();
	      dbPro.insertArticle(article);
		return "/writePro.jsp";
	}

}




