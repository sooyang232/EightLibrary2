package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDAO;
import db.UserDTO;

public class UpdateUserAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// 빈즈객체를 생성 -> 확인용
		UserDTO user = new UserDTO();
		
		user.setUserID(request.getParameter("userID"));
		user.setUserTel(request.getParameter("userTel"));
		user.setUserEmail(request.getParameter("userEmail"));
		
		UserDAO userdao = new UserDAO();
		boolean flag = userdao.userUpdate(user);
		System.out.println("flag="+flag);
		request.setAttribute("flag", new Boolean(flag));
		
		return "/updateUser.jsp";
	}

}
