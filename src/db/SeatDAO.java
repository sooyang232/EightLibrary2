package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;	//select
	private String sql="";
	
	public SeatDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	public int getSeatCount() {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from seat";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getSeatCount() 메서드 에러유발"+e);
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
			pstmt.setInt(1, start - 1);// mysql은 레코드 순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
				seats = new ArrayList(end);
				do {

					SeatDTO seat = new SeatDTO();
					
					seat.setSeatID(rs.getString("seatID"));
					seat.setRev_roomCheck(rs.getString("rev_roomCheck"));
					// 추가
					seats.add(seat);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getSeats() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return seats;
	}
	
	// 좌석예약후 해당좌석 예약불가처리
	public void seatUpdate(String id) {
		try {
			con = pool.getConnection();

			sql = "update seat set rev_roomCheck=? where seatID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "이용중");
			pstmt.setString(2, id);

			int update = pstmt.executeUpdate();
			System.out.println("좌석 예약불가처리(update)=>" + update);

		} catch (Exception e) {
			System.out.println("seatUpdate() 실행 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//좌석예약취소
	public void seatCancel(String id) {
		try {
			con = pool.getConnection();

			sql = "update seat set rev_roomCheck=? where seatID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "예약가능");
			pstmt.setString(2, id);

			int update = pstmt.executeUpdate();
			System.out.println("좌석예약취소처리(update)=>" + update);

		} catch (Exception e) {
			System.out.println("seatCancel() 실행 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
}
