package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;	//select
	private String sql="";
	
	public SeatDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}
	
	public int getSeatCount() {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from seat";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getSeatCount() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List getSeats(int start, int end) {

		List seats = null;

		try {
			con = pool.getConnection();
			
			sql = "select * from seat order by seatID asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ� ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				seats = new ArrayList(end);
				do {

					SeatDTO seat = new SeatDTO();
					
					seat.setSeatID(rs.getString("seatID"));
					seat.setRev_roomCheck(rs.getString("rev_roomCheck"));
					// �߰�
					seats.add(seat);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getSeats() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return seats;
	}
	
	// �¼������� �ش��¼� ����Ұ�ó��
	public void seatUpdate(String id) {
		try {
			con = pool.getConnection();

			sql = "update seat set rev_roomCheck=? where seatID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "�̿���");
			pstmt.setString(2, id);

			int update = pstmt.executeUpdate();
			System.out.println("�¼� ����Ұ�ó��(update)=>" + update);

		} catch (Exception e) {
			System.out.println("seatUpdate() ���� ��������=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//�¼��������
	public void seatCancel(String id) {
		try {
			con = pool.getConnection();

			sql = "update seat set rev_roomCheck=? where seatID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "���డ��");
			pstmt.setString(2, id);

			int update = pstmt.executeUpdate();
			System.out.println("�¼��������ó��(update)=>" + update);

		} catch (Exception e) {
			System.out.println("seatCancel() ���� ��������=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
}
