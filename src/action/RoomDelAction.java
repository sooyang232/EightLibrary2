package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.RoomDAO;
import db.SeatDAO;

public class RoomDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String userID=request.getParameter("userID");
		String seatID=request.getParameter("seatID");
		System.out.println("userID=>"+userID);
		System.out.println("seatID=>"+seatID);
		
		//room 수정
		RoomDAO roomdao = new RoomDAO();
		roomdao.updateRoom(userID, seatID);
		
		//seat 수정
		SeatDAO seatdao = new SeatDAO();
		seatdao.seatCancel(seatID);
		
		return "/roomDel.jsp";
	}

}
