package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BookDAO {
	
	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";

	public BookDAO(){
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}
	
	public int getBookCount() {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from book";	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getBookCount() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	// newbook : �Խ����� ���ڵ���� �˻�� ���� �޼����ۼ�(�˻��о�,�˻���)
	public int getBookSearchCount(String search, String searchtext) { // getMemberCount()
		int x = 0;// �� ���ڵ尹���� ����

		try {
			con = pool.getConnection();// Ŀ�ؼ�Ǯ���� �Ѱ� ���������۾�
			System.out.println("con=>" + con);// ������ڵ�
			// ---------------------------------------------------------------
			if (search == null || search == "") { // �˻��о� ����X
				sql = "select count(*) from book";
			} else { // �˻��о�
				if (search.equals("all")) { // ����+����+���ǻ�
					sql = "select count(*) from book where bookName like '%" + searchtext + "%' or bookWriter like '%"
							+ searchtext + "%' or bookPublisher like '%" +searchtext+"%'";
				} else { // ����,����,���ǻ� -> �Ű������� �̿��ؼ� �ϳ��� sql����
					sql = "select count(*) from book where " + search + " like '%" + searchtext + "%'";
				}
			}
			System.out.println("getBookSearchCount �˻�sql=>" + sql);
			// ---------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// �����ִ� ����� �ִٸ�
				x = rs.getInt(1); // ������=rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)=>�ʵ��X�� ����� �� ���� ��쿡 ���
			}
		} catch (Exception e) {
			System.out.println("getBookSearchCount() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);// ���ᰴü �� �ٸ� ��ü �ݳ�
		}
		return x;
	}
	
	// search : �Խ����� ���ڵ���� �˻�� ���� �޼����ۼ�(�˻��о�,�˻���)
		public int getSearchCount(String search, String searchtext) { // getMemberCount()
			int x = 0;// �� ���ڵ尹���� ����

			try {
				con = pool.getConnection();// Ŀ�ؼ�Ǯ���� �Ѱ� ���������۾�
				System.out.println("con=>" + con);// ������ڵ�
				// ---------------------------------------------------------------
				if (search == null || search == "") { // �˻��о� ����X
					//sql = "select count(*) from book";
				} else { // �˻��о�
					if (search.equals("all")) { // ����+����+���ǻ�
						sql = "select count(*) from book where bookName like '%" + searchtext + "%' or bookWriter like '%"
								+ searchtext + "%' or bookPublisher like '%" +searchtext+"%'";
					} else { // ����,����,���ǻ� -> �Ű������� �̿��ؼ� �ϳ��� sql����
						sql = "select count(*) from book where " + search + " like '%" + searchtext + "%'";
					}
				}
				System.out.println("getBookSearchCount �˻�sql=>" + sql);
				// ---------------------------------------------------------------
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {// �����ִ� ����� �ִٸ�
					x = rs.getInt(1); // ������=rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)=>�ʵ��X�� ����� �� ���� ��쿡 ���
				}
			} catch (Exception e) {
				System.out.println("getBookSearchCount() �޼��� ��������" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);// ���ᰴü �� �ٸ� ��ü �ݳ�
			}
			return x;
		}
		
	public List getBooks(int start, int end) {

		List bookList = null;

		try {
			con = pool.getConnection();
			/*
			 * �׷��ȣ�� ���� �ֽ��� ���� �߽����� �����ϵ�, ���࿡ level�� ���� ��쿡�� step������ ���������� ���ؼ� ���° ���ڵ��ȣ��
			 * �����ؼ� �����Ұ��ΰ�?
			 */
			sql = "select * from book order by bookID desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ� ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) { // ���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
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
					// �߰�
					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBooks() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bookList;
	}
	
	// �˻�� ���� ���ڵ��� ���������� ���� �޼���
	public List getSearchBooks(int start, int end, String search, String searchtext) {

		List bookList = null;

		try {
			con = pool.getConnection();
			// --------------------------------------------------------------------------
			if (search == null | search == "") {
				sql = "select * from book order by bookID desc limit ?,?";// 1,10
			} else { 
				if (search.equals("all")) { 
					sql = "select * from book where bookName like '%" + searchtext + "%' or bookWriter like '%"
							+ searchtext + "%'or bookPublisher like '%" + searchtext 
							+ "%' order by bookID desc limit ?,?";
				} else { 
					sql = "select * from book where " + search + " like '%" + searchtext
							+ "%' order by bookID desc limit ?,?";
				}
			}
			System.out.println("getSearchBooks()�� sql=>" + sql);
			// --------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql�� ���ڵ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// �۸�Ϻ���
			if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
				bookList = new ArrayList(end);// 10=>end������ŭ �����͸� ���� ������ �����϶�
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
					// �߰�
					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getSearchBooks() �޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bookList;
	}
		
	// �ۻ� ����
		public BookDTO getBook(String id) {

			BookDTO book= null;
			try {
				con = pool.getConnection();

				sql = "select * from book where bookID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				// �۸�Ϻ���
				if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
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
				System.out.println("getBook() �޼��� ��������" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return book;
		}
		
		//����¡ ó����� �������ִ� �޼���
		//1)ȭ�鿡 �����ִ� ��������ȣ 2) ȭ�鿡 ����� ���ڵ� ����
		public Hashtable pageList(String pageNum,int count) {
			
			//1.����¡ ó�� ����� ������ hashtable ��ü�� ����
			Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
			//������ ����¡ó���� ��� ó��
		     int pageSize=5;//numPerPage->�������� �����ִ� �Խù���(=���ڵ��) 
		     int blockSize=5;//pagePerBlock->���� �����ִ� �������� 
		     
		    //�Խ����� �� ó�� �����Ű�� ������ 1���������� ���
		    if(pageNum==null){
		    	pageNum="1";//default(������ 1�������� �������� �ʾƵ� ������� �ϱ⶧����),���� �ֱ��� ��
		    }
		    int currentPage=Integer.parseInt(pageNum);//"1"->1 ����������(=nowPage)
		    //�޼��� ȣ��->���� ���ڵ��ȣ
		    //                  (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
		    int startRow=(currentPage-1)*pageSize+1; //���� ���ڵ� ��ȣ
		    int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30 ->������ ���ڵ��ȣ 	
		    int number=0;//beginPerPage->���������� �����ϴ� �� ó���� ������ �Խù���ȣ
		    System.out.println("���� ���ڵ��(count)=>"+count);
		    //			122-(1-1)*10=122,122-(2-1)*10
		    number=count-(currentPage-1)*pageSize;
		    System.out.println("�������� number=>"+number);
		    //����������,����,���������� ���
		    //					122/10=12.2+1=>12.2+1.0=13.2=13������
		    int pageCount=count/pageSize+(count%pageSize==0?0:1);
		    //��1 jsp �ҽ�
		  //���� �������� ��� -> 10 -> 10�� ���,3 ->3���
		  		int startPage=0;
		  		if(currentPage%blockSize!=0){//10�ǹ���� �ƴ϶��
		  			startPage=currentPage/blockSize*blockSize+1;
		  		}else{	//10%10=0,(10,20,30)
		  				//			((10/10)-1)*10+1
		  			startPage=((currentPage/blockSize)-1)*blockSize+1;
		  		}
		  		int endPage=startPage+blockSize-1;		//1+10-1=10
		  		System.out.println("startPage="+startPage+",endPage="+endPage);
		  		if(endPage > pageCount)
		  			endPage=pageCount;//������ ������ = ����������
		  		//����¡ó���� ���� ����� -> Hashtable,HashMap 
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
		
	// ���������� ���⿹��Ұ�ó��
	public void revBookUpdate(String id) {
		try {
			con = pool.getConnection();

			sql = "update book set bookCheck=? where bookID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "����Ұ�");
			pstmt.setString(2, id);

			int update = pstmt.executeUpdate();
			System.out.println("���⿹��Ұ�ó��(update)=>" + update);

		} catch (Exception e) {
			System.out.println("revBookUpdate() ���� ��������=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// ����������� �� ���⿹�డ��ó��
	public void revBookCancel(String id) {
		try {
			con = pool.getConnection();

			sql = "update book set bookCheck=? where bookID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "���Ⱑ��");
			pstmt.setString(2, id);

			int cancel = pstmt.executeUpdate();
			System.out.println("���⿹�డ��ó��(update)=>" + cancel);

		} catch (Exception e) {
			System.out.println("revBookCancel() ���� ��������=>" + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
}
