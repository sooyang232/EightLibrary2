package db;
import java.sql.Timestamp;	

public class FaqDTO {

	private int b4_num;
	private String b4_title;
	private String b4_content;
	private String adminID;
	private int b4_view;
	private Timestamp b4_date;
	
	public int getB4_num() {
		return b4_num;
	}
	public void setB4_num(int b4_num) {
		this.b4_num = b4_num;
	}
	public String getB4_title() {
		return b4_title;
	}
	public void setB4_title(String b4_title) {
		this.b4_title = b4_title;
	}
	public String getB4_content() {
		return b4_content;
	}
	public void setB4_content(String b4_content) {
		this.b4_content = b4_content;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public int getB4_view() {
		return b4_view;
	}
	public void setB4_view(int b4_view) {
		this.b4_view = b4_view;
	}
	public Timestamp getB4_date() {
		return b4_date;
	}
	public void setB4_date(Timestamp b4_date) {
		this.b4_date = b4_date;
	}
	

	
	
}
