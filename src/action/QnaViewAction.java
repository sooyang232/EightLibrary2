package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;

public class QnaViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1.content.jsp�� ó���ؾ��� ������ ��� ó��
	    //�ۻ󼼺���=>��ǰ�� ������ �ڼ���(SangDetail.jsp)
	   // list.jsp���� ��ũ->content.jsp?num=3&pageNum=1
	   int num=Integer.parseInt(request.getParameter("num"));
	   String pageNum=request.getParameter("pageNum");
	  
	   QnaDAO  dbPro=new QnaDAO();
	   QnaDTO article=dbPro.getArticle(num);//select * frm board where num=?
	   //��ũ���ڿ��� ���̸� ���̱� ���ؼ�
	   int b2_reply=article.getB2_reply();
	   int b2_step=article.getB2_step();
	   System.out.println("qnaView.do�� �Ű����� Ȯ��");
	   System.out.println("b2_reply=>"+b2_reply+",b2_step=>"+b2_step);
	
		//2.ó������� �޸𸮿� ����
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    //ref,re_step,re_level ����X ->article�ȿ� ������ �Ǿ������ϱ�
	    
		return "/qnaView.jsp";//��
	}

}
