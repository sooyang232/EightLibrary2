package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteQnaAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//수정 필요,,
		
		  //qna.jsp(글쓰기)->신규글 ,qnaView.jsp(글상세보기)->글쓰기->답변글 
		int b2_num=0,b2_reply=0,b2_step=0;//writeProQna.jsp(신규글)
		  
		  //content.jsp에서 매개변수가 전달 
		if(request.getParameter("b2_num")!=null){ 
		//0은 아니고,음수X ->양수 1이상 
		  b2_num=Integer.parseInt(request.getParameter("b2_num"));//"3"->3
		  b2_reply=Integer.parseInt(request.getParameter("b2_reply"));
		  b2_step=Integer.parseInt(request.getParameter("b2_step"));
		  
		  System.out.println("qnaView.jsp에서 넘어온 매개변수 확인");
		  System.out.println("b2_num=>"+b2_num+",b2_reply="+b2_reply
		  +"b2_step="+b2_step); }
		 
		//실행결과->서버의 메모리에 저장 
		request.setAttribute("b2_num",b2_num);
		request.setAttribute("b2_reply",b2_reply);
		request.setAttribute("b2_step",b2_step);
		 
	   
	   return "/writeQna.jsp";
	}

}