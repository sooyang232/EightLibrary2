package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.UserDTO;
import me.laziness.mail.Mail;
import db.UserDAO;

public class Join3Action implements CommandAction
{
	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
		//�ѱ�ó��
		request.setCharacterEncoding("utf-8");
		//���ü�� ���� -> Ȯ�ο�
		UserDTO userdto = new UserDTO();
		
		userdto.setUserName(request.getParameter("userName"));
		userdto.setUserID(request.getParameter("userID"));
		userdto.setUserPWD(request.getParameter("userPWD"));
		userdto.setUserTel(request.getParameter("userTel"));
		userdto.setUserEmail(request.getParameter("userEmail"));
		userdto.setJoinDate(new Timestamp(System.currentTimeMillis()));
		
		//ȸ�����Ը޼��� ȣ��
		UserDAO userdao = new UserDAO();
		boolean flag = userdao.userInsert(userdto);
		System.out.println("flag="+flag);
		request.setAttribute("flag", new Boolean(flag));
		
		//�̸��� ������
		//String id = (String)request.getSession().getAttribute("idKey");
		String id = request.getParameter("userID");
		//UserDAO userDAO = new UserDAO();
		//Mail.getInstance().sendEmail(userdao.getUserEMail(id),"�̸��� ����","�ȳ��ϼ���. EightLibrary�Դϴ�. �̸��� ������ �Ͻø� ȸ�������� �Ϸ�˴ϴ�. �����մϴ�^^");
		Mail.getInstance().sendEmail(userdao.getUserEMail(id),"�̸��� ����","�ȳ��ϼ���. EightLibrary�Դϴ�. �̸��� ������ �Ͻø� ȸ�������� �Ϸ�˴ϴ�. �����մϴ�^^");
		
    return "/join3.jsp";
  
	}
}