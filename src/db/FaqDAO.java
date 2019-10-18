package db;

import java.sql.*;	//DB���
import java.util.*;	//ArrayList,List�� ���

public class FaqDAO {

	private DBConnectionMgr pool=null;	//1.�����Ұ�ü ����
	//����
	private Connection con=null;
	private PreparedStatement pstmt=null;	//?
	private ResultSet rs=null;	//select
	private String sql="";		//�����ų SQL���� ����
	
	//2.�����ڸ� ���ؼ� ����->������
	public FaqDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB���� ����=>"+e);
		}
	}//������
	
	public int getArticleCount() {
		int x=0;	//�� ���ڵ尳���� ����
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);	//������ڵ�
			sql="select count(*) from b4";		//select count(*) from member;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);
			}	
		} catch (Exception e) {
			System.out.println("getArticleCount() �޼��� ��������"+e);
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
			 * �׷��ȣ�� ���� �ֽ��� ���� �߽����� �����ϵ�, ���࿡ level�� ���� ��쿡��
			 * step������ ���������� ���ؼ� ���° ���ڵ��ȣ�� �����ؼ� �����Ұ��ΰ�?
			 */
			sql="select * from b4 order by b4_num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql�� ���ڵ� ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			//�۸�Ϻ���
			if(rs.next()) {	//���ڵ尡 �ּ� ���� 1�� �̻� �����Ѵٸ�
				articleList=new ArrayList(end);
				do {
					
					  FaqDTO article=new FaqDTO(); 
					  article.setB4_num(rs.getInt("b4_num"));
					  article.setB4_title(rs.getString("b4_title"));
					  article.setB4_content(rs.getString("b4_content"));
					  article.setAdminID(rs.getString("adminID"));
					  article.setB4_view(rs.getInt("b4_view"));
					  article.setB4_date(rs.getTimestamp("b4_date"));
					  
					//�߰�
					articleList.add(article);
				} while (rs.next());
			}	
		} catch (Exception e) {
			System.out.println("getArticles() �޼��� ��������"+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
//------(3)����¡ ó����� �������ִ� �޼���(ListAction)----------------------------------------
	//1)ȭ�鿡 �����ִ� ��������ȣ 2) ȭ�鿡 ����� ���ڵ� ����
	public Hashtable pageList(String pageNum,int count) {
		
		//1.����¡ ó�� ����� ������ hashtable ��ü�� ����
		Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
		//ListAction������ ������ ����¡ó���� ��� ó��
	     int pageSize=10;//numPerPage->�������� �����ִ� �Խù���(=���ڵ��) 10
	     int blockSize=10;//pagePerBlock->���� �����ִ� �������� 10
	     
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
	    //��1 list.jsp �ҽ�
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
	  		//����¡ó���� ���� ����� -> Hashtable,HashMap -> ListAction ���� -> �޸𸮿� ���� -> list.jsp
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
//(1)�Խ����� ���ڵ���� �˻�� ���� �޼����ۼ�(�˻��о�,�˻���)

	public int getArticleSearchCount(String search, String searchtext) {  //getMemberCount()
		int x=0;//�� ���ڵ尹���� ����
		
		try {
			con=pool.getConnection();//Ŀ�ؼ�Ǯ���� �Ѱ� ���������۾�
			System.out.println("con=>"+con);//������ڵ�
			//---------------------------------------------------------------
			if(search==null || search=="") { // �˻��о� ����X
				sql="select count(*) from b4"; //select count(*) from member;
			}else {	//�˻��о�(����,�ۼ���,����+����)
				if(search.equals("all")) {	//����+����
					sql="select count(*) from b4 where b4_title like '%"+
				searchtext+"%' or b4_content like '%"+searchtext+"%' or adminID like '%"+searchtext+"%'";
				}else {	//����,�ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql����
					sql="select count(*) from b4 where "+search+" like '%"+
				searchtext+"%'";
				}
			}
			System.out.println("getArticleSearchCount �˻�sql=>"+sql);
			//---------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);  //������=rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)=>�ʵ��X�� ����� �� ���� ��쿡 ���
			}
		}catch(Exception e) {
			System.out.println("getArticleSearchCount() �޼��� ��������"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);//���ᰴü �� �ٸ� ��ü �ݳ�
		}
		return x;
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
					sql = "select * from b4 order by b4_num desc limit ?,?";// 1,10
				} else { // ����+����
					if (search.equals("all")) { // ����+����
						sql = "select * from b4 where b4_title like '%" +searchtext+ "%' or b4_content like '%"
								+searchtext+ "%' or adminID like '%"+searchtext+"%' order by b4_num desc limit ?,?";
					} else { // ����,�ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql����
						sql = "select * from b4 where " +search+ " like '%" +searchtext
								+ "%' order by b4_num desc limit ?,?";
					}
				}
				System.out.println("getBoardArticles()�� sql=>"+sql);
				// --------------------------------------------------------------------------
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start - 1);// mysql�� ���ڵ������ ���������� 0���� ����
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				// �۸�Ϻ���
				if (rs.next()) {// ���ڵ尡 �ּ� ���� 1���̻� �����Ѵٸ�
					articleList = new ArrayList(end);// 10=>end������ŭ �����͸� ���� ������ �����϶�
					do {
						FaqDTO article = new FaqDTO();// MemberDTO~
						
						article.setB4_num(rs.getInt("b4_num"));
						article.setB4_title(rs.getString("b4_title"));
						article.setB4_content(rs.getString("b4_content"));
						article.setAdminID(rs.getString("adminID"));
						article.setB4_view(rs.getInt("b4_view"));
						article.setB4_date(rs.getTimestamp("b4_date"));
					
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
		
		//�ۻ󼼺���
		public FaqDTO getArticle(int num) {
			FaqDTO article=null;
			try {
				con=pool.getConnection();
				
				sql="update b4 set b4_view=b4_view+1 where b4_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				int update=pstmt.executeUpdate();
				System.out.println("��ȸ�� ��������(update)=>"+update);
				
				sql="select * from b4 where b4_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					article=new FaqDTO();
					article.setB4_num(rs.getInt("b4_num"));
					article.setB4_title(rs.getString("b4_title"));
					article.setB4_content(rs.getString("b4_content"));
					article.setAdminID(rs.getString("adminID"));
					article.setB4_view(rs.getInt("b4_view"));
					article.setB4_date(rs.getTimestamp("b4_date"));
				}
			} catch(Exception e) {
				System.out.println("getArticle() �޼ҵ� ��������"+e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article;
		}
		
}
