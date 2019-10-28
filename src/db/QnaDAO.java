package db;

import java.sql.*; //DB���
import java.util.*; //ArrayList,List�� ���

public class QnaDAO {

	private DBConnectionMgr pool = null; // 1.�����Ұ�ü ����
	// ����
	private Connection con = null;
	private PreparedStatement pstmt = null; // ?
	private ResultSet rs = null; // select
	private String sql = ""; // �����ų SQL���� ����

	// 2.�����ڸ� ���ؼ� ����->������
	public QnaDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>" + e);
		}
	}// ������

	public int getArticleCount() {
		int x = 0; // �� ���ڵ尳���� ����

		try {
			con = pool.getConnection();
			System.out.println("con=>" + con); // ������ڵ�
			sql = "select count(*) from b2"; // select count(*) from member;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // �����ִ� ����� �ִٸ�
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	// (1)�Խ����� ���ڵ���� �˻�� ���� �޼����ۼ�(�˻��о�,�˻���)

	public int getArticleSearchCount(String search, String searchtext) { // getMemberCount()
		int x = 0;// �� ���ڵ尹���� ����

		try {
			con = pool.getConnection();// Ŀ�ؼ�Ǯ���� �Ѱ� ���������۾�
			System.out.println("con=>" + con);// ������ڵ�
			// ---------------------------------------------------------------
			if (search == null || search == "") { // �˻��о� ����X
				sql = "select count(*) from b2"; // select count(*) from member;
			} else { // �˻��о�
				if (search.equals("all")) { 
					sql = "select count(*) from b2 where b2_title like '%" + searchtext + "%' or b2_content like '%"
							+ searchtext + "%' or userID like '%" + searchtext +"%'";
				} else { // ����,�ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql����
					sql = "select count(*) from b2 where " + search + " like '%" + searchtext + "%'";
				}
			}
			System.out.println("getArticleSearchCount �˻�sql=>" + sql);
			// ---------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// �����ִ� ����� �ִٸ�
				x = rs.getInt(1); // ������=rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)=>�ʵ��X�� ����� �� ���� ��쿡 ���
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCount() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);// ���ᰴü �� �ٸ� ��ü �ݳ�
		}
		return x;
	}

	public List getArticles(int start, int end) {

		List articleList = null;

		try {
			con = pool.getConnection();
			/*
			 * �׷��ȣ�� ���� �ֽ��� ���� �߽����� �����ϵ�, ���࿡ level�� ���� ��쿡�� step������ ���������� ���ؼ� ���° ���ڵ��ȣ��
			 * �����ؼ� �����Ұ��ΰ�?
			 */
			sql = "select * from b2 order by b2_reply desc,b2_step asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ� ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
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

					// �߰�
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticles() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

//----------------------------------------------------------------------------------
	// (2)�˻�� ���� ���ڵ��� ���������� ���� �޼���
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
					
				} else { // ����,�ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql����
					sql = "select * from b2 where " + search + " like '%" + searchtext
							+ "%' order by b2_reply desc,b2_step asc limit ?,?";
				}
			}
			System.out.println("getBoardArticles()�� sql=>" + sql);
			// --------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
				articleList = new ArrayList(end);// 10=>end������ŭ �����͸� ���� ������ �����϶�
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

					// �߰�
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBoardArticles() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

//------(3)����¡ ó����� �������ִ� �޼���(ListAction)----------------------------------------
	// 1)ȭ�鿡 �����ִ� ��������ȣ 2) ȭ�鿡 ����� ���ڵ� ����
	public Hashtable pageList(String pageNum, int count) {

		// 1.����¡ ó�� ����� ������ hashtable ��ü�� ����
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();
		// ListAction������ ������ ����¡ó���� ��� ó��
		int pageSize = 10;// numPerPage->�������� �����ִ� �Խù���(=���ڵ��) 10
		int blockSize = 10;// pagePerBlock->���� �����ִ� �������� 10

		// �Խ����� �� ó�� �����Ű�� ������ 1���������� ���
		if (pageNum == null) {
			pageNum = "1";// default(������ 1�������� �������� �ʾƵ� ������� �ϱ⶧����),���� �ֱ��� ��
		}
		int currentPage = Integer.parseInt(pageNum);// "1"->1 ����������(=nowPage)
		// �޼��� ȣ��->���� ���ڵ��ȣ
		// (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
		int startRow = (currentPage - 1) * pageSize + 1; // ���� ���ڵ� ��ȣ
		int endRow = currentPage * pageSize;// 1*10=10,2*10=20,3*10=30 ->������ ���ڵ��ȣ
		int number = 0;// beginPerPage->���������� �����ϴ� �� ó���� ������ �Խù���ȣ
		System.out.println("���� ���ڵ��(count)=>" + count);
		// 122-(1-1)*10=122,122-(2-1)*10
		number = count - (currentPage - 1) * pageSize;
		System.out.println("�������� number=>" + number);
		// ����������,����,���������� ���
		// 122/10=12.2+1=>12.2+1.0=13.2=13������
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// ��1 list.jsp �ҽ�
		// ���� �������� ��� -> 10 -> 10�� ���,3 ->3���
		int startPage = 0;
		if (currentPage % blockSize != 0) {// 10�ǹ���� �ƴ϶��
			startPage = currentPage / blockSize * blockSize + 1;
		} else { // 10%10=0,(10,20,30)
					// ((10/10)-1)*10+1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		int endPage = startPage + blockSize - 1; // 1+10-1=10
		System.out.println("startPage=" + startPage + ",endPage=" + endPage);
		if (endPage > pageCount)
			endPage = pageCount;// ������ ������ = ����������
		// ����¡ó���� ���� ����� -> Hashtable,HashMap -> ListAction ���� -> �޸𸮿� ���� -> list.jsp
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

	// �Խ��� �۾���
	public void insertArticle(QnaDTO article) {
		
		int b2_num=0;		//�۹�ȣ
		int b2_reply=0;	//�׷��ȣ
		int b2_view=0;		//��ȸ��
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
			System.out.println("insertArticle �޼��� ��������"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	// �ۻ� ����
	public QnaDTO getArticle(int num) {

		QnaDTO article = null;// ArrayList articleList=null;
		try {
			con = pool.getConnection();

			sql = "update b2 set b2_view=b2_view+1 where b2_num=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);// mysql�� ���ڵ������ ���������� 0���� ����
			int update = pstmt.executeUpdate();
			System.out.println("��ȸ�� ��������(update)=>" + update);// 1

			sql = "select * from b2 where b2_num=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
				article = new QnaDTO();// MemberDTO~
				// article = makeArticleFromResult();// ��ȯ���� ���ͼ� ó��
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
			System.out.println("getArticle() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	//���� �Խñ� ���� - count
	public int getBoardCount(String id) {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from b2 where userID=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBoard1Count() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//���� �Խñ� ���� - �Խñ� ����Ʈ
	public List getMyBoards(String id,int cnt) {
		List board1list = null;
		try {
			con = pool.getConnection();
			sql="select * from b2 where userID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				board1list = new ArrayList(cnt);
				do {

					QnaDTO qna=new QnaDTO();
					
					qna.setB2_num(rs.getInt("b2_num"));
					qna.setB2_title(rs.getString("b2_title"));
					qna.setB2_content(rs.getString("b2_content"));
					qna.setUserID(rs.getString("userID"));
					qna.setB2_date(rs.getTimestamp("b2_date"));
					qna.setB2_view(rs.getInt("b2_view"));

					// �߰�
					board1list.add(qna);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getInterBooks() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return board1list;
	}

}
