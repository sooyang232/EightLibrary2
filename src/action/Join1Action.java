package action;

import javax.servlet.http.*;
import javax.servlet.jsp.*;

public class Join1Action implements CommandAction
{
	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
    return "/join1.jsp";
  
	}
}
