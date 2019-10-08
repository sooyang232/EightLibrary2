package db;

import java.sql.*;//DB작업
import java.util.*;//Vector,Hashtable,list


public class UserDAO {

	//pool객체를 선언
		private DBConnectionMgr pool = null;//풀객체

		public UserDAO(){
	     //DBConnectionMgr의 객체를 얻어오는 구문
		  try
		  {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool="+pool);
		  }
		  catch (Exception e)
		  {
			System.out.println
			("Error:커넥션불러오기 실패"+e);
		  }
		}
		
		//회원인지를 체크해주는 메서드(인증)
	public boolean loginCheck(String id, String passwd) {
		//DB접속
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean check = false;// 로그인성공유무
		//DB작업(select)

		try {
			//DB접속구문
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
			System.out.println("=loginCheck()에러=");
			System.out.println(ex);
		} finally { // DB객체를 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	 //중복ID를 체크하는 메서드
	 public boolean checkId(String id){
	  
	    //DB접속
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	    
		boolean check = false;//중복ID유무
		//DB작업(select)

		try
		{
		  //DB접속구문
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
	      System.out.println("=checkId()에러=");
	      System.out.println(ex);
		}finally{	//DB객체를 해제
	      pool.freeConnection(con,pstmt,rs);
		}
	   return check;
	 }
	 
	//회원가입->화면에 출력(빈즈데이터)->DB
	 public boolean userInsert(UserDTO user){
	   
	   //DB접속
		Connection con = null;
		PreparedStatement pstmt=null;    
		boolean check = false;//회원가입성공유무
		String sql = null;

		try
		{
		  //DB접속구문
		  con = pool.getConnection();
		  //트랜잭션처리
		  //con.setAutoCommit(false);//DB작업의시작

	     sql="insert into user values(?,?,?,?,?,?)";
		 pstmt = con.prepareStatement(sql);
		 
	     pstmt.setString(1,user.getUserID());
		 pstmt.setString(2,user.getUserPWD());
		 pstmt.setString(3,user.getUserName());
		 pstmt.setString(4,user.getUserEmail());
		 pstmt.setString(5,user.getUserTel());
		 pstmt.setTimestamp(6, user.getJoinDate());
	     //1->insert갯수,0->삽입실패
		 int count = pstmt.executeUpdate();//insert
	     System.out.println("count="+count);
		 //con.commit();//오라클의 경우

		 if(count > 0){
			 check = true;//데이터입력 성공
		  }
		}
		catch (Exception ex)
		{
	      System.out.println("=userInsert()에러=");
	      System.out.println(ex);
		}finally{	//DB객체를 해제
	      pool.freeConnection(con,pstmt);
		}
	   return check;
	 }
}
