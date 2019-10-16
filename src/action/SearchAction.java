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
		request.setCharacterEncoding("utf-8");//�ѱ�ó��
		
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");//�˻��о�
		String searchtext=request.getParameter("searchtext");
		System.out.println("pageNum="+pageNum+",search="+search+",searchtext"+searchtext);
		
		int count=0;//�ѷ��ڵ��
		List bookList=null;//ȭ�鿡 ����� ���ڵ带 ������ ����
		
		BookDAO bookdao=new BookDAO();
		count=bookdao.getSearchCount(search, searchtext);
		System.out.println("���� �˻��� ���ڵ��(count)=>"+count);
		
		Hashtable<String,Integer> pgList=bookdao.pageList(pageNum, count);
		
		if(count>0) {
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			bookList=bookdao.getSearchBooks(pgList.get("startRow"),pgList.get("pageSize"), search, searchtext);
		}else {
			bookList=Collections.EMPTY_LIST;
		}
		
		//ó���� ����� ����(�����޸𸮿� ����)->�̵��� �������� �����ؼ� ���(request��ü)
		
		request.setAttribute("search", search);//�˻��о�
	    request.setAttribute("searchtext", searchtext);//�˻���
		request.setAttribute("bookList", bookList);//${bookList}
		request.setAttribute("pgList", pgList);//����¡ó�� 10�� ����
		
		return "/search.jsp";
	}

}
