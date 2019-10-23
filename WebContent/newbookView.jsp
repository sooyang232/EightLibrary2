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
                            <li><a href="index.html">Home</a></li>
                            <li><a href="search.html">자료검색</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">신착도서</h2>
                </div>
                
                <form method="post" action="interBookProc.do">
	                <input type="hidden" name="userID" value="${userID}">
	                <input type="hidden" name="bookID" value="${book.bookID}">
                	<input type="hidden" name="bookName" value="${book.bookName}">
                	<input type="hidden" name="bookWriter" value="${book.bookWriter}"> 
                <div class="result-detail">
                    <div class="detail-thumbnail">
                        <div class="image"><img src="${book.bookImage }" alt="표지"></div>
                    </div>
                    <div class="detail-summary">
                        <h3 class="title">${book.bookName }</h3>
                        <dl class="book-info">
                            <dt>등록번호</dt>
                            <dd>${book.bookID }</dd>
                            <dt>출판사</dt>
                            <dd>${book.bookPublisher}</dd>
                            <dt>발행일자</dt>
                            <dd><fmt:formatDate value="${book.bookDate}" pattern="yyyy-MM-dd" /></dd>
                            <dt>저자</dt>
                            <dd>${book.bookWriter }</dd>
                            <dt>표준번호</dt>
                            <dd>ISBN: ${book.isbn }</dd>
                        </dl>
                    </div>
                </div>
                <div class="btn-area tac">
                <c:if test="${empty userID}">
                    <a onclick="alert('로그인 후 이용가능합니다.')" href="login.do" class="btn deep-blue">관심도서담기</a>
                    <a onclick="alert('로그인 후 이용가능합니다.')" href="login.do" class="btn blue-gray">관심도서목록</a>
                </c:if>
                <c:if test="${!empty userID}">
                    <input type="submit" value="관심도서담기" class="btn deep-blue" >
                    <a href="basketlist.jsp" class="btn blue-gray">관심도서목록</a>
                </c:if>    
                    <a href="javascript:history.back();" class="btn gray">목록</a>
                </div>
                </form>
                <div class="add-section">
                    <h4 class="section-heading"><span>서평정보</span></h4>
                    <div class="section-body">${book.bookContent }</div>
                </div>
                <div class="add-section">
                    <h4 class="section-heading"><span>소장정보</span></h4>
                    <div class="section-body">
                        <table class="table tac">
                            <caption>소장정보</caption>
                            <thead>
                                <tr>
                                    <th scope="col" class="reg-id">등록번호</th>
                                    <th scope="col" class="book-status">도서상태</th>
                                    <th scope="col" class="return-date">반납예정일</th>
                                    <th scope="col" class="actions">예약하기</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="item-barcode" data-th="등록번호">${book.bookID }</td>
                                    <td class="item-status" data-th="도서상태">
                                        <span class="status available">${book.bookCheck }</span>
                                        <!-- <span class="status ing">대출중(예약 0명)</span> -->
                                        <!-- <span class="status not-available">대출불가</span> -->
                                    </td>
                                    <td class="item-due-date" data-th="반납예정일"></td>
                                    <td data-th="예약하기">
                                        <button type="button" class="btn loan-on">예약하기</button>
                                        <!-- <span class="btn loan-off">예약불가</span> -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
			</div>
		</div>
	</div>
	
	<c:import url="footer.jsp"/>
</body>
</html>
	