package db;
import java.sql.Timestamp;

public class BoardDTO {

	private int b1_num;
	private String b1_title;
	private String b1_content;
	private String userID;
	private int b1_view;
	private Timestamp b1_date;
	
	public int getB1_num() {
		return b1_num;
	}
	public void setB1_num(int b1_num) {
		this.b1_num = b1_num;
	}
	public String getB1_title() {
		return b1_title;
	}
	public void setB1_title(String b1_title) {
		this.b1_title = b1_title;
	}
	public String getB1_content() {
		return b1_content;
	}
	public void setB1_content(String b1_content) {
		this.b1_content = b1_content;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getB1_view() {
		return b1_view;
	}
	public void setB1_view(int b1_view) {
		this.b1_view = b1_view;
	}
	public Timestamp getB1_date() {
		return b1_date;
	}
	public void setB1_date(Timestamp b1_date) {
		this.b1_date = b1_date;
	}
}
