<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/sub.css">
</head>
<body>
	
	<c:import url="header.jsp"/>

	<div id="content" class="site-content">
		<div class="inner-max">
			<div class="content-area">
                <div class="page-header">
                    <div class="breadcrumbs">
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li><a href="#">커뮤니티</a></li>
                            <li><a href="#">공지&새소식</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">공지&새소식</h2>
				</div>
                    
                <div class="board-search-box tar">
                    <form method="post" action="notice.do" id="searchForm">
                        <div class="input-box">
                            <select name="search" title="검색 선택" id="search" class="select">
                                <option value="all" selected>전체</option>
                                <option value="b3_title">제목</option>
                                <option value="b3_content">내용</option>
                                <option value="adminID">작성자</option>
                            </select>
                            <input type="text" name="searchtext" title="검색어 입력" id="searchtext" class="form">
                            <input type="submit" value="검색" title="검색" class="btn primary md">
                        </div>
                    </form>
                </div>
                <div class="board board-area">
                    <table class="table tac">
                        <thead>
                            <tr>
                                <th class="board-list-uid">번호</th>
                                <th class="board-list-title">제목</th>
                                <th class="board-list-user">작성자</th>
                                <th class="board-list-date">작성일</th>
                                <th class="board-list-view">조회</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${pgList.count==0}">
                        	<tr>
                        		<td>등록된 글이 없습니다.</td>
                        	</tr>
                        </c:if>
                        <c:if test="${pgList.count>0}">
                        	<c:set var="number" value="${pgList.number}"/>
                        	<c:forEach var="article" items="${articleList}">
                        		<tr>	                                
                        			<td class="board-list-uid">
                        				<c:out value="${number}"/>
                        				<c:set var="number" value="${number-1}"/>
                        			</td>
	                                <td class="board-list-title">
	                                    <a href="noticeView.do?num=${article.b3_num}&pageNum=${pgList.currentPage}">
	                                        <div class="board-default-cut-strings">${article.b3_title}
		                                        <jsp:useBean id="now" class="java.util.Date"/>
												<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
												<fmt:formatDate value="${article.b3_date}" pattern="yyyy-MM-dd" var="date" />
	                                        	<c:if test="${date==today}">
													<span class="board-default-new-notify">New</span>
												</c:if>
	                                        </div>
	                                        <div class="board-mobile-contents">
	                                            <span class="contents-item">${article.adminID}</span>
	                                            <span class="contents-item"><fmt:formatDate value="${article.b3_date}" pattern="yyyy-MM-dd" /></span>
	                                            <span class="contents-item">${article.b3_view}</span>
	                                        </div>
	                                    </a>
	                                </td>
	                                <td class="board-list-user">${article.adminID}</td>
	                                <td class="board-list-date"><fmt:formatDate value="${article.b3_date}" pattern="yyyy-MM-dd" /></td>
	                                <td class="board-list-view">${article.b3_view}</td>
	                            </tr>                        	
                        	</c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <div class="pagination tac">
                    	<c:if test="${pgList.count>0}">
							<c:if test="${pgList.startPage>pgList.blockSize}">
								<a href="notice.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}" class="page-btn prev">이전</a>
							</c:if>
							<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
								<c:if test="${pgList.currentPage==i}">
									<a href="notice.do?pageNum=${i}&search=${search}&searchtext=${earchtext}" class="page-num current">${i}</a>
								</c:if>
								<c:if test="${pgList.currentPage!=i}">
									<a href="notice.do?pageNum=${i}&search=${search}&searchtext=${earchtext}" class="page-num">${i}</a>
								</c:if>
							</c:forEach>
							<c:if test="${pgList.endPage<pgList.pageCount}">
								<a href="notice.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}" class="page-btn next">다음</a>
							</c:if>                    	
                    	</c:if>
                    </div>
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
	
</body>
</html>