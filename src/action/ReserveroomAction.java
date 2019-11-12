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
		request.setCharacterEncoding("utf-8");//한글처리
		
		int count=0;//총레코드수
		List seats=null;//화면에 출력할 레코드를 저장할 변수
		
		SeatDAO seatdao=new SeatDAO();
		count=seatdao.getSeatCount();
		System.out.println("현재 검색된 레코드수(count)=>"+count);
		
		if(count>0) {
			seats=seatdao.getSeats(1, count);
		}else {
			seats=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("seats", seats);	//${seats}
		
		return "/reserveroom.jsp";
	}

}
