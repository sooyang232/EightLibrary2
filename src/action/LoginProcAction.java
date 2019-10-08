package action;

import javax.servlet.http.*;
import db.UserDAO;

public class LoginProcAction implements CommandAction

{
	public String requestPro(HttpServletRequest request,
            HttpServletResponse response)
            throws Throwable{
         
		  //login.do�� ���� login.jsp���� ���� �Ѿ��.
		  String id = request.getParameter("userID");
		  String passwd = request.getParameter("userPWD");

		  System.out.println("id="+id);
		  System.out.println("passwd="+passwd);
		  UserDAO userdao= new UserDAO();
		  boolean loginCheck = userdao.loginCheck(id,passwd);
       
		  request.setAttribute("loginCheck", 
				                new Boolean(loginCheck)); 
		  request.setAttribute("userID", new String(id));
		  
	      return "/loginProc.jsp";
	}
}