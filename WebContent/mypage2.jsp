<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="userID" value="${sessionScope.idKey}" />
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/sub.css">
    <script type="text/javascript">
        $(function(){
            $("#searchStartDate, #searchEndDate").datepicker({
                dateFormat: 'yy-mm-dd',
                showOn:"both",
                buttonImage:"img/sub/ico_calendar.png",
                buttonImageOnly:true
            })
        });
    </script>
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
                            <li><a href="mypage1.html">마이페이지</a></li>
                            <li><a href="mypage1.html">대출/예약/이력</a></li>
                            <li><a href="mypage2.html">예약현황</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">대출/예약/이력</h2>
                </div>
                <div class="sub-tab-menu">
                    <ul class="tab-menu-list">
                        <li class="menu-item">
                            <a href="mypage1.html"><span>대출현황</span></a>
                        </li>
                        <li class="menu-item active">
                            <a href="mypage2.html"><span>예약현황</span></a>
                        </li>
                        <li class="menu-item">
                            <a href="mypage3.html"><span>대출이력</span></a>
                        </li>
                    </ul>
                </div>
                <div class="board-search-box tar">
                    <form method="post" action="" id="searchForm">
                        <div class="input-box">
                            <select name="searchKey" title="검색 선택" id="searchKey" class="select">
                                <option value="reservedate" selected>예약일</option>
                            </select>
                            <input type="text" name="searchStartDate" id="searchStartDate" value="" style="width: 120px;" placeholder="검색시작일" class="form date"> ~ <input type="text" name="searchEndDate" id="searchEndDate" value="" style="width: 120px;" placeholder="검색종료일" class="form date">
                            <input type="submit" value="검색" title="검색" class="btn primary md">                            
                        </div>
                    </form>
                </div>
                <div class="board board-area">
                    <table class="table tac">
                        <colgroup>
                            <col class="book-list-title">
                            <col class="book-list-writer">
                            <col class="book-list-reservedate">
                            <col class="book-list-reserveduedate">
                            <col class="book-list-status">
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="book-list-title">자료명</th>
                                <th class="book-list-writer">저자</th>
                                <th class="book-list-reservedate">예약일</th>
                                <th class="book-list-reserveduedate">예약만기일</th>
                                <th class="book-list-status">관리</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="rev" items="${revlist}">
	                            <tr>
	                                <td class="book-list-title">${rev.bookName }</td>
	                                <td class="book-list-writer">${rev.bookWriter }</td>
	                                <td class="book-list-reservedate">
	                                	<fmt:formatDate value="${rev.revStartDate}" pattern="yyyy-MM-dd" />
	                                </td>
	                                <td class="book-list-reserveduedate">
	                                	<fmt:formatDate value="${rev.revEndDate}" pattern="yyyy-MM-dd" />
	                                </td>
	                                <td class="book-list-status">
	                                
	                                	<input type="button" class="btn loan-off" value="예약취소" id="revDelete"
                                		onclick="if(confirm('예약도서를 취소하시겠습니까?'))
                                		{document.location.href='revDel.do?userID=${userID}&bookID=${rev.bookID}'}">
	                                </td>
	                            </tr>                            
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination tac">
                        <a href="#" class="page-btn prev">이전</a>
                        <a href="#" class="page-num current">1</a>
                        <a href="#" class="page-num">2</a>
                        <a href="#" class="page-num">3</a>
                        <a href="#" class="page-num">4</a>
                        <a href="#" class="page-num">5</a>
                        <a href="#" class="page-btn next">다음</a>
                    </div>
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
</body>
</html>