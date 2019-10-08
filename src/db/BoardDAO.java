package db;

import java.sql.*;
import java.util.*;



public class BoardDAO {

	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";	
	
	public BoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}//생성자
	
	public int getArticleCount() {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from b1";		//select count(*) from member;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getArticleCount() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
public List getArticles(int start,int end) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는
			 * step값으로 오름차순을 통해서 몇번째 레코드번호를 기준해서 정렬할것인가?
			 */
			sql="select * from b1 order by b1_num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql은 레코드 순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			//글목록보기
			if(rs.next()) {	//레코드가 최소 만족 1개 이상 존재한다면
				articleList=new ArrayList(end);
				do {
					
					  BoardDTO article=new BoardDTO(); 
					  article.setB1_num(rs.getInt("b1_num"));
					  article.setB1_title(rs.getString("b1_title"));
					  article.setB1_content(rs.getString("b1_content"));
					  article.setUserID(rs.getString("userID"));
					  article.setB1_view(rs.getInt("b1_view"));
					  article.setB1_date(rs.getTimestamp("b1_date"));
					  
					//추가
					articleList.add(article);
				} while (rs.next());
			}	
		} catch (Exception e) {
			System.out.println("getArticles() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
//------(3)페이징 처리계산 정리해주는 메서드(ListAction)----------------------------------------
	//1)화면에 보여주는 페이지번호 2) 화면에 출력할 레코드 개수
	public Hashtable pageList(String pageNum,int count) {
		
		//1.페이징 처리 결과를 저장할 hashtable 객체를 선언
		Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
		//ListAction에서의 복잡한 페이징처리를 대신 처리
	     int pageSize=10;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
	     int blockSize=10;//pagePerBlock->블럭당 보여주는 페이지수 10
	     
	    //게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력
	    if(pageNum==null){
	    	pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 하기때문에),가장 최근의 글
	    }
	    int currentPage=Integer.parseInt(pageNum);//"1"->1 현재페이지(=nowPage)
	    //메서드 호출->시작 레코드번호
	    //                  (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
	    int startRow=(currentPage-1)*pageSize+1; //시작 레코드 번호
	    int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30 ->마지막 레코드번호 	
	    int number=0;//beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물번호
	    System.out.println("현재 레코드수(count)=>"+count);
	    //			122-(1-1)*10=122,122-(2-1)*10
	    number=count-(currentPage-1)*pageSize;
	    System.out.println("페이지별 number=>"+number);
	    //총페이지수,시작,종료페이지 계산
	    //					122/10=12.2+1=>12.2+1.0=13.2=13페이지
	    int pageCount=count/pageSize+(count%pageSize==0?0:1);
	    //모델1 list.jsp 소스
	  //블럭당 페이지수 계산 -> 10 -> 10의 배수,3 ->3배수
	  		int startPage=0;
	  		if(currentPage%blockSize!=0){//10의배수가 아니라면
	  			startPage=currentPage/blockSize*blockSize+1;
	  		}else{	//10%10=0,(10,20,30)
	  				//			((10/10)-1)*10+1
	  			startPage=((currentPage/blockSize)-1)*blockSize+1;
	  		}
	  		int endPage=startPage+blockSize-1;		//1+10-1=10
	  		System.out.println("startPage="+startPage+",endPage="+endPage);
	  		if(endPage > pageCount)
	  			endPage=pageCount;//마지막 페이지 = 총페이지수
	  		//페이징처리에 대한 계산결과 -> Hashtable,HashMap -> ListAction 전달 -> 메모리에 저장 -> list.jsp
	  		pgList.put("pageSize", pageSize);
	  		pgList.put("blockSize", blockSize);
	  		pgList.put("currentPage", currentPage);
	  		pgList.put("startRow", startRow);
	  		pgList.put("endRow", endRow);
	  		pgList.put("count", count);
	  		pgList.put("number", number);
	  		pgList.put("startPage", startPage);
	  		pgList.put("endPage", endPage);
	  		pgList.put("pageCount", pageCount);
	  		
	  		return pgList;
	}
//(1)게시판의 레코드수를 검색어에 따른 메서드작성(검색분야,검색어)

	public int getArticleSearchCount(String search, String searchtext) {  //getMemberCount()
		int x=0;//총 레코드갯수를 저장
		
		try {
			con=pool.getConnection();//커넥션풀에서 한개 빌려오는작업
			System.out.println("con=>"+con);//디버깅코드
			//---------------------------------------------------------------
			if(search==null || search=="") { // 검색분야 선택X
				sql="select count(*) from b1"; //select count(*) from member;
			}else {	//검색분야(제목,작성자,제목+본문)
				if(search.equals("subject_content")) {	//제목+본문
					sql="select count(*) from b1 where b1_title like '%"+
				searchtext+"%' or content like '%"+searchtext+"%'";
				}else {	//제목,작성자 -> 매개변수를 이용해서 하나의 sql통합
					sql="select count(*) from b1 where "+search+" like '%"+
				searchtext+"%'";
				}
			}
			System.out.println("getArticleSearchCount 검색sql=>"+sql);
			//---------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);  //변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
			}
		}catch(Exception e) {
			System.out.println("getArticleSearchCount() 메서드 에러유발"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);//연결객체 및 다른 객체 반납
		}
		return x;
	}	
	//----------------------------------------------------------------------------------
		// (2)검색어에 따른 레코드의 범위지정에 대한 메서드
		public List getBoardArticles(int start, int end, String search, String searchtext) {// getMemberList(int start,int
																							// end)

			List articleList = null;// ArrayList articleList=null;

			try {
				con = pool.getConnection();
				// --------------------------------------------------------------------------
				if (search == null | search == "") {
					sql = "select * from b1 order by b1_num desc limit ?,?";// 1,10
				} else { // 제목+본문
					if (search.equals("title_content")) { // 제목+본문
						sql = "select * from b1 where b1_title like '%" +searchtext+ "%' or b1_content like '%"
								+searchtext+ "%' order by b1_num desc limit ?,?";
					} else { // 제목,작성자 -> 매개변수를 이용해서 하나의 sql통합
						sql = "select * from b1 where " +search+ " like '%" +searchtext
								+ "%' order by b1_num desc limit ?,?";
					}
				}
				System.out.println("getBoardArticles()의 sql=>"+sql);
				// --------------------------------------------------------------------------
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0부터 시작
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				// 글목록보기
				if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
					articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 생성하라
					do {
						BoardDTO article = new BoardDTO();// MemberDTO~
						
						article.setB1_num(rs.getInt("b1_num"));
						article.setB1_title(rs.getString("b1_title"));
						article.setB1_content(rs.getString("b1_content"));
						article.setUserID(rs.getString("userID"));
						article.setB1_view(rs.getInt("b1_view"));
						article.setB1_date(rs.getTimestamp("b1_date"));
						
					
						// 추가
						articleList.add(article);
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println("getBoardArticles() 메서드 에러유발" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return articleList;
		}
		
		//글상세보기
		
		public BoardDTO getArticle(int num) {
			
			BoardDTO article=null;
			try {
				con= pool.getConnection();
				
				sql="update b1 set b1_view=b1_view+1 where b1_num=?";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, num);
				int update= pstmt.executeUpdate();
				System.out.println("업데이트"+update);
				
				sql="select * from b1 where b1_num=?";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				//글목록보기
				if(rs.next()) {
					article=new BoardDTO();
					
					article.setB1_num(rs.getInt("b1_num"));
					article.setB1_title(rs.getString("b1_title"));
					article.setB1_content(rs.getString("b1_content"));
					article.setUserID(rs.getString("userID"));
					article.setB1_view(rs.getInt("b1_view"));
					article.setB1_date(rs.getTimestamp("b1_date"));
				}//if
			}catch(Exception e) {
				System.out.println("getArticle() 메서드 에러유발"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			
			return article;
		}

	public void insertArticle(BoardDTO article) {
		int b1_num = 0;// 글번호
		int b1_view = 0;// 조회수
		try {
			con = pool.getConnection();
			sql = "select max(b1_num)	from b1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b1_num = rs.getInt(1) + 1;
			} else {
				b1_num = 1;
			}
			System.out.println("insertArticle==>b1_num" + b1_num);

			sql = "insert into b1(b1_num,b1_title,b1_content,userID,b1_view,b1_date) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, b1_num);
			pstmt.setString(2, article.getB1_title());
			pstmt.setString(3, article.getB1_content());
			pstmt.setString(4, article.getUserID());
			pstmt.setInt(5, b1_view);
			pstmt.setTimestamp(6, article.getB1_date());

			int insert = pstmt.executeUpdate();
			System.out.println("insert=>" + insert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insert article메서드 에러유발" + e);

		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
}


	

