package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.InterDAO;

public class BasketlistAction implements CommandAction{

	//��û�� ��ɾ ���� �������� �̵������ִ� �޼���
		public String requestPro(HttpServletRequest request,
			                     HttpServletResponse response)
			                     throws Throwable{
			
			String id=request.getParameter("userID");
			System.out.println("userID=>"+id);
			
			int count=0;
			List basketlist=null;
			
			InterDAO inter = new InterDAO();
			count=inter.getInterCount(id);
			
			if(count>0) {
				basketlist=inter.getInterBooks(id, count);
			}else {
				basketlist=Collections.EMPTY_LIST;
			}
			
			request.setAttribute("basketlist", basketlist);
			return "/basketlist.jsp";
	  
		}
}
