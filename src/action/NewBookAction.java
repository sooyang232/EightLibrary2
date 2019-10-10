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
		
		int count=0;//�ѷ��ڵ��
		List bookList=null;//ȭ�鿡 ����� ���ڵ带 ������ ����
		
		BookDAO bookdao=new BookDAO();
		count=bookdao.getBookCount();
		System.out.println("���� �˻��� ���ڵ��(count)=>"+count);
		
		if(count>0) {
			bookList=bookdao.getBooks(1, count);
		}else {
			bookList=Collections.EMPTY_LIST;
		}
		
		//ó���� ����� ����(�����޸𸮿� ����)->�̵��� �������� �����ؼ� ���(request��ü)
		request.setAttribute("bookList", bookList);//${bookList}
		
		return "/newbook.jsp";
	}

}
