package db;

import java.sql.Timestamp;

public class BookDTO {

	private String bookID;
	private String bookName;
	private String bookWriter;
	private String bookContent;
	private String bookPublisher;
	private Timestamp bookDate;
	private String isbn;
	private String bookImage;
	private String bookCheck;
	
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
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public Timestamp getBookDate() {
		return bookDate;
	}
	public void setBookDate(Timestamp bookDate) {
		this.bookDate = bookDate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public String getBookCheck() {
		return bookCheck;
	}
	public void setBookCheck(String bookCheck) {
		this.bookCheck = bookCheck;
	}
	
	
}
