package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�۾�����(writeForm.jsp���� �۾����ư�� �������)
//�߰�
import db.*;
import java.sql.Timestamp;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//���� �Է��� �޾Ƽ� BoardDTO�� ����->���̺� �����ϴ� ����
		
	     //�ѱ�ó��
	     request.setCharacterEncoding("utf-8");
	     BoardDTO article=new BoardDTO();
	   
	     //article.setB1_num(Integer.parseInt(request.getParameter("b1_num")));
	     
	     article.setB1_title(request.getParameter("b1_title"));
	     article.setB1_content(request.getParameter("b1_content"));
	     article.setUserID(request.getParameter("userID"));
	     article.setB1_date(new Timestamp(System.currentTimeMillis()));//�ۼ���¥
	      
	      BoardDAO dbPro=new BoardDAO();
	      dbPro.insertArticle(article);
		return "/writePro.jsp";
	}

}




