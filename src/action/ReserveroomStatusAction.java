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
		
		request.setCharacterEncoding("utf-8");//한글처리
		
		int count=0;//총레코드수
		List rooms=null;//화면에 출력할 레코드를 저장할 변수
		
		String userID=request.getParameter("userID");
		String seatID=request.getParameter("seatID");
		System.out.println("userID=>"+userID);
		System.out.println("seatID=>"+seatID);
		
		// room 데이터 추가

		RoomDTO room = new RoomDTO();
		room.setUserID(request.getParameter("userID"));
		room.setSeatID(request.getParameter("seatID"));
		room.setDate_time(request.getParameter("date_time"));
		room.setTimeEnd(request.getParameter("timeEnd"));
		room.setRevSpace(request.getParameter("revSpace"));
		room.setRevCheck(request.getParameter("revCheck"));
		
		RoomDAO roomdao = new RoomDAO();
		roomdao.insertRoom(room);
		
		//seat 데이터 변경
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
