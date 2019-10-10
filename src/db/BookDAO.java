package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";

	public BookDAO(){
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	public int getBookCount() {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from book";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBookCount() 메서드 에러유발"+e);
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
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는 step값으로 오름차순을 통해서 몇번째 레코드번호를
			 * 기준해서 정렬할것인가?
			 */
			sql = "select * from book order by bookID desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드 순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
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
					// 추가
					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBooks() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bookList;
	}
	
	// 글상세 보기
		public BookDTO getBook(String id) {

			BookDTO book= null;
			try {
				con = pool.getConnection();

				sql = "select * from book where bookID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// 글목록보기
				if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
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
				System.out.println("getBook() 메서드 에러유발" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return book;
		}
		
}
