package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Join2Action implements CommandAction
{
	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/join2.jsp";
  
	}
}