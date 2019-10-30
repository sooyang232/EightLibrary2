package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RevDAO {

	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";
	
	public RevDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}
	
	//���ڵ� ���� count
	public int getRevCount(String id) {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from rev where userID=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getRevCount() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//����Ʈ �ҷ�����
	public List getRevBooks(String id,int cnt) {
		List revlist = null;
		try {
			con = pool.getConnection();
			sql="select * from rev where userID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				revlist = new ArrayList(cnt);
				do {

					RevDTO rev = new RevDTO();
					
					rev.setUserID(rs.getString("userID"));
					rev.setBookID(rs.getString("bookID"));
					rev.setBookName(rs.getString("bookName"));
					rev.setBookWriter(rs.getString("bookWriter"));
					rev.setRevStartDate(rs.getTimestamp("revStartDate"));
					rev.setRevEndDate(rs.getTimestamp("revEndDate"));

					// �߰�
					revlist.add(rev);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getRevBooks() �޼��� ��������"+e);
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
			System.out.println("insertRev ���� ����"+e);
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
			System.out.println("deleteRev��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
}
