package db;

public class ReadingRoomDTO {

	private int reserveID, memberID, seatNum;
	private String reserveTime,reserveEndTime,reserveStatus;
	
	public int getReserveID() {
		return reserveID;
	}
	public void setReserveID(int reserveID) {
		this.reserveID = reserveID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getReserveEndTime() {
		return reserveEndTime;
	}
	public void setReserveEndTime(String reserveEndTime) {
		this.reserveEndTime = reserveEndTime;
	}
	public String getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	
	
}
