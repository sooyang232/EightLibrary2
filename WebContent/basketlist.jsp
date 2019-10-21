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
                            <li><a href="basketlist.jsp">관심도서</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">관심도서</h2>
                </div>
                <b><c:out value="${userID}" /></b>님의 관심도서목록
                <div class="board board-area">
                    <table class="table tac">
                        <colgroup>
                        	<col class="bookID">
                            <col class="book-list-title">
                            <col class="book-list-writer">
                            <col class="book-list-status">
                        </colgroup>
                        <thead>
                            <tr>
                            	<th class="bookID">자료번호</th>
                                <th class="book-list-title">자료명</th>
                                <th class="book-list-writer">저자</th>
                                <th class="book-list-status">관리</th>
                            </tr>
                        </thead>
                        <tbody>
                        
                        <c:forEach var="basket" items="${basketlist}">
                            <tr>
                            	<td class="bookID">${basket.bookID }</td>
                                <td class="book-list-title">${basket.bookName}</td>
                                <td class="book-list-writer">${basket.bookWriter }</td>
                                <td class="book-list-status">
                                	<input type="button" class="btn loan-off" value="관심취소"
                                		onclick="document.location.href='interDel.do?userID=${userID}&bookID=${basket.bookID}'">
                                </td>
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