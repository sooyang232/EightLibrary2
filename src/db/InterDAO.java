package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InterDAO {

	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";
	
	public InterDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}
	
	public int getInterCount(String id) {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from inter where userID=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getInterCount() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List getInterBooks(String id,int cnt) {
		List basketlist = null;
		try {
			con = pool.getConnection();
			sql="select * from inter where userID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				basketlist = new ArrayList(cnt);
				do {

					InterDTO inter = new InterDTO();
					
					inter.setUserID(rs.getString("userID"));
					inter.setBookID(rs.getString("bookID"));
					inter.setBookName(rs.getString("bookName"));
					inter.setBookWriter(rs.getString("bookWriter"));

					// �߰�
					basketlist.add(inter);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getInterBooks() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return basketlist;
	}
	
	public void insertInter(InterDTO inter) {
		try {
			con = pool.getConnection();
			sql = "insert into inter values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,inter.getUserID());
			pstmt.setString(2, inter.getBookID());
			pstmt.setString(3, inter.getBookName());
			pstmt.setString(4, inter.getBookWriter());
			
			int insert = pstmt.executeUpdate();
			System.out.println("insert=>" + insert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertInter ���� ����"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public int deleteInter(String userID, String bookID) {
		int x=-1;
		try {
			con = pool.getConnection();
			sql ="delete from inter where userID=? and bookID=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, bookID);
			
			int delete=pstmt.executeUpdate();
			System.out.println("delete=>"+delete);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteInter��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
}
