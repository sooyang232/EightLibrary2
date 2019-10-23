package db;

import java.sql.*;
import java.util.*;

public class NoticeDAO {
	
	private DBConnectionMgr pool=null;
	
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	
	public NoticeDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	public int getArticleCount() {
		
		int x=0;
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from b3";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount()메서드 에러유발=>"+e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	}
	
	public int getArticleSearchCount(String search,String searchtext) {
		int x=0;
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			if(search==null || search=="") {
				sql="select count(*) from b3";
			}else{
				if(search.equals("all")) {
					sql="select count(*) from b3 where b3_title like '%"+searchtext+"%' or b3_content like '%"+searchtext+"%' or adminID like '%"+searchtext+"%'";
				}else{
					sql="select count(*) from b3 where "+search+" like '%"+searchtext+"%'";
				}
			}
			System.out.println("getArticleSearchCount 검색 sql=>"+sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCount()메서드 에러유발=>"+e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	}
	
	public List getArticles(int start,int end) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
			sql="select * from b3 order by b3_num limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				articleList=new ArrayList(end);
				do{
					NoticeDTO article=new NoticeDTO();
					article=makeArticleFromResult();					
					articleList.add(article);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticles()메서드 에러유발=>"+e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return articleList;
	}
	
	public List getBoardArticles(int start, int end, String search, String searchtext) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
			
			if(search==null || search=="") {
				sql="select * from b3 order by b3_num desc limit ?,?";
			}else{
				if(search.equals("all")) {
					sql="select * from b3 where b3_title like '%"+searchtext+"%' or b3_content like '%"+searchtext+"%' or adminID like '%"+searchtext+"%' order by b3_num desc limit ?,?";
				}else{
					sql="select * from b3 where "+search+" like '%"+searchtext+"%' order by b3_num desc limit ?,?";
				}
			}
			System.out.println("getBoardArticles()의 sql=>"+sql);
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				articleList=new ArrayList(end);
				
				do {
					NoticeDTO article=new NoticeDTO();
					article=makeArticleFromResult();					
					articleList.add(article);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getBoardArticles()메서드 에러유발=>"+e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return articleList;
	}	
	
	public Hashtable pageList(String pageNum,int count) {
		
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
		int pageSize=10;
		int blockSize=5;
		
		if(pageNum==null) {
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		int number=0;
		System.out.println("현재 레코드수(count)=>"+count);
		
		number=count-(currentPage-1)*pageSize;
		System.out.println("페이지별 number=>"+number);
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage=0;
		if(currentPage%blockSize!=0) {
			startPage=currentPage/blockSize*blockSize+1;
		}else{
			startPage=((currentPage/blockSize)-1)*blockSize+1;
		}
		int endPage=startPage+blockSize-1;
		System.out.println("startPage=>"+startPage+",endPage=>"+endPage);
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
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
	
	public NoticeDTO getArticle(int num) {
		
		NoticeDTO article=null;
		
		try {
			con=pool.getConnection();
			
			sql="update b3 set b3_view=b3_view+1 where b3_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update=pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>"+update);
			
			sql="select * from b3 where b3_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				article=makeArticleFromResult();
			}
		} catch(Exception e) {
			System.out.println("getArticle()메서드 에러유발=>"+e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return article;
	}
	
	private NoticeDTO makeArticleFromResult() throws Exception {
		
		NoticeDTO article=new NoticeDTO();
		
		article.setB3_num(rs.getInt("b3_num"));
		article.setB3_title(rs.getString("b3_title"));
		article.setB3_content(rs.getString("b3_content"));
		article.setAdminID(rs.getString("adminID"));
		article.setB3_date(rs.getTimestamp("b3_date"));
		article.setB3_view(rs.getInt("b3_view"));
		
		return article;
	}
	
}
