package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SeatDAO;

public class ReserveroomAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//�ѱ�ó��
		
		int count=0;//�ѷ��ڵ��
		List seats=null;//ȭ�鿡 ����� ���ڵ带 ������ ����
		
		SeatDAO seatdao=new SeatDAO();
		count=seatdao.getSeatCount();
		System.out.println("���� �˻��� ���ڵ��(count)=>"+count);
		
		if(count>0) {
			seats=seatdao.getSeats(1, count);
		}else {
			seats=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("seats", seats);	//${seats}
		
		return "/reserveroom.jsp";
	}

}
