package db;

import java.sql.Timestamp;

public class RevDTO {

	private String userID;
	private String bookID;
	private String bookName;
	private String bookWriter;
	private Timestamp revStartDate;
	private Timestamp revEndDate;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public Timestamp getRevStartDate() {
		return revStartDate;
	}
	public void setRevStartDate(Timestamp revStartDate) {
		this.revStartDate = revStartDate;
	}
	public Timestamp getRevEndDate() {
		return revEndDate;
	}
	public void setRevEndDate(Timestamp revEndDate) {
		this.revEndDate = revEndDate;
	}
	
	
	
}
