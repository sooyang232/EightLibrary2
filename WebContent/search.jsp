<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="userID" value="${sessionScope.idKey}" />
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
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
                            <li><a href="index.html">Home</a></li>
                            <li><a href="search.html">자료검색</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">통합검색</h2>
                </div>
                <div class="search-box tac">
                    <form method="post" action="search.do" id="searchForm">
                        <div class="input-box">
                            <select name="search" id="search" class="select">
                                <option value="all" selected>전체</option>
                                <option value="bookName">서명</option>
                                <option value="bookWriter">저자</option>
                                <option value="bookPublisher">출판사</option>
                            </select>
                            <input type="text" name="searchtext" id="searchtext" class="form">
                            <input type="submit" value="검 색" class="btn primary md">
                        </div>
                    </form>
                </div>
                
                <div class="summary-area">
                    <p class="total-text">전체 : ' <strong>${searchtext}</strong> ' 에 대한 검색결과입니다. (총 <em>${pgList.count }</em> 건)</p>
                </div>
                
                <div class="list-area">
                    <div class="utility-bar">
                        <div class="left">
                            <div class="check-item">
                                <input type="checkbox" name="bookCheckAll" id="bookCheckAll" title="전체 선택" onclick="bookCheckAll();">
                            </div>                            
                            <c:if test="${empty userID}">
								<a onclick="alert('로그인 후 이용가능합니다.')" href="login.do"
									class="btn function1">관심자료담기</a>
								<a onclick="alert('로그인 후 이용가능합니다.')" href="login.do"
									class="btn function2">관심자료보기</a>
							</c:if>
							<c:if test="${!empty userID}">
								<button type="button" class="btn function1">관심자료담기</button>
                            	<a href="basketlist.do?userID=${userID}" class="btn function2">관심자료보기</a>
                            </c:if>
                        </div>
                        <div class="right">
                            <select name="sort" id="sort" class="select" title="정렬방식 선택">
                                <option value="key" selected>등록일</option>
                                <option value="title">서명</option>
                                <option value="author">저자</option>
                                <option value="publisher">출판사</option>
                                <option value="publishyear">발행년도</option>
                            </select>
                            <select name="order" id="order" class="select" title="정렬순서 선택">
                                <option value="desc" selected>내림차순</option>
                                <option value="asc">오름차순</option>
                            </select>
                            <button type="button" class="btn go">실행</button>
                        </div>
                    </div>
                    
                    <c:if test="${pgList.count==0}">
                    </c:if>
                    <c:if test="${pgList.count!=0}">
                    <ul class="result-list">
                    <c:forEach var="book" items="${bookList}">
                        <li class="result-item">
                            <div class="check-item">
                                <input type="checkbox" id="check93154380" value="93154380">
                                <label for="solars-check93154380">${book.bookName}</label>
                            </div>
                            <div class="thumbnail" isbn="9788960881570">
                                <div class="image"><img src="${book.bookImage}" alt="표지"></div>
                            </div>
                            <div class="summary">
                                <dl class="book-data">
                                    <dt class="title"><a href="searchView.do?id=${book.bookID}&pageNum=${pgList.currentPage}">${book.bookName}</a></dt>
                                    <dd>저자: ${book.bookWriter} | 출판사: ${book.bookPublisher} | 
                                    발행일자: <fmt:formatDate value="${book.bookDate}" pattern="yyyy-MM-dd" /> |</dd>
                                    <dd>ISBN: ${book.isbn} | 등록번호: ${book.bookID}</dd>
                                    <dd class="book-status">
                                        <div class="left">
                                            자료상태: <span class="status available">${book.bookCheck}</span>
                                        </div>
                                        <div class="right">
                                            <span class="btn loan-on">
                                                <a href="#">대출예약</a>
                                            </span>
                                            <span class="btn print">
                                                <a href="#">위치출력</a>
                                            </span>
                                        </div>
                                    </dd>
                                </dl>
                            </div>
                        </li>
                     </c:forEach>   
                    </ul>
                    </c:if>
                    <!-- 페이징 처리 -->
                    <div class="pagination tac">
                    	<c:if test="${pgList.startPage > pgList.blockSize}">
                        	<a href="search.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}" class="page-btn prev">이전</a>
                        </c:if>
                        
                        <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
                        	<c:if test="${pgList.currentPage==i}">
                        		<a href="search.do?pageNum=${i}&search=${search}&searchtext=${searchtext}" class="page-num current">${i}</a>
                        	</c:if>
                        	<c:if test="${pgList.currentPage!=i}">
                        		<a href="search.do?pageNum=${i}&search=${search}&searchtext=${searchtext}" class="page-num">${i}</a>
                        	</c:if>
                        </c:forEach>
                        
                        <c:if test="${pgList.endPage < pgList.pageCount}">
                        	<a href="search.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}" class="page-btn next">다음</a>
                        </c:if>
                        
                    </div>
                    
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
</body>
</html>