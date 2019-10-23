package db;

import java.sql.Timestamp;

public class NoticeDTO {
	
	private int b3_num;
	private String b3_title,b3_content,adminID;
	
	private Timestamp b3_date;
	private int b3_view;	
	
	public int getB3_num() {
		return b3_num;
	}
	public void setB3_num(int b3_num) {
		this.b3_num = b3_num;
	}
	public String getB3_title() {
		return b3_title;
	}
	public void setB3_title(String b3_title) {
		this.b3_title = b3_title;
	}
	public String getB3_content() {
		return b3_content;
	}
	public void setB3_content(String b3_content) {
		this.b3_content = b3_content;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public Timestamp getB3_date() {
		return b3_date;
	}
	public void setB3_date(Timestamp b3_date) {
		this.b3_date = b3_date;
	}
	public int getB3_view() {
		return b3_view;
	}
	public void setB3_view(int b3_view) {
		this.b3_view = b3_view;
	}
	
}