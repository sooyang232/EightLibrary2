package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteQnaAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//���� �ʿ�,,
		
		  //qna.jsp(�۾���)->�űԱ� ,qnaView.jsp(�ۻ󼼺���)->�۾���->�亯�� 
		int b2_num=0,b2_reply=0,b2_step=0;//writeProQna.jsp(�űԱ�)
		  
		  //content.jsp���� �Ű������� ���� 
		if(request.getParameter("b2_num")!=null){ 
		//0�� �ƴϰ�,����X ->��� 1�̻� 
		  b2_num=Integer.parseInt(request.getParameter("b2_num"));//"3"->3
		  b2_reply=Integer.parseInt(request.getParameter("b2_reply"));
		  b2_step=Integer.parseInt(request.getParameter("b2_step"));
		  
		  System.out.println("qnaView.jsp���� �Ѿ�� �Ű����� Ȯ��");
		  System.out.println("b2_num=>"+b2_num+",b2_reply="+b2_reply
		  +"b2_step="+b2_step); }
		 
		//������->������ �޸𸮿� ���� 
		request.setAttribute("b2_num",b2_num);
		request.setAttribute("b2_reply",b2_reply);
		request.setAttribute("b2_step",b2_step);
		 
	   
	   return "/writeQna.jsp";
	}

}