package action;

import javax.servlet.http.*;
import javax.servlet.jsp.*;//pageContext

public class LoginAction implements CommandAction
{
	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/login.jsp";
  
	}
}
