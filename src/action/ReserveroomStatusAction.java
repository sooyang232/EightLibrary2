package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.RoomDAO;
import db.RoomDTO;
import db.SeatDAO;

public class ReserveroomStatusAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");//�ѱ�ó��
		
		int count=0;//�ѷ��ڵ��
		List rooms=null;//ȭ�鿡 ����� ���ڵ带 ������ ����
		
		String userID=request.getParameter("userID");
		String seatID=request.getParameter("seatID");
		System.out.println("userID=>"+userID);
		System.out.println("seatID=>"+seatID);
		
		// room ������ �߰�

		RoomDTO room = new RoomDTO();
		room.setUserID(request.getParameter("userID"));
		room.setSeatID(request.getParameter("seatID"));
		room.setDate_time(request.getParameter("date_time"));
		room.setTimeEnd(request.getParameter("timeEnd"));
		room.setRevSpace(request.getParameter("revSpace"));
		room.setRevCheck(request.getParameter("revCheck"));
		
		RoomDAO roomdao = new RoomDAO();
		roomdao.insertRoom(room);
		
		//seat ������ ����
		SeatDAO seatdao = new SeatDAO();
		seatdao.seatUpdate(seatID);
		
		
		count = roomdao.getRoomCount(userID);
		
		if(count>0) {
			rooms=roomdao.getRooms(userID, count);
		}else {
			rooms=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("count", count);
		request.setAttribute("rooms", rooms);
		
		return "/reserveroomstatus.jsp";
	}

}
