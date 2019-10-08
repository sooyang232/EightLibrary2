package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�߰�
import db.*;

// /content.do��û
public class BoardViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1.content.jsp�� ó���ؾ��� ������ ��� ó��
	    //�ۻ󼼺���=>��ǰ�� ������ �ڼ���(SangDetail.jsp)
	   // list.jsp���� ��ũ->content.jsp?num=3&pageNum=1
	   int num=Integer.parseInt(request.getParameter("num"));
	   String pageNum=request.getParameter("pageNum");
	  
	   BoardDAO  dbPro=new BoardDAO();
	   BoardDTO article=dbPro.getArticle(num);//select * frm board where num=?
	   
	
		//2.ó������� �޸𸮿� ����
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    //ref,re_step,re_level ����X ->article�ȿ� ������ �Ǿ������ϱ�
	    
		return "/boardView.jsp";//��
	}

}




