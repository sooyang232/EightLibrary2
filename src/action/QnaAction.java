package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;

import java.util.*;//List

public class QnaAction implements CommandAction {

	 // /qna.do�� ��û�� �������� ó�����ִ� ����
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");
		//�߰�(�˻��о�,�˻���)
		String search=request.getParameter("search");//�˻��о�
		String searchtext=request.getParameter("searchtext");
		System.out.println("QnaAction�� �Ű����� Ȯ��");
		System.out.println("pageNum="+pageNum+",search="+search+",searchtext="+searchtext);
		
		int count=0;//�ѷ��ڵ��
		List articleList=null;//ȭ�鿡 ����� ���ڵ带 ������ ����
		
		QnaDAO dbPro=new QnaDAO();
		count=dbPro.getArticleSearchCount(search, searchtext);//sql������ ����� ��� �޶���
		System.out.println("���� �˻��� ���ڵ��(count)=>"+count);
		
		Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		
	    if(count > 0){
	    	System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
	    	articleList=dbPro.getBoardArticles(pgList.get("startRow"), 
	    			                                          pgList.get("pageSize"),
	    			                                          search,searchtext);//ù��° ���ڵ��ȣ,�ҷ��� ����
	    }else { //count=0
	    	articleList=Collections.EMPTY_LIST;//�ƹ��͵� ���� �� list��ü ��ȯ
	    }
	    
		//2.ó���� ����� ����(�����޸𸮿� ����)->�̵��� �������� �����ؼ� ���(request��ü)
	    //request.getAttribue("currentPage"(Ű��))=>${currentPage(Ű��)}
	    request.setAttribute("search", search);//�˻��о�
	    request.setAttribute("searchtext", searchtext);//�˻���
	    request.setAttribute("pgList", pgList);//����¡ó�� 10�� ����
	    request.setAttribute("articleList", articleList);//${articleList}
	    
		//3.�����ؼ� �̵��� �� �ֵ��� �������� ����
		return "/qna.jsp"; // /board/list.jsp=>��θ� �����ؼ� ������ ���ִ�.
	}
}
