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
	<script type="text/javascript" src="js/function.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
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
                
                
                <b><c:out value="${userID}" /></b>님의 예약도서목록
                
                <div class="board board-area">
                    <table class="table tac">
                        <colgroup>
                            <col class="bookID">
                            <col class="book-list-title">
                            <col class="book-list-writer">
                            <col class="book-list-reservedate">
                            <col class="book-list-reserveduedate">
                            <col class="book-list-status">
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="bookID">자료번호</th>
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
		                                <td class="bookID">${rev.bookID}</td>
		                                <td class="book-list-title">${rev.bookName}</td>
		                                <td class="book-list-writer">${rev.bookWriter}</td>
		                                <td class="book-list-reservedate"><fmt:formatDate value="${rev.revStartDate}" pattern="yyyy-MM-dd" /></td>
		                                <td class="book-list-reserveduedate"><fmt:formatDate value="${rev.revEndDate}" pattern="yyyy-MM-dd" /></td>
		                                <td class="book-list-status"><button type="button" class="btn loan-off"
                                		onclick="if(confirm('예약도서를 취소하시겠습니까?'))
                                		{document.location.href='revDel.do?userID=${userID}&bookID=${rev.bookID}'}">예약취소</button></td>
		                            </tr>            	
	                       		</c:forEach>
                        </tbody>
                    </table>
                    
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
</body>
</html>