package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;
import java.util.*;

public class NoticeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("NoticeAction의 매개변수 확인");
		System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
		
		int count=0;
		List articleList=null;
		
		NoticeDAO dbPro=new NoticeDAO();
		count=dbPro.getArticleSearchCount(search, searchtext);
		System.out.println("현재 검색된 레코드수(count)=>"+count);
		
		Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		
		if(count>0) {
			System.out.println("startRow=>"+pgList.get("startRow")+",endRow=>"+pgList.get("endRow"));
			articleList=dbPro.getBoardArticles(pgList.get("startRow"), pgList.get("pageSize"), search, searchtext);
		}else{
			articleList=Collections.EMPTY_LIST;
		}
		
		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
			
		return "/notice.jsp";
	}
	
}
