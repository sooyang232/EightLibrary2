package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;


public class SearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//한글처리
		
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");//검색분야
		String searchtext=request.getParameter("searchtext");
		System.out.println("pageNum="+pageNum+",search="+search+",searchtext"+searchtext);
		
		int count=0;//총레코드수
		List bookList=null;//화면에 출력할 레코드를 저장할 변수
		
		BookDAO bookdao=new BookDAO();
		count=bookdao.getSearchCount(search, searchtext);
		System.out.println("현재 검색된 레코드수(count)=>"+count);
		
		Hashtable<String,Integer> pgList=bookdao.pageList(pageNum, count);
		
		if(count>0) {
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			bookList=bookdao.getSearchBooks(pgList.get("startRow"),pgList.get("pageSize"), search, searchtext);
		}else {
			bookList=Collections.EMPTY_LIST;
		}
		
		//처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request객체)
		
		request.setAttribute("search", search);//검색분야
	    request.setAttribute("searchtext", searchtext);//검색어
		request.setAttribute("bookList", bookList);//${bookList}
		request.setAttribute("pgList", pgList);//페이징처리 10개 정보
		
		return "/search.jsp";
	}

}
