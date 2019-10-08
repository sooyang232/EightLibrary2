package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.UserDTO;
import db.UserDAO;

public class Join3Action implements CommandAction
{
	//요청한 명령어에 따른 페이지로 이동시켜주는 메서드
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
		//한글처리
		request.setCharacterEncoding("utf-8");
		//빈즈객체를 생성 -> 확인용
		UserDTO userdto = new UserDTO();
		
		userdto.setUserName(request.getParameter("userName"));
		userdto.setUserID(request.getParameter("userID"));
		userdto.setUserPWD(request.getParameter("userPWD"));
		userdto.setUserTel(request.getParameter("userTel"));
		userdto.setUserEmail(request.getParameter("userEmail"));
		userdto.setJoinDate(new Timestamp(System.currentTimeMillis()));
		
		//회원가입메서드 호출
		UserDAO userdao = new UserDAO();
		boolean flag = userdao.userInsert(userdto);
		System.out.println("flag="+flag);
		request.setAttribute("flag", new Boolean(flag));
		
    return "/join3.jsp";
  
	}
}