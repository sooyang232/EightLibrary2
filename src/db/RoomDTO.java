package db;

public class RoomDTO {

	private String userID;		//ȸ��ID
	private String seatID;		//�¼���ȣ
	private String date_time;//����ð�
	private String timeEnd;	//���ุ��ð�
	private String revSpace;	//�������
	private String revCheck;	//�������
	
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
