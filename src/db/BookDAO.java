package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";

	public BookDAO(){
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}
	
	public int getBookCount() {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from book";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBookCount() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List getBooks(int start, int end) {

		List bookList = null;

		try {
			con = pool.getConnection();
			/*
			 * �׷��ȣ�� ���� �ֽ��� ���� �߽����� �����ϵ�, ���࿡ level�� ���� ��쿡�� step������ ���������� ���ؼ� ���° ���ڵ��ȣ��
			 * �����ؼ� �����Ұ��ΰ�?
			 */
			sql = "select * from book order by bookID desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ� ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				bookList = new ArrayList(end);
				do {

					BookDTO book = new BookDTO();
					book.setBookID(rs.getString("bookID"));
					book.setBookName(rs.getString("bookName"));
					book.setBookWriter(rs.getString("bookWriter"));
					book.setBookContent(rs.getString("bookContent"));
					book.setBookPublisher(rs.getString("bookPublisher"));
					book.setBookDate(rs.getTimestamp("bookDate"));
					book.setIsbn(rs.getString("isbn"));
					book.setBookImage(rs.getString("bookImage"));
					book.setBookCheck(rs.getString("bookCheck"));
					// �߰�
					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBooks() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bookList;
	}
	
	// �ۻ� ����
		public BookDTO getBook(String id) {

			BookDTO book= null;
			try {
				con = pool.getConnection();

				sql = "select * from book where bookID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// �۸�Ϻ���
				if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
					book = new BookDTO();
					
					book.setBookID(rs.getString("bookID"));
					book.setBookName(rs.getString("bookName"));
					book.setBookWriter(rs.getString("bookWriter"));
					book.setBookContent(rs.getString("bookContent"));
					book.setBookPublisher(rs.getString("bookPublisher"));
					book.setBookDate(rs.getTimestamp("bookDate"));
					book.setIsbn(rs.getString("isbn"));
					book.setBookImage(rs.getString("bookImage"));
					book.setBookCheck(rs.getString("bookCheck"));

				}
			} catch (Exception e) {
				System.out.println("getBook() �޼��� ��������" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return book;
		}
		
}
