package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;	//select
	private String sql="";
	
	public RoomDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}

	//레코드 개수 count
		public int getRoomCount(String id) {
			int x=0;	//총 레코드개수를 저장
			
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);	//디버깅코드
				sql="select count(*) from room where userID=?";	
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {	//보여주는 결과가 있다면
					x=rs.getInt(1);
				}	
			} catch (Exception e) {
				System.out.println("getRoomCount() 메서드 에러유발"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
	
		//리스트 불러오기
		public List getRooms(String id,int cnt) {
			List rooms = null;
			try {
				con = pool.getConnection();
				sql="select * from room where userID=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
					rooms = new ArrayList(cnt);
					do {

						RoomDTO room = new RoomDTO();
						
						room.setUserID(rs.getString("userID"));
						room.setSeatID(rs.getString("seatID"));
						room.setDate_time(rs.getString("date_time"));
						room.setTimeEnd(rs.getString("timeEnd"));
						room.setRevSpace(rs.getString("revSpace"));
						room.setRevCheck(rs.getString("revCheck"));

						// 추가
						rooms.add(room);
					} while (rs.next());
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getRooms() 메서드 에러유발"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return rooms;
		}
		
		//데이터 추가 insert
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
				System.out.println("insert확인=>" + insert);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("insertRoom() 에러 유발"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
		//좌석 예약 취소
		public void updateRoom(String userID, String seatID) {
			try {
				con = pool.getConnection();

				sql = "update room set revCheck=? where userID=? and seatID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "취소완료");
				pstmt.setString(2, userID);
				pstmt.setString(3, seatID);

				int update = pstmt.executeUpdate();
				System.out.println("좌석 예약 취소 처리(update)=>" + update);

			} catch (Exception e) {
				System.out.println("updateRoom() 실행 에러유발=>" + e);
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}
}
