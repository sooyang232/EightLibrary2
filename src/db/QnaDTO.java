package db;
import java.sql.Timestamp;	

public class QnaDTO {

	private int b2_num;
	private String b2_title;
	private String b2_content;
	private String userID;
	private int b2_view;
	private Timestamp b2_date;
	private int b2_reply;
	private int b2_step;
	
	public int getB2_num() {
		return b2_num;
	}
	public void setB2_num(int b2_num) {
		this.b2_num = b2_num;
	}
	public String getB2_title() {
		return b2_title;
	}
	public void setB2_title(String b2_title) {
		this.b2_title = b2_title;
	}
	public String getB2_content() {
		return b2_content;
	}
	public void setB2_content(String b2_content) {
		this.b2_content = b2_content;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getB2_view() {
		return b2_view;
	}
	public void setB2_view(int b2_view) {
		this.b2_view = b2_view;
	}
	public Timestamp getB2_date() {
		return b2_date;
	}
	public void setB2_date(Timestamp b2_date) {
		this.b2_date = b2_date;
	}
	public int getB2_reply() {
		return b2_reply;
	}
	public void setB2_reply(int b2_reply) {
		this.b2_reply = b2_reply;
	}
	public int getB2_step() {
		return b2_step;
	}
	public void setB2_step(int b2_step) {
		this.b2_step = b2_step;
	}
	
	
}
