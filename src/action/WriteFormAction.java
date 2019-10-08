package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//list.jsp(글쓰기) -> 신규글, content.jsp(글상세보기) -> 글쓰기 -> 답변글
		int b1_num =0;		//writePro.jsp(신규글)
		
		//content.do에서 매개변수가 전달
		if(request.getParameter("b1_num") != null){	//0은 아니고, 음수 X -> 양수 1 이상
			b1_num = Integer.parseInt(request.getParameter("b1_num"));		//"3" -> 3
			
			System.out.println("b1_num => " +b1_num);
		}
		//실행결과->서버의 메모리에 저장
		request.setAttribute("b1_num", b1_num);
		
		return "/boardRegister.jsp";
	}

}
