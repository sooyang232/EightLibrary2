package action;

import javax.servlet.http.*;
import javax.servlet.jsp.*;//pageContext

public class IndexAction implements CommandAction
{
	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/index.jsp";
  
	}
}

