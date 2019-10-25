package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDAO;
import db.UserDTO;

public class UserModifyAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		String id=request.getParameter("userID");
		System.out.println("userID=>"+id);
		
		UserDAO userdao = new UserDAO();
		UserDTO user = userdao.getUser(id);
		
		//처리결과를 메모리에 저장
		
	    request.setAttribute("id", id);
	    request.setAttribute("user",user);
		
		return "/usermodify.jsp";
	}

}
