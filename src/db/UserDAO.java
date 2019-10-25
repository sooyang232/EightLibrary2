package db;

import java.sql.*;//DB�۾�
import java.util.*;//Vector,Hashtable,list


public class UserDAO {

	//pool��ü�� ����
		private DBConnectionMgr pool = null;//Ǯ��ü
		//����
		private Connection con=null;
		private PreparedStatement pstmt=null;	//?
		private ResultSet rs=null;	//select
		private String sql="";

		public UserDAO(){
	     //DBConnectionMgr�� ��ü�� ������ ����
		  try
		  {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool="+pool);
		  }
		  catch (Exception e)
		  {
			System.out.println
			("Error:Ŀ�ؼǺҷ����� ����"+e);
		  }
		}
		
		//ȸ�������� üũ���ִ� �޼���(����)
	public boolean loginCheck(String id, String passwd) {

		boolean check = false;// �α��μ�������
		//DB�۾�(select)

		try {
			//DB���ӱ���
			con = pool.getConnection();
			String sql = "select userID,userPWD from user " 
					+ "where userID = ? and userPWD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			check = rs.next();
			//System.out.println("id=>"+id);
		} catch (Exception ex) {
			System.out.println("=loginCheck()����=");
			System.out.println(ex);
		} finally { // DB��ü�� ����
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	 //�ߺ�ID�� üũ�ϴ� �޼���
	 public boolean checkId(String id){
	    
		boolean check = false;//�ߺ�ID����
		//DB�۾�(select)

		try
		{
		  //DB���ӱ���
		  con = pool.getConnection();
		  System.out.println("con="+con);
	      String sql="select userID from user "+
			         " where userID = ?";
	      pstmt = con.prepareStatement(sql);
		  pstmt.setString(1,id);
		  rs = pstmt.executeQuery();
		  check = rs.next();//true
		  /*
	       if(rs.next())
	         check=true;
			 else
			 check=false;
		  */
		}
		catch (Exception ex)
		{
	      System.out.println("=checkId()����=");
	      System.out.println(ex);
		}finally{	//DB��ü�� ����
	      pool.freeConnection(con,pstmt,rs);
		}
	   return check;
	 }
	 
	//ȸ������->ȭ�鿡 ���(�������)->DB
	 public boolean userInsert(UserDTO user){
	   
		boolean check = false;//ȸ�����Լ�������
		String sql = null;

		try
		{
		  //DB���ӱ���
		  con = pool.getConnection();
		  //Ʈ�����ó��
		  //con.setAutoCommit(false);//DB�۾��ǽ���

	     sql="insert into user values(?,?,?,?,?,?)";
		 pstmt = con.prepareStatement(sql);
		 
	     pstmt.setString(1,user.getUserID());
		 pstmt.setString(2,user.getUserPWD());
		 pstmt.setString(3,user.getUserName());
		 pstmt.setString(4,user.getUserEmail());
		 pstmt.setString(5,user.getUserTel());
		 pstmt.setTimestamp(6, user.getJoinDate());
	     //1->insert����,0->���Խ���
		 int count = pstmt.executeUpdate();//insert
	     System.out.println("count="+count);
		 //con.commit();//����Ŭ�� ���

		 if(count > 0){
			 check = true;//�������Է� ����
		  }
		}
		catch (Exception ex)
		{
	      System.out.println("=userInsert()����=");
	      System.out.println(ex);
		}finally{	//DB��ü�� ����
	      pool.freeConnection(con,pstmt);
		}
	   return check;
	 }
	 //ȸ����������->ȸ�������ҷ�����
	 public UserDTO getUser(String id) {
		 UserDTO user=null;
		 
		 try {
				con = pool.getConnection();

				sql = "select * from user where userID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// �۸�Ϻ���
				if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
					user = new UserDTO();
					
					user.setUserID(rs.getString("userID"));
					user.setUserPWD(rs.getString("userPWD"));
					user.setUserName(rs.getString("userName"));
					user.setUserEmail(rs.getString("userEmail"));
					user.setUserTel(rs.getString("userTel"));

				}
			} catch (Exception e) {
				System.out.println("getUser() �޼��� ��������" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		 
		 return user;
	 }
	 //ȸ����������
	 public boolean userUpdate(UserDTO user) {
		 boolean check = false;//ȸ���������� ��������
		 
			 try {
				 con = pool.getConnection();

				 sql = "update user set userTel=?,userEmail=? where userID=?";
				 pstmt=con.prepareStatement(sql);
				 
				 pstmt.setString(1, user.getUserTel());
				 pstmt.setString(2, user.getUserEmail());
				 pstmt.setString(3, user.getUserID());
				 
				 int update=pstmt.executeUpdate();
				 System.out.println("ȸ������ ���� ��������(update)=>"+update);//1 or 0����
				 
				 if(update > 0){
					 check = true;//�������Է� ����
				  }
			} catch (Exception e) {
				System.out.println("userUpdate() �޼��� ��������" + e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
		 
		 return check;
	 }
}
