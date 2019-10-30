package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RevDAO {

	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";
	
	public RevDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	//레코드 개수 count
	public int getRevCount(String id) {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from rev where userID=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getRevCount() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//리스트 불러오기
	public List getRevBooks(String id,int cnt) {
		List revlist = null;
		try {
			con = pool.getConnection();
			sql="select * from rev where userID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
				revlist = new ArrayList(cnt);
				do {

					RevDTO rev = new RevDTO();
					
					rev.setUserID(rs.getString("userID"));
					rev.setBookID(rs.getString("bookID"));
					rev.setBookName(rs.getString("bookName"));
					rev.setBookWriter(rs.getString("bookWriter"));
					rev.setRevStartDate(rs.getTimestamp("revStartDate"));
					rev.setRevEndDate(rs.getTimestamp("revEndDate"));

					// 추가
					revlist.add(rev);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getRevBooks() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return revlist;
	}
	//insert
	public void insertRev(RevDTO rev) {
		try {
			con = pool.getConnection();
			sql = "insert into rev values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,rev.getUserID());
			pstmt.setString(2, rev.getBookID());
			pstmt.setString(3, rev.getBookName());
			pstmt.setString(4, rev.getBookWriter());
			pstmt.setTimestamp(5, rev.getRevStartDate());
			pstmt.setTimestamp(6, rev.getRevEndDate());
			
			
			int insert = pstmt.executeUpdate();
			System.out.println("insert=>" + insert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertRev 에러 유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	//delete
	public int deleteRev(String userID, String bookID) {
		int x=-1;
		try {
			con = pool.getConnection();
			sql ="delete from rev where userID=? and bookID=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, bookID);
			
			int delete=pstmt.executeUpdate();
			System.out.println("delete=>"+delete);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteRev에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
}
