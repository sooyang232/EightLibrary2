package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingRoomDAO {


	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";		//�����ų SQL���� ����
	
	//2.�����ڸ� ���ؼ� ����->������
		public ReadingRoomDAO() {
			try {
				pool=DBConnectionMgr.getInstance();
			} catch (Exception e) {
				System.out.println("DB���� ����=>"+e);
			}
		}//������
		
		public int getReserveCount() {
			int x=0;	//�� ���ڵ尳���� ����
			
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);	//������ڵ�
				sql="select count(*) from readingroom";		//select count(*) from member;
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {	//�����ִ� ����� �ִٸ�
					x=rs.getInt(1);
				}	
			} catch (Exception e) {
				System.out.println("getReserveCount() �޼��� ��������"+e);
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
				 * �׷��ȣ�� ���� �ֽ��� ���� �߽����� �����ϵ�, ���࿡ level�� ���� ��쿡��
				 * step������ ���������� ���ؼ� ���° ���ڵ��ȣ�� �����ؼ� �����Ұ��ΰ�?
				 */
				sql="select * from readingroom order by reserveID asc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start-1);//mysql�� ���ڵ� ������ ���������� 0���� ����
				pstmt.setInt(2, end);
				rs=pstmt.executeQuery();
				//�۸�Ϻ���
				if(rs.next()) {	//���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
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
						
						//�߰�
						reserveList.add(reserve);
					} while (rs.next());
				}	
			} catch (Exception e) {
				System.out.println("getReserves() �޼��� ��������"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return reserveList;
		}
}
