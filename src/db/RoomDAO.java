package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;	//select
	private String sql="";
	
	public RoomDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}

	//���ڵ� ���� count
		public int getRoomCount(String id) {
			int x=0;	//�� ���ڵ尳���� ����
			
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);	//������ڵ�
				sql="select count(*) from room where userID=?";	
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {	//�����ִ� ����� �ִٸ�
					x=rs.getInt(1);
				}	
			} catch (Exception e) {
				System.out.println("getRoomCount() �޼��� ��������"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
	
		//����Ʈ �ҷ�����
		public List getRooms(String id,int cnt) {
			List rooms = null;
			try {
				con = pool.getConnection();
				sql="select * from room where userID=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
					rooms = new ArrayList(cnt);
					do {

						RoomDTO room = new RoomDTO();
						
						room.setUserID(rs.getString("userID"));
						room.setSeatID(rs.getString("seatID"));
						room.setDate_time(rs.getString("date_time"));
						room.setTimeEnd(rs.getString("timeEnd"));
						room.setRevSpace(rs.getString("revSpace"));
						room.setRevCheck(rs.getString("revCheck"));

						// �߰�
						rooms.add(room);
					} while (rs.next());
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getRooms() �޼��� ��������"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return rooms;
		}
		
		//������ �߰� insert
		public void insertRoom(RoomDTO room) {
			try {
				con = pool.getConnection();
				sql = "insert into room values(?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,room.getUserID());
				pstmt.setString(2, room.getSeatID());
				pstmt.setString(3, room.getDate_time());
				pstmt.setString(4, room.getTimeEnd());
				pstmt.setString(5, room.getRevSpace());
				pstmt.setString(6, room.getRevCheck());
				
				
				int insert = pstmt.executeUpdate();
				System.out.println("insertȮ��=>" + insert);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("insertRoom() ���� ����"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
		//�¼� ���� ���
		public void updateRoom(String userID, String seatID) {
			try {
				con = pool.getConnection();

				sql = "update room set revCheck=? where userID=? and seatID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "��ҿϷ�");
				pstmt.setString(2, userID);
				pstmt.setString(3, seatID);

				int update = pstmt.executeUpdate();
				System.out.println("�¼� ���� ��� ó��(update)=>" + update);

			} catch (Exception e) {
				System.out.println("updateRoom() ���� ��������=>" + e);
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}
}
