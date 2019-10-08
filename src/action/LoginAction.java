package action;

import javax.servlet.http.*;
import javax.servlet.jsp.*;//pageContext

public class LoginAction implements CommandAction
{
	//요청한 명령어에 따른 페이지로 이동시켜주는 메서드
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/login.jsp";
  
	}
}
