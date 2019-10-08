package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingRoomDAO {


	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";		//실행시킬 SQL구문 저장
	
	//2.생성자를 통해서 연결->의존성
		public ReadingRoomDAO() {
			try {
				pool=DBConnectionMgr.getInstance();
			} catch (Exception e) {
				System.out.println("DB접속 오류=>"+e);
			}
		}//생성자
		
		public int getReserveCount() {
			int x=0;	//총 레코드개수를 저장
			
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);	//디버깅코드
				sql="select count(*) from readingroom";		//select count(*) from member;
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {	//보여주는 결과가 있다면
					x=rs.getInt(1);
				}	
			} catch (Exception e) {
				System.out.println("getReserveCount() 메서드 에러유발"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
		
		public List getReserves(int start,int end) {
			
			List reserveList=null;
			
			try {
				con=pool.getConnection();
				/*
				 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는
				 * step값으로 오름차순을 통해서 몇번째 레코드번호를 기준해서 정렬할것인가?
				 */
				sql="select * from readingroom order by reserveID asc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start-1);//mysql은 레코드 순번이 내부적으로 0부터 시작
				pstmt.setInt(2, end);
				rs=pstmt.executeQuery();
				//글목록보기
				if(rs.next()) {	//레코드가 최소 만족 1개 이상 존재한다면
					reserveList=new ArrayList(end);
					do {
						//BoardDTO article=makeArticleFromResult();
						
						  ReadingRoomDTO reserve=new ReadingRoomDTO();
						  reserve.setReserveID(rs.getInt("reserveID"));
						  reserve.setMemberID(rs.getInt("memberID"));
						  reserve.setSeatNum(rs.getInt("seatNum"));
						  reserve.setReserveTime(rs.getString("reserveTime"));
						  reserve.setReserveEndTime(rs.getString("reserveEndTime"));
						  reserve.setReserveStatus(rs.getString("reserveStatus"));
						
						//추가
						reserveList.add(reserve);
					} while (rs.next());
				}	
			} catch (Exception e) {
				System.out.println("getReserves() 메서드 에러유발"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return reserveList;
		}
}
