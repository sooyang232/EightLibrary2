package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookDAO;


public class NewBookAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int count=0;//총레코드수
		List bookList=null;//화면에 출력할 레코드를 저장할 변수
		
		BookDAO bookdao=new BookDAO();
		count=bookdao.getBookCount();
		System.out.println("현재 검색된 레코드수(count)=>"+count);
		
		if(count>0) {
			bookList=bookdao.getBooks(1, count);
		}else {
			bookList=Collections.EMPTY_LIST;
		}
		
		//처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request객체)
		request.setAttribute("bookList", bookList);//${bookList}
		
		return "/newbook.jsp";
	}

}
