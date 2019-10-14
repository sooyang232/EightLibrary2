package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BookDAO {
	
	private DBConnectionMgr pool=null;	//1.연결할객체 선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";

	public BookDAO(){
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	public int getBookCount() {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from book";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBookCount() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List getBooks(int start, int end) {

		List bookList = null;

		try {
			con = pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는 step값으로 오름차순을 통해서 몇번째 레코드번호를
			 * 기준해서 정렬할것인가?
			 */
			sql = "select * from book order by bookID desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드 순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
				bookList = new ArrayList(end);
				do {

					BookDTO book = new BookDTO();
					book.setBookID(rs.getString("bookID"));
					book.setBookName(rs.getString("bookName"));
					book.setBookWriter(rs.getString("bookWriter"));
					book.setBookContent(rs.getString("bookContent"));
					book.setBookPublisher(rs.getString("bookPublisher"));
					book.setBookDate(rs.getTimestamp("bookDate"));
					book.setIsbn(rs.getString("isbn"));
					book.setBookImage(rs.getString("bookImage"));
					book.setBookCheck(rs.getString("bookCheck"));
					// 추가
					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBooks() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bookList;
	}
	
	// 글상세 보기
		public BookDTO getBook(String id) {

			BookDTO book= null;
			try {
				con = pool.getConnection();

				sql = "select * from book where bookID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// 글목록보기
				if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
					book = new BookDTO();
					
					book.setBookID(rs.getString("bookID"));
					book.setBookName(rs.getString("bookName"));
					book.setBookWriter(rs.getString("bookWriter"));
					book.setBookContent(rs.getString("bookContent"));
					book.setBookPublisher(rs.getString("bookPublisher"));
					book.setBookDate(rs.getTimestamp("bookDate"));
					book.setIsbn(rs.getString("isbn"));
					book.setBookImage(rs.getString("bookImage"));
					book.setBookCheck(rs.getString("bookCheck"));

				}
			} catch (Exception e) {
				System.out.println("getBook() 메서드 에러유발" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return book;
		}
		
		//페이징 처리계산 정리해주는 메서드
		//1)화면에 보여주는 페이지번호 2) 화면에 출력할 레코드 개수
		public Hashtable pageList(String pageNum,int count) {
			
			//1.페이징 처리 결과를 저장할 hashtable 객체를 선언
			Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
			//복잡한 페이징처리를 대신 처리
		     int pageSize=5;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 
		     int blockSize=3;//pagePerBlock->블럭당 보여주는 페이지수 
		     
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
		    //모델1 jsp 소스
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
		  		//페이징처리에 대한 계산결과 -> Hashtable,HashMap 
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
		
}
