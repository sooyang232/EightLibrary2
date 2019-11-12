package db;

public class RoomDTO {

	private String userID;		//회원ID
	private String seatID;		//좌석번호
	private String date_time;//예약시간
	private String timeEnd;	//예약만기시간
	private String revSpace;	//예약공간
	private String revCheck;	//예약상태
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getRevSpace() {
		return revSpace;
	}
	public void setRevSpace(String revSpace) {
		this.revSpace = revSpace;
	}
	public String getRevCheck() {
		return revCheck;
	}
	public void setRevCheck(String revCheck) {
		this.revCheck = revCheck;
	}

}
