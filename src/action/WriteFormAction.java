package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//list.jsp(�۾���) -> �űԱ�, content.jsp(�ۻ󼼺���) -> �۾��� -> �亯��
		int b1_num =0;		//writePro.jsp(�űԱ�)
		
		//content.do���� �Ű������� ����
		if(request.getParameter("b1_num") != null){	//0�� �ƴϰ�, ���� X -> ��� 1 �̻�
			b1_num = Integer.parseInt(request.getParameter("b1_num"));		//"3" -> 3
			
			System.out.println("b1_num => " +b1_num);
		}
		//������->������ �޸𸮿� ����
		request.setAttribute("b1_num", b1_num);
		
		return "/boardRegister.jsp";
	}

}
