package db;
import java.sql.Timestamp;

public class UserDTO {

	private String userID;
	private String userPWD;
	private String userName;
	private String userEmail;
	private String userTel;
	private Timestamp JoinDate;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPWD() {
		return userPWD;
	}
	public void setUserPWD(String userPWD) {
		this.userPWD = userPWD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public Timestamp getJoinDate() {
		return JoinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		JoinDate = joinDate;
	}
	
	
}
