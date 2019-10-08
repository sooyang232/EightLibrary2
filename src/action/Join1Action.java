package action;

import javax.servlet.http.*;
import javax.servlet.jsp.*;

public class Join1Action implements CommandAction
{
	//요청한 명령어에 따른 페이지로 이동시켜주는 메서드
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/join1.jsp";
  
	}
}
