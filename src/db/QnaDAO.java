package db;

import java.sql.*; //DB사용
import java.util.*; //ArrayList,List를 사용

public class QnaDAO {

	private DBConnectionMgr pool = null; // 1.연결할객체 선언
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null; // ?
	private ResultSet rs = null; // select
	private String sql = ""; // 실행시킬 SQL구문 저장

	// 2.생성자를 통해서 연결->의존성
	public QnaDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}// 생성자

	public int getArticleCount() {
		int x = 0; // 총 레코드개수를 저장

		try {
			con = pool.getConnection();
			System.out.println("con=>" + con); // 디버깅코드
			sql = "select count(*) from b2"; // select count(*) from member;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	// (1)게시판의 레코드수를 검색어에 따른 메서드작성(검색분야,검색어)

	public int getArticleSearchCount(String search, String searchtext) { // getMemberCount()
		int x = 0;// 총 레코드갯수를 저장

		try {
			con = pool.getConnection();// 커넥션풀에서 한개 빌려오는작업
			System.out.println("con=>" + con);// 디버깅코드
			// ---------------------------------------------------------------
			if (search == null || search == "") { // 검색분야 선택X
				sql = "select count(*) from b2"; // select count(*) from member;
			} else { // 검색분야
				if (search.equals("all")) { 
					sql = "select count(*) from b2 where b2_title like '%" + searchtext + "%' or b2_content like '%"
							+ searchtext + "%' or userID like '%" + searchtext +"%'";
				} else { // 제목,작성자 -> 매개변수를 이용해서 하나의 sql통합
					sql = "select count(*) from b2 where " + search + " like '%" + searchtext + "%'";
				}
			}
			System.out.println("getArticleSearchCount 검색sql=>" + sql);
			// ---------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				x = rs.getInt(1); // 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCount() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);// 연결객체 및 다른 객체 반납
		}
		return x;
	}

	public List getArticles(int start, int end) {

		List articleList = null;

		try {
			con = pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되, 만약에 level이 같은 경우에는 step값으로 오름차순을 통해서 몇번째 레코드번호를
			 * 기준해서 정렬할것인가?
			 */
			sql = "select * from b2 order by b2_reply desc,b2_step asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드 순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
				articleList = new ArrayList(end);
				do {

					QnaDTO article = new QnaDTO();
					article.setB2_num(rs.getInt("b2_num"));
					article.setB2_title(rs.getString("b2_title"));
					article.setB2_content(rs.getString("b2_content"));
					article.setUserID(rs.getString("userID"));
					article.setB2_view(rs.getInt("b2_view"));
					article.setB2_date(rs.getTimestamp("b2_date"));
					article.setB2_reply(rs.getInt("b2_reply"));
					article.setB2_step(rs.getInt("b2_step"));

					// 추가
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticles() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
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
				sql = "select * from b2 order by b2_reply desc,b2_step asc limit ?,?";// 1,10
			} else { 
				if (search.equals("all")) { 
					sql = "select * from b2 where b2_title like '%" + searchtext + "%' or b2_content like '%"
							+ searchtext +"%' or userID like '%" + searchtext 
							+ "%' order by b2_reply desc,b2_step asc limit ?,?";
					
				} else { // 제목,작성자 -> 매개변수를 이용해서 하나의 sql통합
					sql = "select * from b2 where " + search + " like '%" + searchtext
							+ "%' order by b2_reply desc,b2_step asc limit ?,?";
				}
			}
			System.out.println("getBoardArticles()의 sql=>" + sql);
			// --------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 생성하라
				do {
					QnaDTO article = new QnaDTO();// MemberDTO~

					article.setB2_num(rs.getInt("b2_num"));
					article.setB2_title(rs.getString("b2_title"));
					article.setB2_content(rs.getString("b2_content"));
					article.setUserID(rs.getString("userID"));
					article.setB2_view(rs.getInt("b2_view"));
					article.setB2_date(rs.getTimestamp("b2_date"));
					article.setB2_reply(rs.getInt("b2_reply"));
					article.setB2_step(rs.getInt("b2_step"));

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

//------(3)페이징 처리계산 정리해주는 메서드(ListAction)----------------------------------------
	// 1)화면에 보여주는 페이지번호 2) 화면에 출력할 레코드 개수
	public Hashtable pageList(String pageNum, int count) {

		// 1.페이징 처리 결과를 저장할 hashtable 객체를 선언
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();
		// ListAction에서의 복잡한 페이징처리를 대신 처리
		int pageSize = 10;// numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
		int blockSize = 10;// pagePerBlock->블럭당 보여주는 페이지수 10

		// 게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력
		if (pageNum == null) {
			pageNum = "1";// default(무조건 1페이지는 선택하지 않아도 보여줘야 하기때문에),가장 최근의 글
		}
		int currentPage = Integer.parseInt(pageNum);// "1"->1 현재페이지(=nowPage)
		// 메서드 호출->시작 레코드번호
		// (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
		int startRow = (currentPage - 1) * pageSize + 1; // 시작 레코드 번호
		int endRow = currentPage * pageSize;// 1*10=10,2*10=20,3*10=30 ->마지막 레코드번호
		int number = 0;// beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물번호
		System.out.println("현재 레코드수(count)=>" + count);
		// 122-(1-1)*10=122,122-(2-1)*10
		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=>" + number);
		// 총페이지수,시작,종료페이지 계산
		// 122/10=12.2+1=>12.2+1.0=13.2=13페이지
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 모델1 list.jsp 소스
		// 블럭당 페이지수 계산 -> 10 -> 10의 배수,3 ->3배수
		int startPage = 0;
		if (currentPage % blockSize != 0) {// 10의배수가 아니라면
			startPage = currentPage / blockSize * blockSize + 1;
		} else { // 10%10=0,(10,20,30)
					// ((10/10)-1)*10+1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		int endPage = startPage + blockSize - 1; // 1+10-1=10
		System.out.println("startPage=" + startPage + ",endPage=" + endPage);
		if (endPage > pageCount)
			endPage = pageCount;// 마지막 페이지 = 총페이지수
		// 페이징처리에 대한 계산결과 -> Hashtable,HashMap -> ListAction 전달 -> 메모리에 저장 -> list.jsp
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

	// 게시판 글쓰기
	public void insertArticle(QnaDTO article) {
		
		int b2_num=0;		//글번호
		int b2_reply=0;	//그룹번호
		int b2_view=0;		//조회수
		try {
			con=pool.getConnection();
			sql="select max(b2_num) from b2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b2_num=rs.getInt(1)+1;
			}else {
				b2_num=1;
			}
			System.out.println("insertArticle ==> b2_num"+b2_num);
			
			sql="select max(b2_reply) from b2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b2_reply=rs.getInt(1)+1;
			}
			
			
			sql="insert into b2(b2_num,b2_title,b2_content,userID,b2_view,b2_date,b2_reply,b2_step) values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, b2_num);
			pstmt.setString(2, article.getB2_title());
			pstmt.setString(3, article.getB2_content());
			pstmt.setString(4, article.getUserID());
			pstmt.setInt(5,b2_view);
			pstmt.setTimestamp(6,article.getB2_date());
			pstmt.setInt(7, b2_reply);
			pstmt.setInt(8, article.getB2_step());
			
			int insert=pstmt.executeUpdate();
			System.out.println("insert=>"+insert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertArticle 메서드 에러유발"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	// 글상세 보기
	public QnaDTO getArticle(int num) {

		QnaDTO article = null;// ArrayList articleList=null;
		try {
			con = pool.getConnection();

			sql = "update b2 set b2_view=b2_view+1 where b2_num=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);// mysql은 레코드순번이 내부적으로 0부터 시작
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>" + update);// 1

			sql = "select * from b2 where b2_num=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				article = new QnaDTO();// MemberDTO~
				// article = makeArticleFromResult();// 반환형을 얻어와서 처리
				article.setB2_num(rs.getInt("b2_num"));
				article.setB2_title(rs.getString("b2_title"));
				article.setB2_content(rs.getString("b2_content"));
				article.setUserID(rs.getString("userID"));
				article.setB2_view(rs.getInt("b2_view"));
				article.setB2_date(rs.getTimestamp("b2_date"));
				article.setB2_reply(rs.getInt("b2_reply"));
				article.setB2_step(rs.getInt("b2_step"));

			}
		} catch (Exception e) {
			System.out.println("getArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	//나의 게시글 보기 - count
	public int getBoardCount(String id) {
		int x=0;	//총 레코드개수를 저장
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//디버깅코드
			sql="select count(*) from b2 where userID=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//보여주는 결과가 있다면
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBoard1Count() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//나의 게시글 보기 - 게시글 리스트
	public List getMyBoards(String id,int cnt) {
		List board1list = null;
		try {
			con = pool.getConnection();
			sql="select * from b2 where userID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // 레코드가 최소 만족 1개 이상 존재한다면
				board1list = new ArrayList(cnt);
				do {

					QnaDTO qna=new QnaDTO();
					
					qna.setB2_num(rs.getInt("b2_num"));
					qna.setB2_title(rs.getString("b2_title"));
					qna.setB2_content(rs.getString("b2_content"));
					qna.setUserID(rs.getString("userID"));
					qna.setB2_date(rs.getTimestamp("b2_date"));
					qna.setB2_view(rs.getInt("b2_view"));

					// 추가
					board1list.add(qna);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getInterBooks() 메서드 에러유발"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return board1list;
	}

}
